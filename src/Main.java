import manager.Managers;
import manager.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;

import static model.Status.NEW;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Трекер задач!");
        testCode(); // Тестирование задачи
    }

    private static void testCode() { // Проверка работы приложения
        TaskManager inMemoryTaskManager = Managers.getDefault();

        Task taskOne = new Task("Задача №1", "Описание задачи №1", NEW);
        Task taskTwo = new Task("Задача №2", "Описание задачи №2", NEW);
        Epic epicOne = new Epic("Большая задача (эпик) №3", "Описание задачи №3", NEW);
        Subtask subtaskOne = new Subtask("Подзадача №4", "Описание подзадачи №4", NEW, 3);
        Subtask subtaskTwo = new Subtask("Подзадача №5", "Описание подзадачи №5", NEW, 3);
        Epic epicTwo = new Epic("Большая задача (эпик) №6", "Описание задачи №6", NEW);
        Subtask subtaskThree = new Subtask("Подзадача №7", "Описание подзадачи №7", NEW, 6);
        inMemoryTaskManager.addTask(taskOne); // Внесение всех задач, эпиков, подзадач
        inMemoryTaskManager.addTask(taskTwo);
        inMemoryTaskManager.addEpic(epicOne);
        inMemoryTaskManager.addSubtask(subtaskOne);
        inMemoryTaskManager.addSubtask(subtaskTwo);
        inMemoryTaskManager.addEpic(epicTwo);
        inMemoryTaskManager.addSubtask(subtaskThree);
        inMemoryTaskManager.getTasksID(1);
        System.out.println(inMemoryTaskManager.getHistory());
        inMemoryTaskManager.getEpicsID(6);
        System.out.println(inMemoryTaskManager.getHistory());
        inMemoryTaskManager.getSubtasksID(7);
        System.out.println(inMemoryTaskManager.getHistory());
        inMemoryTaskManager.getEpicsID(6);
        inMemoryTaskManager.getEpicsID(6);
        inMemoryTaskManager.getEpicsID(6);
        inMemoryTaskManager.getEpicsID(6);
        inMemoryTaskManager.getEpicsID(6);
        inMemoryTaskManager.getEpicsID(6);
        inMemoryTaskManager.getEpicsID(6);
        inMemoryTaskManager.getEpicsID(6);
        System.out.println(inMemoryTaskManager.getHistory());
        inMemoryTaskManager.getSubtasksID(7);
        System.out.println(inMemoryTaskManager.getHistory());
    }
}
