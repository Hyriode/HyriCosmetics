package fr.hyriode.cosmetics.task;

import fr.hyriode.cosmetics.task.node.TaskNode;

import java.util.UUID;

public interface CosmeticTask extends Runnable {

    UUID getUUID();

    TaskNode getNode();

    void assignNode(TaskNode node);

}
