package fr.hyriode.cosmetics.gui;

import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.hyrame.inventory.HyriInventory;
import org.bukkit.entity.Player;

public class CosmeticsGui extends HyriInventory {

    protected final IHyriPlayer account;

    public CosmeticsGui(Player owner, String name, int size) {
        super(owner, name, size);
        this.account = IHyriPlayer.get(owner.getUniqueId());
    }
}
