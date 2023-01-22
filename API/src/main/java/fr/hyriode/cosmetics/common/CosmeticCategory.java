package fr.hyriode.cosmetics.common;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.cosmetics.armor.ArmorManager;
import fr.hyriode.cosmetics.complex.ComplexManager;
import fr.hyriode.cosmetics.gadget.GadgetManager;
import fr.hyriode.cosmetics.mount.MountManager;
import fr.hyriode.cosmetics.particle.ParticleManager;
import fr.hyriode.cosmetics.pet.PetManager;
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

    Class<? extends CosmeticManager> getManager();

    int getGuiSlot();

    enum Default implements CosmeticCategory {

        GADGET("gadget", new ItemBuilder(Material.CARROT_STICK).build(), GadgetManager.class, 20),
        PET("pet", new ItemBuilder(Material.BONE).build(), PetManager.class, 21),
        MOUNT("mount", new ItemBuilder(Material.SADDLE).build(), MountManager.class, 22),
        PARTICLE("particle", new ItemBuilder(Material.BLAZE_POWDER).build(), ParticleManager.class, 23),
        ARMOR("armor", new ItemBuilder(Material.LEATHER_CHESTPLATE).withLeatherArmorColor(Color.AQUA).withAllItemFlags().build(), ArmorManager.class, 24),
        COMPLEX("complex", new ItemBuilder(Material.GOLDEN_APPLE).withGlow().withAllItemFlags().build(), ComplexManager.class, 31);
        ;

        private final String name;
        private final ItemStack icon;
        private final Class<? extends CosmeticManager<?>> manager;
        private final int guiSlot;

        Default(final String name, final ItemStack icon, final Class<? extends CosmeticManager<?>> manager, final int guiSlot) {
            this.name = name;
            this.icon = icon;
            this.manager = manager;
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
        public Class<? extends CosmeticManager> getManager() {
            return manager;
        }

        @Override
        public int getGuiSlot() {
            return guiSlot;
        }
    }

}