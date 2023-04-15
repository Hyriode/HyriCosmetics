package fr.hyriode.cosmetics.task;

import java.util.UUID;

public interface TaskProvider {

    TaskNode initTaskNode(TaskNode taskNode);

    boolean removeTaskNode(UUID nodeTaskUUID);

    void startTask(Task task);

    void endTask(Task task);

    void shutdown();

    void stopTask(Task task);
}
