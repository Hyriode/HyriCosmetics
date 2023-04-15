package fr.hyriode.cosmetics.task;

import java.util.UUID;

public class TaskNode {

    private final UUID uuid;
    private final Runnable runnable;

    public TaskNode(final Runnable runnable) {
        this.uuid = UUID.randomUUID();
        this.runnable = runnable;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void run() {
        this.runnable.run();
    }
}
