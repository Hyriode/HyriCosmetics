package fr.hyriode.cosmetics.task;

import java.util.List;
import java.util.UUID;

public interface Task extends Runnable {
    TaskNode initTaskNode(TaskNode taskNode);

    boolean removeTaskNode(UUID nodeTaskUUID);

    boolean hasSpace();

    boolean isEmpty();

    void stop();

    void tick();

    List<TaskNode> getTaskNodes();

    boolean isRunning();

    void setRunning(boolean running);

    void setTaskId(int taskId);

    int getTaskId();
}
