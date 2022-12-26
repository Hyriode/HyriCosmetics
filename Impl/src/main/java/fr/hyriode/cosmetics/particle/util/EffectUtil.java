package fr.hyriode.cosmetics.particle.util;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class EffectUtil {

    public static void particle(EnumParticle effect, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(effect, true, x, y, z, offsetX, offsetY, offsetZ, speed, amount, new int[0]);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getLocation().distance(new Location(player.getWorld(), x, y, z)) <= 70.0D && ((CraftPlayer) player).getHandle() != null) {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

}
