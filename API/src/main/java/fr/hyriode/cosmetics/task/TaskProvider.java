package fr.hyriode.cosmetics.task;

import java.util.List;
import java.util.UUID;

public interface TaskProvider {

    CosmeticTask execute(Runnable task);

    void remove(CosmeticTask task);

    List<SimpleTask> getNodes();

    List<CosmeticTask> getTasks();

    CosmeticTask getTask(UUID uuid);

    void removeNode(SimpleTask node);
}
