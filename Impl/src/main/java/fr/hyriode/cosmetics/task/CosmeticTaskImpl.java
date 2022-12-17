package fr.hyriode.cosmetics.task;

import fr.hyriode.cosmetics.task.node.TaskNode;

import java.util.UUID;

public class CosmeticTaskImpl implements CosmeticTask {

    private final UUID uuid;
    private final Runnable runnable;

    private TaskNode node;

    public CosmeticTaskImpl(final Runnable runnable) {
        this.uuid = UUID.randomUUID();
        this.runnable = runnable;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public TaskNode getNode() {
        return this.node;
    }

    @Override
    public void assignNode(TaskNode node) {
        this.node = node;
    }

    @Override
    public void run() {
        this.runnable.run();
    }

}