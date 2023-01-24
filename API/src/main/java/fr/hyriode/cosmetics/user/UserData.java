package fr.hyriode.cosmetics.user;

import fr.hyriode.api.player.HyriPlayerData;

import java.util.HashMap;
import java.util.Map;

public class UserData extends HyriPlayerData {

    private final Map<String, String> equippedCosmetics = new HashMap<>();

    public Map<String, String> getEquippedCosmetics() {
        return equippedCosmetics;
    }
}
