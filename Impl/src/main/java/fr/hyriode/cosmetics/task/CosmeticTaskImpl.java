package fr.hyriode.cosmetics.task;

import java.util.UUID;

public class CosmeticTaskImpl implements CosmeticTask {

    private final UUID uuid;
    private final Runnable runnable;

    private SimpleTask node;

    public CosmeticTaskImpl(final Runnable runnable) {
        this.uuid = UUID.randomUUID();
        this.runnable = runnable;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public SimpleTask getNode() {
        return this.node;
    }

    @Override
    public void assignNode(SimpleTask node) {
        this.node = node;
    }

    @Override
    public void run() {
        this.runnable.run();
    }
}