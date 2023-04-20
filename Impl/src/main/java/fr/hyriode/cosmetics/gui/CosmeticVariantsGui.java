package fr.hyriode.cosmetics.gui;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticVariants;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.user.PlayerCosmetic;
import fr.hyriode.hyrame.inventory.pagination.PaginatedInventory;
import fr.hyriode.hyrame.inventory.pagination.PaginatedItem;
import fr.hyriode.hyrame.inventory.pagination.PaginationArea;
import fr.hyriode.hyrame.item.ItemBuilder;
import fr.hyriode.hyrame.utils.Pagination;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CosmeticVariantsGui extends PaginatedInventory {

    private final PlayerCosmetic<?> playerCosmetic;

    public CosmeticVariantsGui(final Player owner, final PlayerCosmetic<?> playerCosmetic) {
        super(owner, name(owner, "gui.cosmetic.variants"), 9 * 6);
        this.playerCosmetic = playerCosmetic;

        this.paginationManager.setArea(new PaginationArea(20, 33));
        this.applyDesign(Design.BORDER);

        this.setItem(4, playerCosmetic.getAbstractCosmetic().getType().toItemStack(owner, false));
        this.setItem(49,
                new ItemBuilder(Material.ARROW).withName(name(owner, "go-back.display")).build(), event -> {
                    event.getWhoClicked().closeInventory();
                    new CosmeticsGui((Player) event.getWhoClicked(), playerCosmetic.getAbstractCosmetic().getCategory()).open();
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

        AbstractCosmetic<?> abstractCosmetic = this.playerCosmetic.getAbstractCosmetic();
        if (abstractCosmetic.hasVariants()) {
            for (Map.Entry<String, ItemStack> entry : abstractCosmetic.getVariantsItem().entrySet()) {
                pagination.add(PaginatedItem.from(entry.getValue(), clickEvent(entry.getKey(), abstractCosmetic)));
            }
        }

        this.paginationManager.updateGUI();
    }

    private Consumer<InventoryClickEvent> clickEvent(String variant, AbstractCosmetic<?> cosmetic) {
        return event -> {
            cosmetic.setVariant(variant);
            this.owner.playSound(this.owner.getLocation(), Sound.VILLAGER_IDLE, 0.5F, 1.0F);
            this.owner.getOpenInventory().close();
        };
    }
}
