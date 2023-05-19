package fr.hyriode.cosmetics;

import fr.hyriode.hyrame.HyrameLoader;
import fr.hyriode.hyrame.IHyrame;
import fr.hyriode.hyrame.plugin.IPluginProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class HyriCosmeticsPlugin extends JavaPlugin implements IPluginProvider {

    private static final String PACKAGE = "fr.hyriode.cosmetics";

    private static HyriCosmeticsPlugin instance;

    private IHyrame hyrame;
    private HyriCosmeticsImpl cosmetics;

    @Override
    public void onEnable() {
        instance = this;

        this.hyrame = HyrameLoader.load(this);
        this.cosmetics = new HyriCosmeticsImpl();
    }

    @Override
    public void onDisable() {
        this.cosmetics.disable();
    }

    public static HyriCosmeticsPlugin get() {
        return instance;
    }

    public IHyrame getHyrame() {
        return this.hyrame;
    }

    @Override
    public JavaPlugin getPlugin() {
        return this;
    }

    @Override
    public String getId() {
        return "cosmetics";
    }

    @Override
    public String[] getCommandsPackages() {
        return new String[]{PACKAGE};
    }

    @Override
    public String[] getListenersPackages() {
        return new String[]{PACKAGE};
    }

    @Override
    public String[] getItemsPackages() {
        return new String[]{PACKAGE};
    }

    @Override
    public String getLanguagesPath() {
        return "/lang/";
    }

}
