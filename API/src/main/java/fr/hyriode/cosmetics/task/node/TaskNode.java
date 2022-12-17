package fr.hyriode.cosmetics.task.node;

import fr.hyriode.cosmetics.task.CosmeticTask;

import java.util.List;
import java.util.UUID;

public interface TaskNode extends Runnable {

    void init();

    void stop();

    List<CosmeticTask> getCosmeticTasks();

    List<UUID> getTasksUUID();
}
