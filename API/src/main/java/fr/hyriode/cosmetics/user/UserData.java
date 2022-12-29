package fr.hyriode.cosmetics.user;

import fr.hyriode.api.player.HyriPlayerData;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.AbstractCosmetic;

import java.util.ArrayList;
import java.util.List;

public class UserData extends HyriPlayerData {

    private final List<String> unlockedCosmetics = new ArrayList<>();

    public List<AbstractCosmetic> getUnlockedCosmetics() {
        final List<AbstractCosmetic> result = new ArrayList<>();

        for (String cosmetic : this.unlockedCosmetics) {
            result.add(HyriCosmetics.get().get);
        }

        return unlockedCosmetics;
    }

    public void addUnlockedCosmetic(String cosmetic) {
        this.unlockedCosmetics.add(cosmetic);
    }

    public void removeUnlockedCosmetic(String cosmetic) {
        this.unlockedCosmetics.remove(cosmetic);
    }

    public boolean hasUnlockedCosmetic(String cosmetic) {
        return this.unlockedCosmetics.contains(cosmetic);
    }

}
