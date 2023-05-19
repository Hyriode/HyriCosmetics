package fr.hyriode.cosmetics.registry;

import fr.hyriode.cosmetics.balloon.BalloonImpl;
import fr.hyriode.cosmetics.common.*;
import fr.hyriode.cosmetics.particle.effect.*;
import fr.hyriode.cosmetics.pet.pets.GhostPet;
import fr.hyriode.cosmetics.pet.pets.MiniMePet;
import fr.hyriode.cosmetics.pet.pets.ReaperPet;
import fr.hyriode.cosmetics.pet.pets.SnowManComplexPet;
import fr.hyriode.cosmetics.user.CosmeticUser;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by AstFaster
 * on 19/05/2023 at 13:47
 */
public class CosmeticRegistryImpl implements CosmeticRegistry {

    private final Map<CosmeticCategory, List<CosmeticInfo>> categories = new HashMap<>();

    private final Map<String, CosmeticInfo> infos = new HashMap<>();
    @SuppressWarnings("all")
    private final Map<String, Function<CosmeticUser, AbstractCosmetic>> handlers = new HashMap<>();

    public void registerDefaults() {
        System.out.println("Registering default cosmetics...");

        // == Particles ==
        this.registerCosmetic(DefaultCosmetics.ENCHANTED, EnchantedParticle::new);
        this.registerCosmetic(DefaultCosmetics.FIRE_INVOCATION, InvocationParticle.FireInvocationParticle::new);
        this.registerCosmetic(DefaultCosmetics.GEM_INVOCATION, InvocationParticle.GemInvocationParticle::new);
        this.registerCosmetic(DefaultCosmetics.PORTAL_INVOCATION, InvocationParticle.PortalInvocationParticle::new);
        this.registerCosmetic(DefaultCosmetics.STEP_IN_THE_AIR, StepInTheAirParticle::new);
        this.registerCosmetic(DefaultCosmetics.RAINBOW_TWINS, RainbowTwinsParticle::new);
        this.registerCosmetic(DefaultCosmetics.BLACK_VORTEX, BlackVortexParticle::new);

        // == Pets ==
        this.registerCosmetic(DefaultCosmetics.SNOWMAN, SnowManComplexPet::new);
        this.registerCosmetic(DefaultCosmetics.MINI_ME, MiniMePet::new);
        this.registerCosmetic(DefaultCosmetics.REAPER, ReaperPet::new);
        this.registerCosmetic(DefaultCosmetics.GHOST, GhostPet::new);

        // == Balloons ==
        for (CosmeticInfo balloon : DefaultCosmetics.BALLOONS) {
            this.registerCosmetic(balloon, user -> new BalloonImpl(user, balloon));
        }
    }

    @SuppressWarnings("all")
    @Override
    public void registerCosmetic(CosmeticInfo cosmeticInfo, Function<CosmeticUser, AbstractCosmetic> handlerProvider) {
        this.infos.put(cosmeticInfo.getId(), cosmeticInfo);
        this.handlers.put(cosmeticInfo.getId(), handlerProvider);
        this.categories.merge(cosmeticInfo.getCategory(), new ArrayList<>(), (oldValue, newValue) -> oldValue).add(cosmeticInfo);
    }

    @Override
    public CosmeticInfo getCosmetic(String id) {
        return this.infos.get(id);
    }

    @SuppressWarnings("all")
    @Override
    public <T extends AbstractCosmetic> T createCosmetic(String id, CosmeticUser user) {
        return (T) this.handlers.get(id).apply(user);
    }

    @Override
    public Map<CosmeticCategory, List<CosmeticInfo>> getCosmetics() {
        return this.categories;
    }

    @Override
    public List<CosmeticInfo> getCosmetics(CosmeticCategory category) {
        return this.categories.get(category);
    }

    @Override
    public List<CosmeticInfo> getFilteredCosmetics(CosmeticUser user, CosmeticCategory category) {
        final List<CosmeticInfo> ownedCosmetics = this.getCosmetics().get(category).stream().filter(user::hasUnlockedCosmetic).collect(Collectors.toList());
        final List<CosmeticInfo> noOwnedCosmetics = this.getCosmetics().get(category).stream().filter(cosmetic -> !user.hasUnlockedCosmetic(cosmetic)).collect(Collectors.toList());

        CosmeticRarity.sortCosmeticsByRarity(ownedCosmetics);
        CosmeticRarity.sortCosmeticsByRarity(noOwnedCosmetics);

        final List<CosmeticInfo> result = new ArrayList<>(ownedCosmetics.size() + noOwnedCosmetics.size());

        result.addAll(ownedCosmetics);
        result.addAll(noOwnedCosmetics);

        return result;
    }

    @Override
    public Set<CosmeticCategory> getCategories() {
        return this.categories.keySet();
    }

}
