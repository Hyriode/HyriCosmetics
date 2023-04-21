package fr.hyriode.cosmetics.common;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.cosmetics.gadget.AbstractGadget;
import fr.hyriode.cosmetics.particle.AbstractParticle;
import fr.hyriode.cosmetics.pet.AbstractPet;
import fr.hyriode.cosmetics.utils.Head;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface CosmeticCategory {

    String getName();

    default HyriLanguageMessage getTranslatedName() {
        return HyriLanguageMessage.get("cosmetic.type." + this.getName() + ".name");
    }

    default HyriLanguageMessage getTranslatedDescription() {
        return HyriLanguageMessage.get("cosmetic.type." + this.getName() + ".description");
    }

    ItemStack getIcon();

    int getGuiSlot();

    Class<? extends AbstractCosmetic> getCosmeticClass();

    enum Default implements CosmeticCategory {

        PARTICLE("particle", AbstractParticle.class, new ItemBuilder(Material.BLAZE_POWDER).build(), 21),
        PET("pet", AbstractPet.class, new ItemBuilder(Material.BONE).build(), 22),
        BALLOON("balloon", AbstractParticle.class, Head.BALLOON_COLOR_RED.asItem(), 23),
        GADGET("gadget", AbstractGadget.class, new ItemBuilder(Material.CARROT_STICK).build(), 24),
        ;

        private final String name;
        private final ItemStack icon;
        private final int guiSlot;
        private final Class<? extends AbstractCosmetic> cosmeticClass;

        Default(final String name, final Class<? extends AbstractCosmetic> cosmeticClass, final ItemStack icon, final int guiSlot) {
            this.name = name;
            this.cosmeticClass = cosmeticClass;
            this.icon = icon;
            this.guiSlot = guiSlot;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public ItemStack getIcon() {
            return this.icon.clone();
        }

        @Override
        public int getGuiSlot() {
            return guiSlot;
        }

        @Override
        public Class<? extends AbstractCosmetic> getCosmeticClass() {
            return cosmeticClass;
        }
    }

}