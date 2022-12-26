package fr.hyriode.cosmetics.complex.mesh.armorstand;

import fr.hyriode.cosmetics.complex.ComplexCosmeticElement;
import fr.hyriode.cosmetics.complex.mesh.MeshEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

public class MeshArmorStand<T extends ArmorStand> extends MeshEntity<T> {

    protected MeshArmorStand(T armorStand, ComplexCosmeticElement<MeshEntity<T>> element) {
        super(armorStand, element);
    }

    public void rotate(BodyPart bodyPart, EulerAngle angle) {

    }

}