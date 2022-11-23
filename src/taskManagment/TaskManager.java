package taskManagment;

import taskManagment.tasks.Epic;
import taskManagment.tasks.Subtask;
import taskManagment.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TaskManager {

     //String[] status = {"NEW", "IN_PROGRESS", "DONE"};
     public ArrayList<String> getAllSubtasksStatusOfEpic(int epicId) {
        ArrayList<String> statuses = new ArrayList<>();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        for (int id : epic.getSubtaskIds()) {
            statuses.add(subtasks.get(id).getStatus());
        }

        return statuses;
    }

    private final HashMap<Integer, Task> simpleTasks = new HashMap<>();

    private final HashMap<Integer, Epic> epics = new HashMap<>();

    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int generatorId = -1;


    public ArrayList<Task> getSimpleTasks(){
        return new ArrayList<>(this.simpleTasks.values());
    }

    public ArrayList<Epic> getEpics(){
        return new ArrayList<>(this.epics.values());
    }

    public ArrayList<Subtask> getEpicSubtasks(int epicId) {
        ArrayList<Subtask> tasks = new ArrayList<>();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        for (int id : epic.getSubtaskIds()) {
            tasks.add(subtasks.get(id));
        }
        return tasks;
    }

    public Task getSimpleTask(int id) {
        return simpleTasks.get(id);
    }
    public Epic getEpic(int id) {
        return epics.get(id);
    }
    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public int addNewTask(Task task) {
        final int id = ++generatorId;
        task.setId(id);
        task.setStatus("NEW");
        simpleTasks.put(id, task);
        return id;
    }
    public int addNewEpic(Epic epic) {
        final int id = ++generatorId;
        epic.setId(id);
        epic.setStatus("NEW");
        epics.put(id, epic);
        return id;
    }

    public Integer addNewSubtask(Subtask subtask) {
        Epic epic = (Epic) getEpic(subtask.getEpicId());
        if (epic == null) {
            System.out.println("No epic:" + subtask.getEpicId());
            return -1;
        }
        final int id = ++generatorId;
        subtask.setId(id);
        subtask.setStatus("NEW");
        epic.addSubtaskId(subtask.getId());
        subtasks.put(subtask.getId(), subtask);
        return subtask.getId();
    }

    public void updateSimpleTask(Task task) {
        simpleTasks.put(task.getId(), task);
    }


   public void updateEpic(Epic epic) {
       epics.put(epic.getId(), epic);
       ArrayList<String> statusesOfSub = new ArrayList<>();
       statusesOfSub = getAllSubtasksStatusOfEpic(epic.getId());
       if (epic.getSubtaskIds() == null || statusesOfSub.contains("NEW")
               && !statusesOfSub.contains("IN_PROGRESS")
               && !statusesOfSub.contains("DONE")) {
            epic.setStatus("NEW");
        } else if (!statusesOfSub.contains("NEW")
               && !statusesOfSub.contains("IN_PROGRESS")
               && statusesOfSub.contains("DONE")) {
           epic.setStatus("DONE");
       } else {
           epic.setStatus("IN_PROGRESS");
       }

   }
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
    }

    public void deleteSimpleTask(int id) {
        simpleTasks.remove(id);
    }

    public void deleteEpic(int epicId) {

        Epic epic = epics.get(epicId);
        for (int id : epic.getSubtaskIds()) {
            subtasks.remove(subtasks.get(id));
        }
        epics.remove(epicId);
    }

    public void deleteSubtask(int id) {
        subtasks.remove(id);
    }

    public void deleteAllSimpleTasks() {
        simpleTasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
    }
}
