package fr.hyriode.cosmetics.user;

import fr.hyriode.api.language.HyriLanguage;
import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import org.bukkit.entity.Player;

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
        final Player player = this.user.asBukkit();
        this.user.asBukkit().sendMessage(HyriLanguageMessage.get("cosmetics.equip").getValue(player).replace("%cosmetic%", this.cosmetic.getTranslatedName().getValue(player)));
        this.cosmetic.onEquip(this.user);
    }

    @Override
    public void unequip() {
        final Player player = this.user.asBukkit();
        this.user.asBukkit().sendMessage(HyriLanguageMessage.get("cosmetics.unequip").getValue(player).replace("%cosmetic%", this.cosmetic.getTranslatedName().getValue(player)));
        this.cosmetic.onUnequip(this.user);
    }

}
