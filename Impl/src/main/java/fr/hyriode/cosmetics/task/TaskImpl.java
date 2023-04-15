package fr.hyriode.cosmetics.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class TaskImpl implements Task {

    private final List<TaskNode> taskNodes;
    private boolean isRunning;
    private int taskId;

    public TaskImpl() {
        this.taskNodes = new ArrayList<>();
        this.isRunning = true;
    }

    @Override
    public TaskNode initTaskNode(final TaskNode taskNode) {
        taskNodes.add(taskNode);
        return taskNode;
    }

    @Override
    public boolean removeTaskNode(UUID nodeTaskUUID) {
        Iterator<TaskNode> iterator = taskNodes.iterator();
        while (iterator.hasNext()) {
            TaskNode taskNode = iterator.next();
            if (taskNode.getUUID().equals(nodeTaskUUID)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasSpace() {
        return taskNodes.size() < 10;
    }

    @Override
    public boolean isEmpty() {
        return taskNodes.isEmpty();
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        if (isRunning) {
            this.tick();
        }
    }

    @Override
    public void tick() {
        for (TaskNode taskNode : taskNodes) {
            taskNode.run();
        }
    }

    @Override
    public List<TaskNode> getTaskNodes() {
        return taskNodes;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public int getTaskId() {
        return taskId;
    }
}