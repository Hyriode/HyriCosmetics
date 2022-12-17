package fr.hyriode.cosmetics.task;

import fr.hyriode.cosmetics.task.node.TaskNode;

import java.util.List;
import java.util.UUID;

public interface TaskProvider {

    CosmeticTask execute(Runnable task);

    void remove(CosmeticTask task);

    List<TaskNode> getNodes();

    List<CosmeticTask> getTasks();

    CosmeticTask getTask(UUID uuid);

    void removeNode(TaskNode node);
}