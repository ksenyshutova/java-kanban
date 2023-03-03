package manager;


import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager { /* Класс для работы с историей, перенос функционала из InMemoryTaskManager,
реализует интерфейс HistoryManager */

    private final CustomLinkedList<Task> list = new CustomLinkedList<>();


    @Override
    public void add(Task task) { // Метод по добавлению задач
        List<Task> tasks = new ArrayList<>(); //Создаем список задач
        if (tasks != null) { // Добавляем проверку на null для всего кода
            list.linkLast(task);
        }
    }

    @Override
    public List<Task> getHistory() { // Метод должен перекладывать задачи из связного списка в AL для формирования ответа
        return list.getTasks();
    }

    @Override
    public void remove(int id) { // Метод для удаления задачи из просмотра
        list.removeId(id);
    }
}