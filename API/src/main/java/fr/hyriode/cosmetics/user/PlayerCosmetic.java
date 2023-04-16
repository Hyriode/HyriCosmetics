package fr.hyriode.cosmetics.user;

import fr.hyriode.cosmetics.common.AbstractCosmetic;

public interface PlayerCosmetic<T extends AbstractCosmetic> {

    T getCosmetic();

    CosmeticUser getUser();

    void equip(boolean message);

    void unequip(boolean message);
}
