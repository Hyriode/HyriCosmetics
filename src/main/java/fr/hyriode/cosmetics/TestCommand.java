package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.api.HyriCosmetic;
import fr.hyriode.cosmetics.api.HyriCosmeticType;
import fr.hyriode.cosmetics.api.HyriCosmeticsAPI;
import fr.hyriode.cosmetics.api.particle.HyriParticleName;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 02/04/2022 at 14:48
 */
public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(args.length == 1) {

                for (HyriCosmetic hyriCosmetic : HyriCosmeticsAPI.get().getCosmeticsManager().getCosmeticsByType(HyriCosmeticType.Default.PARTICLE)) {
                    if(args[0].equalsIgnoreCase(hyriCosmetic.getId())) {
                        HyriCosmeticsAPI.get().getCosmeticsManager().clearCosmetics(p.getUniqueId());
                        HyriCosmeticsAPI.get().getCosmeticsManager().getCosmeticById(hyriCosmetic.getId()).play(p);
                    }
                }
                if(args[0].equalsIgnoreCase("reset")) {
                    HyriCosmeticsAPI.get().getCosmeticsManager().clearCosmetics(p.getUniqueId());
                }
            }
        }
        return false;
    }
}
