package fr.hyriode.cosmetics.user;

import fr.hyriode.cosmetics.common.AbstractCosmetic;

public class PlayerCosmeticImpl<T extends AbstractCosmetic> implements PlayerCosmetic<T> {

    private final T cosmetic;
    private final  CosmeticUser user;

    public PlayerCosmeticImpl(final T cosmetic, final CosmeticUser user) {
        this.cosmetic = cosmetic;
        this.user = user;
    }

    @Override
    public T getCosmetic() {
        return this.cosmetic;
    }

    @Override
    public CosmeticUser getUser() {
        return this.user;
    }

    @Override
    public void equip() {
        this.cosmetic.onEquip(this.user);
    }

    @Override
    public void unequip() {
        this.cosmetic.onUnequip(this.user);
    }

}
