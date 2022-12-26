package fr.hyriode.cosmetics.common;

import fr.hyriode.cosmetics.particle.AbstractParticle;

import java.util.List;

public interface CosmeticManager<T extends AbstractCosmetic> {

    List<T> getCosmetics();

    void registerCosmetic(T cosmetic);

}
