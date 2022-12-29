package fr.hyriode.cosmetics.gui;

import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.hyrame.inventory.HyriInventory;
import org.bukkit.entity.Player;

public class CosmeticsGui extends HyriInventory {

    protected final IHyriPlayer account;

    public CosmeticsGui(Player owner, String name, int size) {
        super(owner, name, size);
        this.account = IHyriPlayer.get(owner.getUniqueId());
    }

    @Override
    public void open() {
        super.open();
        this.applyDesign(Design.BORDER);
        for (CosmeticCategory category : HyriCosmetics.get().getCategories()) {
            this.setItem(
                    category.getGuiSlot(),
                    category.getIcon()
                            .withName("§b" + category.getTranslatedName().getValue(account))
                            .withLore("§7" + category.getTranslatedDescription().getValue(account))
                            .build()
            );
        }
    }
}