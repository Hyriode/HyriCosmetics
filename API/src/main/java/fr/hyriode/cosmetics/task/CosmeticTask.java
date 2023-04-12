package fr.hyriode.cosmetics.task;

import java.util.UUID;

public interface CosmeticTask {

    UUID getUUID();

    SimpleTask getNode();

    void assignNode(SimpleTask node);

    void run();

}
