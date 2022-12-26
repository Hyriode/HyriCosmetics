package fr.hyriode.cosmetics.task;

import java.util.List;
import java.util.UUID;

public interface SimpleTask extends Runnable {

    void init();

    void stop();

    List<CosmeticTask> getCosmeticTasks();

    List<UUID> getTasksUUID();
}