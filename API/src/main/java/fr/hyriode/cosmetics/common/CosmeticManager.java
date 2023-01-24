package fr.hyriode.cosmetics.common;

import java.util.List;

public interface CosmeticManager<T extends AbstractCosmetic> {

    List<T> getCosmetics();

    void registerCosmetic(T cosmetic);

}
