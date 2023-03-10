package manager;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager { // Класс для хранения всей необходимой информации задач, эпиков, подзадач
    private final Map<Integer, Task> tasks = new HashMap<>(); // Присвоение соответствия между идентификатором и задачей
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private final HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
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
        Epic epic = epics.get(subtask.getIdEpic());
        epic.addSubtaskId(id);
        checkStatusEpic(epic);
    }

    @Override
    public List<Subtask> getListSubtaskEpic(int id) { // Метод для получения списка подзадач в эпике
        if (epics.containsKey(id)) {
            List<Subtask> subtask = new ArrayList<>();
            Epic epic = epics.get(id);
            for (int i = 0; i < epic.getSubtaskId().size(); i++) {
                subtask.add(subtasks.get(epic.getSubtaskId().get(i)));
            }
            return subtask;
        } else {
            return null;
        }
    }

    @Override
    public void deleteAllTask() { // Метод по удалению всех задач из мапы
        for (Integer id : tasks.keySet()) {
            inMemoryHistoryManager.remove(id);
        }
        tasks.clear();
    }

    @Override
    public void deleteAllEpic() { // Метод по удалению всех эпиков
        for (Integer id : epics.keySet()) {
            inMemoryHistoryManager.remove(id);
        }
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void deleteAllSubtask() { // Метод по удалению всех подзадач
        for (Integer id : subtasks.keySet()) {
            inMemoryHistoryManager.remove(id);
        }
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.deleteAllSubtaskId();
            epic.setStatus(Status.NEW);
        }
    }

    @Override
    public void deleteTaskID(int id) { // Метод по удалению определенной задачи
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            inMemoryHistoryManager.remove(id);
        }
    }

    @Override
    public void deleteEpicID(int id) { // Метод по удалению определенного эпика
        if (epics.containsKey(id)) {
            List<Integer> subtaskId = epics.get(id).getSubtaskId();
            for (Integer idSubtask : subtaskId) {
                subtasks.remove(idSubtask);
            }
            epics.remove(id);
            inMemoryHistoryManager.remove(id);
        }
    }

    @Override
    public void deleteSubtaskID(int id) { // Метод по удалению определенной подзадачи
        if (subtasks.containsKey(id)) {
            int idEpic = subtasks.get(id).getIdEpic();
            Epic epic = epics.get(idEpic);
            epic.deleteSubtaskId(id);
            subtasks.remove(id);
            inMemoryHistoryManager.remove(id);
            checkStatusEpic(epic);
        }
    }

    @Override
    public void checkStatusEpic(Epic epic) { //Обновление данных эпика
        int subtaskNew = 0;
        int subtaskDone = 0;
        if (epic.getSubtaskId().isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }
        for (Integer subtaskId : epic.getSubtaskId()) {
            if (subtasks.get(subtaskId).getStatus() == Status.NEW) {
                subtaskNew++;
            } else if (subtasks.get(subtaskId).getStatus() == Status.DONE) {
                subtaskDone++;
            }
        }
        if (subtaskNew == epic.getSubtaskId().size()) {
            epic.setStatus(Status.NEW);
        } else if (subtaskDone == epic.getSubtaskId().size()) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    @Override
    public Task renewalTask(Task task) { // Методы по обновлению задач, подзадач, эпиков
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
        return null;
    }

    @Override
    public Epic renewalEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
            checkStatusEpic(epic);
        }
        return null;
    }

    @Override
    public Subtask renewalSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getIdEpic());
            checkStatusEpic(epic);
        }
        return null;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public Task getTasksID(int id) { // Просмотром будем считаться вызов у менеджера методов получения задачи по идентификатору
        inMemoryHistoryManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpicsID(int id) {
        inMemoryHistoryManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public Subtask getSubtasksID(int id) {
        inMemoryHistoryManager.add(subtasks.get(id));
        return subtasks.get(id);

    }

    @Override
    public List<Task> getHistory() {
        return inMemoryHistoryManager.getHistory();
    }
}
