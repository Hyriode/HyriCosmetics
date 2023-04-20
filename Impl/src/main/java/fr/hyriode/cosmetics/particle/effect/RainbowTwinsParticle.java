package fr.hyriode.cosmetics.particle.effect;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.cosmetics.algorithm.PowerConsciousHSV;
import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.particle.AbstractParticleImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.utils.MathUtil;
import fr.hyriode.hyrame.game.team.HyriGameTeamColor;
import fr.hyriode.hyrame.item.ItemBuilder;
import fr.hyriode.hyrame.utils.Pair;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.color.RegularColor;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class RainbowTwinsParticle extends AbstractParticleImpl<RegularColor> {

    private final static int orbs = 2;
    private final static double radius = 1;
    private final static int numSteps = 60;
    private final static int maxStepY = 30;

    private final PowerConsciousHSV hsv;

    private int stepX = 0;
    private int stepY = 0;
    private boolean reverse = false;

    private RegularColor color;

    public RainbowTwinsParticle(CosmeticUser user) {
        super(user, Cosmetic.RAINBOW_TWINS, true);
        this.hsv = new PowerConsciousHSV();
        this.updateVariant();
    }

    @Override
    public void tick(CosmeticUser user) {
        if (isMoving()) {
            if (variant != null && variant.equals("rainbow")) {
                new ParticleBuilder(ParticleEffect.REDSTONE, getLocation().clone().add(0, 0.8, 0))
                        .setAmount(4)
                        .setSpeed(0)
                        .setParticleData(new RegularColor(hsv.run()))
                        .display();
            } else {
                new ParticleBuilder(ParticleEffect.REDSTONE, getLocation().clone().add(0, 0.8, 0))
                        .setAmount(4)
                        .setSpeed(0)
                        .setParticleData(color)
                        .display();
            }
            return;
        }
        this.stepX++;
        if (this.stepX > numSteps) {
            this.stepX = 0;
        }

        if (this.reverse) {
            this.stepY++;
            if (this.stepY > maxStepY)
                this.reverse = false;
        } else {
            this.stepY--;
            if (this.stepY < -maxStepY)
                this.reverse = true;
        }

        for (int i = 0; i < orbs; i++) {
            double slice = Math.PI * 2 / numSteps;
            double orbSlice = Math.PI * 2 / orbs;

            double dx = -MathUtil.cos(slice * this.stepX + orbSlice * i) * radius;
            double dy = (this.stepY / (double) maxStepY);
            double dz = -MathUtil.sin(slice * this.stepX + orbSlice * i) * radius;

            if (variant != null && variant.equals("rainbow")) {
                new ParticleBuilder(ParticleEffect.REDSTONE, getLocation().clone().add(dx, dy + 0.8, dz))
                        .setAmount(4)
                        .setSpeed(0)
                        .setParticleData(new RegularColor(hsv.run()))
                        .display();
            } else {
                new ParticleBuilder(ParticleEffect.REDSTONE, getLocation().clone().add(dx, dy + 0.8, dz))
                        .setAmount(4)
                        .setSpeed(0)
                        .setParticleData(color)
                        .display();
            }
        }
    }

    @Override
    public String getDefaultVariant() {
        return "rainbow";
    }

    @Override
    public void updateVariant() {
        color = (RegularColor) getVariants().get(variant);
    }

    @Override
    public Map<String, Pair<ItemStack, RegularColor>> initVariants() {
        final Map<String, Pair<ItemStack, RegularColor>> resultMap = new LinkedHashMap<>();
        resultMap.put("rainbow", new Pair<>(new ItemBuilder(Material.DOUBLE_PLANT)
                .withName(HyriLanguageMessage.get("gui.cosmetic.variant.color.rainbow").getValue(getPlayer()))
                .withData((short) 0)
                .build(), new RegularColor(Color.RED)));

        for (HyriGameTeamColor color : HyriGameTeamColor.values()) {
            this.addVariantColor(resultMap, color);
        }
        return resultMap;
    }

    @SuppressWarnings("deprecation")
    private void addVariantColor(Map<String, Pair<ItemStack, RegularColor>> variantsMap, HyriGameTeamColor color) {
        variantsMap.put(color.name().toLowerCase(),
                new Pair<>(
                        new ItemBuilder(Material.INK_SACK).withName(color.getChatColor() + color.getDisplay().getValue(getPlayer())).withData(color.getDyeColor().getDyeData()).build(),
                        new RegularColor(new Color(color.getDyeColor().getColor().asRGB()))
                )
        );
    }
}
