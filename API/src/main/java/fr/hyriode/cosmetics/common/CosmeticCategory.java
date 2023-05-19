package fr.hyriode.cosmetics.common;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.cosmetics.balloon.AbstractBalloon;
import fr.hyriode.cosmetics.gadget.AbstractGadget;
import fr.hyriode.cosmetics.particle.AbstractParticle;
import fr.hyriode.cosmetics.pet.AbstractPet;
import fr.hyriode.cosmetics.transaction.CosmeticPrice;
import fr.hyriode.cosmetics.utils.Head;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static fr.hyriode.cosmetics.transaction.CosmeticPrice.*;

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

    Map<CosmeticRarity, CosmeticPrice> getDefaultPrices();

    default CosmeticPrice getDefaultPrice(CosmeticRarity rarity) {
        return this.getDefaultPrices().getOrDefault(rarity, new CosmeticPrice());
    }

    enum Default implements CosmeticCategory {

        PARTICLE("particle", AbstractParticle.class, new ItemBuilder(Material.BLAZE_POWDER).build(), 21, prices -> {
            prices.put(CosmeticRarity.COMMON, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 3000)
                    .newValue(Currency.HYODES, 100));

            prices.put(CosmeticRarity.RARE, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 7000)
                    .newValue(Currency.HYODES, 175));

            prices.put(CosmeticRarity.EPIC, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 11500)
                    .newValue(Currency.HYODES, 250));

            prices.put(CosmeticRarity.LEGENDARY, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 18000)
                    .newValue(Currency.HYODES, 350));
        }),

        PET("pet", AbstractPet.class, new ItemBuilder(Material.BONE).build(), 22, prices -> {
            prices.put(CosmeticRarity.COMMON, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 7500)
                    .newValue(Currency.HYODES, 175));

            prices.put(CosmeticRarity.RARE, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 12000)
                    .newValue(Currency.HYODES, 250));

            prices.put(CosmeticRarity.EPIC, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 25000)
                    .newValue(Currency.HYODES, 450));

            prices.put(CosmeticRarity.LEGENDARY, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 50000)
                    .newValue(Currency.HYODES, 650));
        }),

        BALLOON("balloon", AbstractBalloon.class, Head.BALLOON_COLOR_RED.asItem(), 23, prices -> {
            prices.put(CosmeticRarity.COMMON, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 2000)
                    .newValue(Currency.HYODES, 75));

            prices.put(CosmeticRarity.RARE, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 5000)
                    .newValue(Currency.HYODES, 150));

            prices.put(CosmeticRarity.EPIC, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 10000)
                    .newValue(Currency.HYODES, 220));

            prices.put(CosmeticRarity.LEGENDARY, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 15000)
                    .newValue(Currency.HYODES, 300));
        }),

        GADGET("gadget", AbstractGadget.class, new ItemBuilder(Material.CARROT_STICK).build(), 24, prices -> {
            prices.put(CosmeticRarity.COMMON, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 5500)
                    .newValue(Currency.HYODES, 150));

            prices.put(CosmeticRarity.RARE, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 9000)
                    .newValue(Currency.HYODES, 230));

            prices.put(CosmeticRarity.EPIC, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 14500)
                    .newValue(Currency.HYODES, 350));

            prices.put(CosmeticRarity.LEGENDARY, new CosmeticPrice()
                    .newValue(Currency.HYRIS, 22000)
                    .newValue(Currency.HYODES, 450));
        }),

        ;

        private final String name;
        private final ItemStack icon;
        private final int guiSlot;
        private final Class<? extends AbstractCosmetic> cosmeticClass;

        private final Map<CosmeticRarity, CosmeticPrice> defaultPrices = new HashMap<>();

        Default(final String name, final Class<? extends AbstractCosmetic> cosmeticClass, final ItemStack icon, final int guiSlot, final Consumer<Map<CosmeticRarity, CosmeticPrice>> pricesConsumer) {
            this.name = name;
            this.cosmeticClass = cosmeticClass;
            this.icon = icon;
            this.guiSlot = guiSlot;

            pricesConsumer.accept(this.defaultPrices);
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

        @Override
        public Map<CosmeticRarity, CosmeticPrice> getDefaultPrices() {
            return this.defaultPrices;
        }

    }

}