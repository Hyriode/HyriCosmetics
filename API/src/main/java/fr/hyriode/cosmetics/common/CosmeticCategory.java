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

public interface CosmeticCategory {

    String getName();

    default HyriLanguageMessage getTranslatedName() {
        return HyriLanguageMessage.get("cosmetic.type." + this.getName() + ".name");
    }

    ItemBuilder getIcon();

    Class<? extends CosmeticManager> getManager();

    enum Default implements CosmeticCategory {

        GADGET("gadget", new ItemBuilder(Material.CARROT_STICK), GadgetManager.class),
        PET("pet", new ItemBuilder(Material.BONE), PetManager.class),
        MOUNT("mount", new ItemBuilder(Material.SADDLE), MountManager.class),
        PARTICLE("particle", new ItemBuilder(Material.BLAZE_POWDER), ParticleManager.class),
        ARMOR("armor", new ItemBuilder(Material.LEATHER_CHESTPLATE).withLeatherArmorColor(Color.AQUA), ArmorManager.class),
        COMPLEX("complex", new ItemBuilder(Material.CAULDRON), ComplexManager.class);
        ;

        private final String name;
        private final ItemBuilder icon;
        private final Class<? extends CosmeticManager> manager;

        Default(String name, ItemBuilder icon, Class<? extends CosmeticManager> manager) {
            this.name = name;
            this.icon = icon;
            this.manager = manager;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public ItemBuilder getIcon() {
            return this.icon;
        }

        @Override
        public Class<? extends CosmeticManager> getManager() {
            return manager;
        }
    }

}