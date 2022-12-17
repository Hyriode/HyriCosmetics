package fr.hyriode.cosmetics.cosmetic.mesh.armorstand;

import fr.hyriode.cosmetics.cosmetic.CosmeticElement;
import fr.hyriode.cosmetics.cosmetic.mesh.MeshEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

public class MeshArmorStand<T extends ArmorStand> extends MeshEntity<T> {

    protected MeshArmorStand(T armorStand, CosmeticElement<MeshEntity<T>> element) {
        super(armorStand, element);
    }

    public void rotate(BodyPart bodyPart, EulerAngle angle) {

    }

}