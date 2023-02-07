package service;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage implements Manager { // Класс для хранения всей необходимой информации задач, эпиков, подзадач
    private final Map<Integer, Task> tasks = new HashMap<>(); // Присвоение соответствия между идентификатором и задачей
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private int id = 0;

    @Override
    public void addTask(Task task) { // Метод по добавлению задач в мапу
        id++;
        task.setId(id);
        tasks.put(id, task);
    }

    @Override
    public void addEpic(Epic epic) { // Метод по добавлению эпиков в мапу
        id++;
        epic.setId(id);
        epics.put(id, epic);
    }

    @Override
    public void addSubtask(Subtask subtask) { // Метод по добавлению подзадач в мапу
        id++;
        subtask.setId(id);
        subtasks.put(id, subtask);
        int idEpic = subtask.getIdEpic();
        Epic epic = epics.get(idEpic);
        List<Subtask> subtaskEpic = epic.getSubtaskEpic();
        subtaskEpic.add(subtask);
        epic.setSubtaskEpic(subtaskEpic);
        checkStatusEpic(epic);
    }

    @Override
    public List<Subtask> getListSubtaskEpic(int id) { // Метод для получения списка подзадач в эпике
        return epics.get(id).getSubtaskEpic();
    }

    @Override
    public void deleteAllTask() { // Метод по удалению всех задач из мапы
        if (!tasks.isEmpty()) {
            tasks.clear();
        }
    }

    @Override
    public void deleteAllEpic() { // Метод по удалению всех эпиков
        if (!epics.isEmpty()) {
            epics.clear();
            deleteAllSubtask();
        }
    }

    @Override
    public void deleteAllSubtask() { // Метод по удалению всех подзадач
        if (!subtasks.isEmpty()) {
            subtasks.clear();
            for (Epic epic : epics.values()) {
                epic.getSubtaskEpic().clear();
                checkStatusEpic(epic);
            }
        }
    }

    @Override
    public void deleteTaskID(int id) { // Метод по удалению определенной задачи
        tasks.remove(id);
    }

    @Override
    public void deleteEpicID(int id) { // Метод по удалению определенного эпика
        List<Subtask> subtaskEpic = epics.get(id).getSubtaskEpic();
        for (Subtask subtask : subtaskEpic) {
            int idSubtask = subtask.getId();
            subtasks.remove(idSubtask);
        }
        epics.remove(id);
    }

    @Override
    public void deleteSubtaskID(int id) { // Метод по удалению определенной подзадачи
        Subtask subtask = subtasks.get(id);
        subtasks.remove(id);
        checkStatusEpic(epics.get(subtask.getIdEpic()));
    }

    @Override
    public void checkStatusEpic(Epic epic) {
        List<Subtask> subtaskEpic = epic.getSubtaskEpic();
        int subtaskNew = 0;
        int subtaskInProgress = 0;
        int subtaskDone = 0;
        for (Subtask subtask : subtaskEpic) {
            switch (subtask.getStatus()) {
                case NEW:
                    ++subtaskNew;
                    break;
                case IN_PROGRESS:
                    ++subtaskInProgress;
                    break;
                case DONE:
                    ++subtaskDone;
            }
        }
        if (subtaskInProgress == 0 && subtaskDone == 0) {
            epic.setStatus(Status.NEW);
        } else if (!subtaskEpic.isEmpty() && subtaskNew == 0 && subtaskInProgress == 0) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    @Override
    public Task renewalTask(Task task) { // Методы по обновлению задач, подзадач, эпиков
        if (!tasks.containsKey(id)) {
            return null;
        }
        tasks.put(id, task);
        return task;
    }

    @Override
    public Epic renewalEpic(Epic epic) {
        if (!epics.containsKey(id)) {
            return null;
        }
        epics.put(id, epic);
        return epic;
    }

    @Override
    public Subtask renewalSubtask(Subtask subtask) {
        if (!subtasks.containsKey(id)) {
            return null;
        }
        subtasks.put(id, subtask);
        checkStatusEpic(epics.get(subtask.getIdEpic()));
        return subtask;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }
}
