package fr.hyriode.cosmetics.gui;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.hyrame.inventory.HyriInventory;
import org.bukkit.entity.Player;

public class FilterGui extends HyriInventory {

    private final CosmeticUser user;

    public FilterGui(Player owner) {
        super(owner, name(owner, "gui.cosmetic.filter.name"), 9 * 5);
        this.user = HyriCosmetics.get().getUserProvider().getUser(owner.getUniqueId());
    }
}
