package fr.hyriode.cosmetics.task.node;

import fr.hyriode.cosmetics.HyriCosmeticsPlugin;
import fr.hyriode.cosmetics.task.CosmeticTask;
import fr.hyriode.cosmetics.task.TaskProvider;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskNodeImpl extends BukkitRunnable implements TaskNode {

    private final TaskProvider provider;
    private final List<UUID> tasksUUID;

    public TaskNodeImpl(TaskProvider provider) {
        this.provider = provider;
        this.tasksUUID = new ArrayList<>();
    }

    @Override
    public void init() {
        this.runTaskTimer(HyriCosmeticsPlugin.getPlugin(HyriCosmeticsPlugin.class), 0, 1L);
    }

    @Override
    public void stop() {
        this.cancel();
        provider.getNodes().remove(this);
    }

    @Override
    public void run() {
        getCosmeticTasks().forEach(CosmeticTask::run);
    }

    @Override
    public List<CosmeticTask> getCosmeticTasks() {
        List<CosmeticTask> result = new ArrayList<>();
        tasksUUID.forEach(uuid -> result.add(provider.getTask(uuid)));
        return result;
    }

    @Override
    public List<UUID> getTasksUUID() {
        return tasksUUID;
    }
}
