package fr.hyriode.cosmetics.gui;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
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

import java.util.List;
import java.util.function.Consumer;

public class CosmeticGui extends PaginatedInventory {

    private final CosmeticUser user;
    private final CosmeticCategory category;

    public CosmeticGui(final Player owner, final CosmeticCategory category) {
        super(owner, name(owner, "gui.cosmetic.name"), 9 * 6);
        this.user = HyriCosmetics.get().getUserManager().getUser(owner.getUniqueId());
        this.category = category;

        this.paginationManager.setArea(new PaginationArea(20, 33));
        this.applyDesign(Design.BORDER);
        final List<AbstractCosmetic> cosmetics = HyriCosmetics.get().getCosmetics().get(category);
        final List<AbstractCosmetic> unlockedCosmetics = user.getUnlockedCosmetics(category);

        this.setItem(
                4,
                category.getIcon()
                        .withName("§b" + category.getTranslatedName().getValue(owner))
                        .withLore(StringUtil.splitIntoPhrases(category.getTranslatedDescription().getValue(owner), 40))
                        .appendLore("§7")
                        .appendLore(HyriLanguageMessage.get("gui.cosmetic.unlocked").getValue(owner)
                                .replace("%percentage%", String.valueOf(unlockedCosmetics.size() / (cosmetics.size() == 0 ? 1 : cosmetics.size()) * 100))
                                .replace("%unlocked%", String.valueOf(unlockedCosmetics.size()))
                                .replace("%total%", String.valueOf(cosmetics.size()))
                        ).build()
        );

        this.setItem(49, new ItemBuilder(Material.ARROW).build());
        this.setupItems();
    }

    @Override
    public void updatePagination(int page, List<PaginatedItem> items) {
        this.addDefaultPagesItems(27, 35);
    }

    private void setupItems() {
        final Pagination<PaginatedItem> pagination = this.paginationManager.getPagination();
        pagination.clear();

        for (AbstractCosmetic cosmetic : HyriCosmetics.get().getCosmetics().get(category)) {
            pagination.add(PaginatedItem.from(this.createCosmeticItem(cosmetic).build(), this.clickEvent(cosmetic)));
        }

        this.paginationManager.updateGUI();
    }

    private ItemBuilder createCosmeticItem(final AbstractCosmetic cosmetic) {
        return cosmetic.getIcon()
                .withName(ChatColor.AQUA + cosmetic.getTranslatedName().getValue(this.owner))
                .withLore(StringUtil.splitIntoPhrases(cosmetic.getTranslatedDescription().getValue(this.owner), 35))
                .appendLore("")
                .appendLore(ChatColor.GRAY + HyriLanguageMessage.get("gui.cosmetic.rarity").getValue(this.owner) + ": " + cosmetic.getRarity().getColor() +  cosmetic.getRarity().getName());
    }

    private Consumer<InventoryClickEvent> clickEvent(AbstractCosmetic cosmetic) {
        return event -> {
            if (event.isRightClick()) {
                this.owner.playSound(this.owner.getLocation(), Sound.FIZZ, 0.5F, 1.0F);
                this.setupItems();
            }
        };
    }
}
