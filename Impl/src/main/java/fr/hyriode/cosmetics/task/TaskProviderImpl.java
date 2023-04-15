package fr.hyriode.cosmetics.task;

import fr.hyriode.cosmetics.HyriCosmeticsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskProviderImpl implements TaskProvider {

    private final List<Task> tasks;
    private final BukkitScheduler bukkitScheduler;

    public TaskProviderImpl() {
        this.tasks = new ArrayList<>();
        this.bukkitScheduler = Bukkit.getScheduler();
    }

    @Override
    public TaskNode initTaskNode(final TaskNode taskNode) {
        for (Task task : tasks) {
            if (task.hasSpace()) {
                return task.initTaskNode(taskNode);
            }
        }
        Task task = new TaskImpl();
        tasks.add(task);
        startTask(task);
        return task.initTaskNode(taskNode);
    }

    @Override
    public boolean removeTaskNode(final UUID nodeTaskUUID) {
        for (Task task : tasks) {
            if (task.removeTaskNode(nodeTaskUUID)) {
                if (task.isEmpty()) {
                    this.endTask(task);
                    tasks.remove(task);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void startTask(final Task task) {
        final BukkitTask bukkitTask = bukkitScheduler.runTaskTimer(HyriCosmeticsPlugin.getPlugin(HyriCosmeticsPlugin.class), task, 0, 1L);
        task.setTaskId(bukkitTask.getTaskId());
    }

    @Override
    public void endTask(final Task task) {
        this.stopTask(task);
        this.bukkitScheduler.cancelTask(task.getTaskId());
    }

    @Override
    public void shutdown() {
        this.bukkitScheduler.cancelTasks(HyriCosmeticsPlugin.getPlugin(HyriCosmeticsPlugin.class));
    }

    @Override
    public void stopTask(final Task task) {
        task.stop();
    }
}