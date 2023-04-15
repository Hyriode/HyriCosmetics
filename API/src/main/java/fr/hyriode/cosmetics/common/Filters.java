package fr.hyriode.cosmetics.common;

public interface Filters<T> {

    String getName();

    enum Owned implements Filters<Boolean> {
        ALL("all"),
        YES("yes"),
        NO("no");

        private final String name;

        Owned(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public static Owned getByName(String name) {
            for (Owned owned : values()) {
                if (owned.getName().equals(name)) {
                    return owned;
                }
            }
            return null;
        }
    }

    enum Rarity implements Filters {
        NO("no"),
        ASCENDING("ascending"),
        DESCENDING("descending");

        private final String name;

        Rarity(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public static Rarity getByName(String name) {
            for (Rarity rarity : values()) {
                if (rarity.getName().equals(name)) {
                    return rarity;
                }
            }
            return null;
        }
    }

    enum Price implements Filters {
        NO("no"),
        ASCENDING("ascending"),
        DESCENDING("descending");

        private final String name;

        Price(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public static Price getByName(String name) {
            for (Price price : values()) {
                if (price.getName().equals(name)) {
                    return price;
                }
            }
            return null;
        }
    }
}
