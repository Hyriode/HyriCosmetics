package fr.hyriode.cosmetics.user;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import org.bukkit.entity.Player;

public interface PlayerCosmetic<T extends AbstractCosmetic> {

    T getCosmetic();

    CosmeticUser getUser();

    void equip();

    void unequip();
}
