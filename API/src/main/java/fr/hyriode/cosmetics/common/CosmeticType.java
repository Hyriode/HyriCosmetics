package fr.hyriode.cosmetics.common;

public interface CosmeticType {

    String getName();

    enum Default implements CosmeticType {

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