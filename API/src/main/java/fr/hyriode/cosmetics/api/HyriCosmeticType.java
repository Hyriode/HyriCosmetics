package fr.hyriode.cosmetics.api;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 01/04/2022 at 20:40
 */
public interface HyriCosmeticType {

    String getName();

    enum Default implements HyriCosmeticType {

        GADGET("gadget"),
        PET("pet"),
        MOUNT("mount"),
        PARTICLE("particle"),
        MORPH("morph"),
        ARMOR("armor"),
        ;

        private final String name;

        Default(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
