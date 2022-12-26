package fr.hyriode.cosmetics.task;

import java.util.UUID;

public interface CosmeticTask extends Runnable {

    UUID getUUID();

    SimpleTask getNode();

    void assignNode(SimpleTask node);

}
