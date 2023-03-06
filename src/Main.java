import manager.Managers;
import manager.TaskManager;
import model.Task;

import static model.Status.NEW;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Трекер задач!");
        testCode(); // Тестирование задачи
    }

    private static void testCode() { // Проверка работы приложения
        /*
        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
        TaskManager inMemoryTaskManager = Managers.getDefault();
        Epic epicOne = new Epic("Большая задача (эпик) №1", "Описание задачи №1", NEW);
        Subtask subtaskOne = new Subtask("Подзадача №2", "Описание подзадачи №2", NEW, 1);
        Subtask subtaskTwo = new Subtask("Подзадача №3", "Описание подзадачи №3", NEW, 1);
        Subtask subtaskThree = new Subtask("Подзадача №4", "Описание подзадачи №4", NEW, 1);
        Epic epicTwo = new Epic("Большая задача (эпик) №5", "Описание задачи №5", NEW);
         */

        TaskManager manager = Managers.getDefault();
        manager.addTask(new Task("1", "1", NEW));
        manager.addTask(new Task("2", "2", NEW));
        manager.addTask(new Task("3", "3", NEW));
        manager.getTasksID(1);
        manager.getTasksID(2);
        manager.getTasksID(3);
        manager.deleteTaskID(2);
        System.out.println(manager.getHistory());
        manager.deleteAllTask();
        System.out.println(manager.getHistory());
    }
}
