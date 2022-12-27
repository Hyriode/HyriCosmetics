package fr.hyriode.cosmetics.task;

import fr.hyriode.cosmetics.user.task.CosmeticTask;
import fr.hyriode.cosmetics.user.task.SimpleTask;
import fr.hyriode.cosmetics.user.task.TaskProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskProviderImpl implements TaskProvider {

    private final List<SimpleTask> nodes;
    private final List<CosmeticTask> tasks;

    public TaskProviderImpl() {
        this.nodes = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    @Override
    public CosmeticTask execute(Runnable task) {
        final CosmeticTask cosmeticTask = new CosmeticTaskImpl(task);
        this.tasks.add(cosmeticTask);

        if (getAvailableNodes().isEmpty()) {
            final SimpleTask node = new SimpleTaskImpl(this);
            this.nodes.add(node);
            cosmeticTask.assignNode(node);
            node.getTasksUUID().add(cosmeticTask.getUUID());
            node.init();
        } else {
            cosmeticTask.assignNode(getAvailableNodes().get(0));
            getAvailableNodes().get(0).getTasksUUID().add(cosmeticTask.getUUID());
        }

        return cosmeticTask;
    }

    @Override
    public void remove(CosmeticTask task) {
        this.tasks.remove(task);
        for (SimpleTask node : this.nodes) {
            if (node.getTasksUUID().contains(task.getUUID())) {
                node.getTasksUUID().remove(task.getUUID());
                if (node.getTasksUUID().isEmpty()) {
                    node.stop();
                }
            }
        }
    }

    public List<SimpleTask> getAvailableNodes() {
        return this.nodes.stream().filter(taskNode -> taskNode.getCosmeticTasks().size() < 10).collect(Collectors.toList());
    }

    @Override
    public List<SimpleTask> getNodes() {
        return this.nodes;
    }

    @Override
    public List<CosmeticTask> getTasks() {
        return tasks;
    }

    @Override
    public CosmeticTask getTask(UUID uuid) {
        return this.tasks.stream().filter(cosmeticTask -> cosmeticTask.getUUID() == uuid).findFirst().orElse(null);
    }

    @Override
    public void removeNode(SimpleTask node) {
        this.nodes.remove(node);
    }
}