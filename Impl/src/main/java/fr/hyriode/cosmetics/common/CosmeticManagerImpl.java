package fr.hyriode.cosmetics.common;

import java.util.ArrayList;
import java.util.List;

public abstract class CosmeticManagerImpl<T extends AbstractCosmetic> implements CosmeticManager<T> {

    protected final List<T> cosmetics;

    public CosmeticManagerImpl() {
        this.cosmetics = new ArrayList<>();
    }

    @Override
    public void registerCosmetic(T particle) {
        this.cosmetics.add(particle);
    }

    @Override
    public List<T> getCosmetics() {
        return this.cosmetics;
    }

}
