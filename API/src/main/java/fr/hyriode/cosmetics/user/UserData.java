package fr.hyriode.cosmetics.user;

import fr.hyriode.api.player.HyriPlayerData;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserData extends HyriPlayerData {

    private final List<String> unlockedCosmetics = new ArrayList<>();
    private final Map<String, String> equippedCosmetics = new HashMap<>();

    public List<AbstractCosmetic> getUnlockedCosmetics() {
        final List<AbstractCosmetic> result = new ArrayList<>();

        for (String cosmetic : this.unlockedCosmetics) {
            result.add(HyriCosmetics.get().getCosmetic(cosmetic));
        }

        return result;
    }

    public List<AbstractCosmetic> getUnlockedCosmetics(CosmeticCategory category) {
        final List<AbstractCosmetic> result = new ArrayList<>();

        for (String cosmetic : this.unlockedCosmetics) {
            final AbstractCosmetic abstractCosmetic = HyriCosmetics.get().getCosmetic(cosmetic);

            if (abstractCosmetic.getCategory() == category) {
                result.add(abstractCosmetic);
            }
        }

        return result;
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

    public Map<String, String> getEquippedCosmetics() {
        return equippedCosmetics;
    }
}
