import manager.HistoryManager;
import manager.Managers;
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

        Epic epicOne = new Epic("Большая задача (эпик) №1", "Описание задачи №1", NEW);
        Subtask subtaskOne = new Subtask("Подзадача №2", "Описание подзадачи №2", NEW, 1);
        Subtask subtaskTwo = new Subtask("Подзадача №3", "Описание подзадачи №3", NEW, 1);
        Subtask subtaskThree = new Subtask("Подзадача №4", "Описание подзадачи №4", NEW, 1);
        Epic epicTwo = new Epic("Большая задача (эпик) №5", "Описание задачи №5", NEW);

        inMemoryHistoryManager.add(epicOne); // Внесение всех задач, эпиков, подзадач
        inMemoryHistoryManager.add(epicTwo);
        inMemoryHistoryManager.add(subtaskOne);
        inMemoryHistoryManager.add(subtaskTwo);
        System.out.println(inMemoryHistoryManager.getHistory()); // После запросов выводим историю и смотрим, что в ней нет повторо
    }
}
