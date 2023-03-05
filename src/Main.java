import manager.HistoryManager;
import manager.Managers;
import manager.TaskManager;
import model.Epic;
import model.Subtask;

import static model.Status.NEW;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Трекер задач!");
        testCode(); // Тестирование задачи
    }

    private static void testCode() { // Проверка работы приложения
        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
        TaskManager inMemoryTaskManager = Managers.getDefault();

        Epic epicOne = new Epic("Большая задача (эпик) №1", "Описание задачи №1", NEW);
        Subtask subtaskOne = new Subtask("Подзадача №2", "Описание подзадачи №2", NEW, 1);
        Subtask subtaskTwo = new Subtask("Подзадача №3", "Описание подзадачи №3", NEW, 1);
        Subtask subtaskThree = new Subtask("Подзадача №4", "Описание подзадачи №4", NEW, 1);
        Epic epicTwo = new Epic("Большая задача (эпик) №5", "Описание задачи №5", NEW);

        inMemoryTaskManager.addEpic(epicOne); // Внесение всех задач, эпиков, подзадач
        inMemoryTaskManager.addEpic(epicTwo);
        inMemoryTaskManager.addSubtask(subtaskOne);
        inMemoryTaskManager.addSubtask(subtaskTwo);
        inMemoryTaskManager.addSubtask(subtaskThree);
        inMemoryTaskManager.addSubtask(subtaskOne);
        inMemoryTaskManager.addEpic(epicTwo);
        inMemoryHistoryManager.add(epicOne);
        inMemoryHistoryManager.add(epicTwo);
        System.out.println(inMemoryHistoryManager.getHistory());
        inMemoryHistoryManager.add(epicOne);
        System.out.println(inMemoryHistoryManager.getHistory()); // После запросов выводим историю и смотрим, что в ней нет повторо
        inMemoryHistoryManager.remove(7);
        System.out.println(inMemoryHistoryManager.getHistory());
    }
}
