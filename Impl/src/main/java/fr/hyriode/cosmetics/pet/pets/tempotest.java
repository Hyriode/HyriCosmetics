package fr.hyriode.cosmetics.pet.pets;

public class tempotest {

    double bodyRotation = 3F;

    public void tick() {
        if (bodyRotation >= 6F) {
            bodyRotation = 0;
        } else {
            bodyRotation += 0.1;
        }
    }

}
