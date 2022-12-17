package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.cosmetic.CosmeticElement;
import fr.hyriode.cosmetics.cosmetic.mesh.Mesh;
import fr.hyriode.cosmetics.cosmetic.mesh.MeshEntity;
import fr.hyriode.cosmetics.task.TaskProvider;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public abstract class HyriCosmetics {

    private static HyriCosmetics instance;

    public HyriCosmetics() {
        instance = this;
    }

    public abstract TaskProvider getTaskProvider();

    public static HyriCosmetics get() {
        return instance;
    }

}