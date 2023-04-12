package fr.hyriode.cosmetics.command;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.HyriCosmeticsPlugin;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.gui.CosmeticsGui;
import fr.hyriode.hyrame.command.HyriCommand;
import fr.hyriode.hyrame.command.HyriCommandContext;
import fr.hyriode.hyrame.command.HyriCommandInfo;
import org.bukkit.entity.Player;

public class CosmeticsCommand extends HyriCommand<HyriCosmeticsPlugin> {

    public CosmeticsCommand(HyriCosmeticsPlugin plugin) {
        super(plugin, new HyriCommandInfo("cosmetics")
                .withAliases("cosmetic", "c")
                .withDescription("Command to open the cosmetics menu")
                .withUsage("/cosmetics"));
    }

    @Override
    public void handle(HyriCommandContext ctx) {
        new CosmeticsGui((Player) ctx.getSender()).open();
    }
}
