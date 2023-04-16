package fr.hyriode.cosmetics.gui;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.utils.StringUtil;
import fr.hyriode.hyrame.inventory.pagination.PaginatedInventory;
import fr.hyriode.hyrame.inventory.pagination.PaginatedItem;
import fr.hyriode.hyrame.inventory.pagination.PaginationArea;
import fr.hyriode.hyrame.item.ItemBuilder;
import fr.hyriode.hyrame.utils.Pagination;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CosmeticsGui extends PaginatedInventory {

    private final CosmeticUser user;
    private final CosmeticCategory category;

    public CosmeticsGui(final Player owner, final CosmeticCategory category) {
        super(owner, name(owner, "gui.cosmetic.name"), 9 * 6);
        this.user = HyriCosmetics.get().getUserProvider().getUser(owner.getUniqueId());
        this.category = category;

        this.paginationManager.setArea(new PaginationArea(21, 33));
        this.applyDesign(Design.BORDER);

        this.update();
    }

    @Override
    public void update() {
        final List<Cosmetics> cosmetics = HyriCosmetics.get().getCosmetics().get(category);
        final List<Cosmetics> unlockedCosmetics = user.getUnlockedCosmetics(category);

        this.setItem(
                4,
                new ItemBuilder(category.getIcon())
                        .withName("§b" + category.getTranslatedName().getValue(owner))
                        .withLore(StringUtil.splitIntoPhrases(category.getTranslatedDescription().getValue(owner), 40))
                        .appendLore("§7")
                        .appendLore(HyriLanguageMessage.get("gui.cosmetic.unlocked").getValue(owner)
                                .replace("%percentage%", String.valueOf(unlockedCosmetics.size() / (cosmetics.size() == 0 ? 1 : cosmetics.size()) * 100))
                                .replace("%unlocked%", String.valueOf(unlockedCosmetics.size()))
                                .replace("%total%", String.valueOf(cosmetics.size()))
                        ).build()
        );

        if (!user.hasEquippedCosmetic(category)) {
            this.setItem(20,
                    new ItemBuilder(Material.BARRIER)
                            .withName(name(owner, "gui.cosmetic.equipped.none.name").replace("%type%", category.getTranslatedName().getValue(owner)))
                            .build()
            );
        } else {
            this.setItem(
                    20,
                    this.createCosmeticItem(user.getEquippedCosmetic(category)),
                    this.clickEvent(user.getEquippedCosmetic(category))
            );
        }

        this.setItem(49, new ItemBuilder(Material.ARROW).withName(name(owner, "go-back.display")).build(), event -> this.update());
        this.setItem(51, new ItemBuilder(Material.RAILS).withName(name(owner, "gui.cosmetic.filter.item.name")).withLore(
                name(owner, "gui.cosmetic.filter.item.lore")
                        //.replace("")
                        .split("\n")
        ).build(), event -> this.update());

        this.setItem(
                52,
                new ItemBuilder(Material.HOPPER).withName(name(owner, "gui.cosmetic.click_to_unequip")).build(),
                event -> {
                    if (user.hasEquippedCosmetic(category)) {
                        user.unequipCosmetic(category);
                        event.getWhoClicked().closeInventory();
                        update();
                        new CosmeticsGui((Player) event.getWhoClicked(), category).open();
                        this.owner.playSound(this.owner.getLocation(), Sound.FIZZ, 0.5F, 1.0F);
                    }
                }
        );

        this.setupItems();
    }

    @Override
    public void updatePagination(int page, List<PaginatedItem> items) {
        this.addDefaultPagesItems(27, 35);
    }

    private void setupItems() {
        final Pagination<PaginatedItem> pagination = this.paginationManager.getPagination();
        pagination.clear();

        List<Cosmetics> cosmetics = new ArrayList<>(HyriCosmetics.get().getFilteredCosmetics(user, category));
        if (user.hasEquippedCosmetic(category)) {
            cosmetics.remove(user.getEquippedCosmetic(category));
        }
        for (Cosmetics cosmetic : cosmetics) {
            pagination.add(PaginatedItem.from(this.createCosmeticItem(cosmetic), this.clickEvent(cosmetic)));
        }

        this.paginationManager.updateGUI();
    }

    private ItemStack createCosmeticItem(final Cosmetics cosmetic) {
        final String footer;
        final ItemBuilder builder = new ItemBuilder(cosmetic.getIcon())
                .withName(ChatColor.AQUA + cosmetic.getTranslatedName().getValue(this.owner))
                .withLore(StringUtil.splitIntoPhrases(cosmetic.getTranslatedDescription().getValue(this.owner), 35))
                .appendLore("")
                .appendLore( name(this.owner, "gui.cosmetic.rarity") + ": " + cosmetic.getRarity().getColor() +  cosmetic.getRarity().getName().toUpperCase());

        if (user.hasEquippedCosmetic(category) && user.getEquippedCosmetic(category) == cosmetic) {
            footer = name(owner, "gui.cosmetic.click_to_edit");
            builder.withGlow();
        } else {
            footer = name(owner, "gui.cosmetic.click_to_equip");
        }

        return builder.appendLore("").appendLore(footer).build();
    }

    private Consumer<InventoryClickEvent> clickEvent(Cosmetics cosmetic) {
        return event -> {
            if (event.isLeftClick()) {
                this.owner.playSound(this.owner.getLocation(), Sound.VILLAGER_IDLE, 0.5F, 1.0F);
                this.user.equipCosmetic(cosmetic);
                this.update();
            }
        };
    }
}
