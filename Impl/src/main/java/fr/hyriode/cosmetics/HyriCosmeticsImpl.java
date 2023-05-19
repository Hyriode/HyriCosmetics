package fr.hyriode.cosmetics;

import fr.hyriode.api.HyriAPI;
import fr.hyriode.api.server.ILobbyAPI;
import fr.hyriode.cosmetics.balloon.BalloonHook;
import fr.hyriode.cosmetics.balloon.BalloonImpl;
import fr.hyriode.cosmetics.common.*;
import fr.hyriode.cosmetics.listener.AccountListener;
import fr.hyriode.cosmetics.listener.PlayerListener;
import fr.hyriode.cosmetics.particle.effect.*;
import fr.hyriode.cosmetics.pet.pets.GhostPet;
import fr.hyriode.cosmetics.pet.pets.MiniMePet;
import fr.hyriode.cosmetics.pet.pets.ReaperPet;
import fr.hyriode.cosmetics.pet.pets.SnowManComplexPet;
import fr.hyriode.cosmetics.registry.CosmeticRegistryImpl;
import fr.hyriode.cosmetics.task.MainTask;
import fr.hyriode.cosmetics.task.TaskProvider;
import fr.hyriode.cosmetics.task.TaskProviderImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.user.CosmeticUserProvider;
import fr.hyriode.cosmetics.user.CosmeticUserProviderImpl;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HyriCosmeticsImpl extends HyriCosmetics {

    private final boolean lobbyServer = HyriAPI.get().getServer().getType().equals(ILobbyAPI.TYPE) || HyriAPI.get().getConfig().isDevEnvironment();

    private TaskProvider taskProvider;
    private CosmeticUserProvider userProvider;
    private CosmeticRegistryImpl registry;

    public HyriCosmeticsImpl() {
        this.preInit();
        this.init();
        this.postInit();
    }

    private void preInit() {
        System.out.println("Pre-initializing " + NAME + "...");

        BalloonHook.hook();
    }

    private void init() {
        System.out.println("Initializing " + NAME + "...");

        this.taskProvider = new TaskProviderImpl();
        this.userProvider = new CosmeticUserProviderImpl();
        this.registry = new CosmeticRegistryImpl();
    }

    private void postInit() {
        System.out.println("Post-initializing " + NAME + "...");

        Bukkit.getScheduler().runTaskTimer(HyriCosmeticsPlugin.get(), new MainTask(), 0, 5L);

        HyriAPI.get().getEventBus().register(new AccountListener(this));

        if (this.lobbyServer) {
            Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), HyriCosmeticsPlugin.get());
        }

        this.registry.registerDefaults();
    }

    public void disable() {
        System.out.println("Disabling " + NAME + "...");

        this.taskProvider.shutdown();
    }

    @Override
    public CosmeticRegistry getRegistry() {
        return this.registry;
    }

    @Override
    public TaskProvider getTaskProvider() {
        return this.taskProvider;
    }

    @Override
    public CosmeticUserProvider getUserProvider() {
        return this.userProvider;
    }

    @Override
    public boolean isLobbyServer() {
        return lobbyServer;
    }

}
