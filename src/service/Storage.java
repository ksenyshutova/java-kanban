package service;

import model.Epic;
import model.Subtask;
import model.Task;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage implements Manager { // Класс для хранения всей необходимой информации задач, эпиков, подзадач
    private final Map<Integer, Task> tasks = new HashMap<>(); // Присвоение соответствия между идентификатором и задачей
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private int id=0;

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
    }

        @Override
        public List<Subtask> getListSubtaskEpic(int id) { // Метод для получения списка подзадач в эпике
            return epics.get(id).getSubtaskEpic();
        }

    @Override
    public void deleteAllTask() { // Метод по удалению всех задач из мапы
        if(!tasks.isEmpty()) {
            tasks.clear();
        }
    }

    @Override
    public void deleteAllEpic() { // Метод по удалению всех эпиков
            if(!epics.isEmpty()) {
                epics.clear();
            }
        }

    @Override
    public void deleteAllSubtask() { // Метод по удалению всех подзадач
        if (!subtasks.isEmpty()) {
            subtasks.clear();
        }
    }
        @Override
        public void deleteTaskID (int id) { // Метод по удалению определенной задачи
            tasks.remove(id);
        }

        @Override
        public void deleteEpicID (int id) { // Метод по удалению определенного эпика
            epics.remove(id);
        }

        @Override
        public void deleteSubtaskID (int id) { // Метод по удалению определенной подзадачи
            subtasks.remove(id);
        }

    @Override
    public void checkStatusEpic(Epic epic) {
        List<Subtask> subtaskEpic= epic.getSubtaskEpic();
        int subtaskNew = 0;
        int subtaskInProgress = 0;
        int subtaskDone = 0;
        for (Subtask subtask: subtaskEpic) {
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
    public void renewalTask(Task task, int id) { // Методы по обновлению задач, подзадач, эпиков
        task.setId(id);
        tasks.put(id, task);
    }

    @Override
    public void renewalEpic(Epic epic, int id) {
        epic.setId(id);
        epics.put(id, epic);
    }

    @Override
    public void renewalSubtask(Subtask subtask, int id) {
        subtask.setId(id);
        subtasks.put(id, subtask);
    }

    public Map<Integer, Task> getTasks() { // Методы для получения задач, эпиков, подзадач
        return tasks;
    }

    public Map<Integer, Epic> getEpics() {
        return epics;
    }

    public Map<Integer, Subtask> getSubtasks() {
        return subtasks;
    }
}
