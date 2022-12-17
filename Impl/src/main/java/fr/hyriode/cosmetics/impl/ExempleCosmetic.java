package fr.hyriode.cosmetics.impl;

import fr.hyriode.cosmetics.cosmetic.Cosmetic;
import fr.hyriode.cosmetics.cosmetic.CosmeticElementImpl;
import fr.hyriode.cosmetics.cosmetic.animation.AnimationPoint;
import fr.hyriode.cosmetics.cosmetic.mesh.MeshEntity;
import fr.hyriode.cosmetics.cosmetic.point.Point;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ExempleCosmetic extends Cosmetic {

    private final Player player;
    private final Creeper creeper;

    public ExempleCosmetic(Player player) {
        this.player = player;

        if (!player.getLocation().getChunk().isLoaded()) {
            player.getLocation().getChunk().load();
        }

        this.creeper = (Creeper) player.getWorld().spawnEntity(player.getLocation().clone().add(1, 1, 0), EntityType.CREEPER);
        this.creeper.setCustomName("§c§lCreeper");

        final CosmeticElementImpl<MeshEntity<Creeper>> element = new CosmeticElementImpl<>(this);
        final MeshEntity<Creeper> mesh = new MeshEntity<>(creeper, element);

        element.setMesh(mesh, new Point(0, 0, 0));

        this.addChild(element);
        this.addAnimation(() -> Arrays.asList(
                new AnimationPoint(1, element, new Point(0.1f, 0, 0.1f)),
                new AnimationPoint(2, element, new Point(0, 0, 0)),
                new AnimationPoint(3, element, new Point(-0.1f, 0, -0.1f)),
                new AnimationPoint(4, element, new Point(0, 0, 0))
        ));

        this.init();
    }

    @Override
    public Location getLocation() {
        return this.player.getLocation();
    }

    @Override
    public void delete() {
        super.delete();
        this.creeper.remove();
    }

    @Override
    protected void tick() {
        super.tick();
    }
}