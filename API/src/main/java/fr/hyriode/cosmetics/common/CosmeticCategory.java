package fr.hyriode.cosmetics.common;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.cosmetics.armor.AbstractArmor;
import fr.hyriode.cosmetics.gadget.AbstractGadget;
import fr.hyriode.cosmetics.mount.AbstractMount;
import fr.hyriode.cosmetics.particle.AbstractParticle;
import fr.hyriode.cosmetics.pet.AbstractPet;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.Color;
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

        GADGET("gadget", AbstractGadget.class, new ItemBuilder(Material.CARROT_STICK).build(), 20),
        PET("pet", AbstractPet.class, new ItemBuilder(Material.BONE).build(), 21),
        MOUNT("mount", AbstractMount.class, new ItemBuilder(Material.SADDLE).build(), 22),
        PARTICLE("particle", AbstractParticle.class, new ItemBuilder(Material.BLAZE_POWDER).build(), 23),
        ARMOR("armor", AbstractArmor.class, new ItemBuilder(Material.LEATHER_CHESTPLATE).withLeatherArmorColor(Color.AQUA).withAllItemFlags().build(), 24);
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