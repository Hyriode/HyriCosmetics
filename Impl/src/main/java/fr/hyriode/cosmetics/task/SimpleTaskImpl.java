package fr.hyriode.cosmetics.task;

import fr.hyriode.cosmetics.HyriCosmeticsPlugin;
import fr.hyriode.cosmetics.user.task.CosmeticTask;
import fr.hyriode.cosmetics.user.task.SimpleTask;
import fr.hyriode.cosmetics.user.task.TaskProvider;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimpleTaskImpl extends BukkitRunnable implements SimpleTask {

    private final TaskProvider provider;
    private final List<UUID> tasksUUID;

    public SimpleTaskImpl(TaskProvider provider) {
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
