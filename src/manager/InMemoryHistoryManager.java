package manager;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager { /* Класс для работы с историей, перенос функционала из InMemoryTaskManager,
реализует интерфейс HistoryManager */
    private final List<Task> tasks = new ArrayList<>(); // Список задач
    private final int dimension = 10; // Кол-во выводимых задач дб не более 10

    @Override
    public void add(Task task) { // Метод, который помечает задачи как просмотренные
        if (tasks != null) { // Добавляем проверку на null
            tasks.add(task);
        }
        if (tasks.size() == dimension) {
            tasks.remove(0); // Удаляем самую старую задачу, если размер списка больше 10
        }
    }

    @Override
    public List<Task> getHistory() { // Метод, возвращающий 10 последних просмотренных задач списком
        return tasks;
    }
}