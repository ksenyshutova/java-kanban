package service;

import model.Epic;
import model.Subtask;
import model.Task;
import java.util.List;
import java.util.Map;

public interface Manager {
    void addTask(Task task); // Методы по добавлению задач, эпиков, подзадач
    void addEpic(Epic epic);
    void addSubtask(Subtask subtask);
    List<Subtask> getListSubtaskEpic(int id); // Метод для получения списка подзадач в эпике
    void deleteAllTask(); // Методы по удалению всех задач, эпиков, подзадач
    void deleteAllEpic();
    void deleteAllSubtask();
    void deleteTaskID(int id); // Методы по удалению определенных задач, эпиков, подзадач по ID
    void deleteEpicID(int id);
    void deleteSubtaskID(int id);
    void checkStatusEpic(Epic epic); // Проверка статуса эпика
    void renewalTask(Task task, int id); // Методы по обновлению задач, подзадач, эпиков
    void renewalEpic(Epic epic, int id);
    void renewalSubtask(Subtask subtask, int id);
    Map<Integer, Task> getTasks(); // Методы для получения задач, эпиков, подзадач
    Map<Integer, Epic> getEpics();
    Map<Integer, Subtask> getSubtasks();
}
