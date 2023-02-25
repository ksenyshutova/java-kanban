package manager;


import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager { /* Класс для работы с историей, перенос функционала из InMemoryTaskManager,
реализует интерфейс HistoryManager */

    private final CustomLinkedList<Task> list = new CustomLinkedList<>();
    private final Map<Integer, Node> mapToList = new HashMap<>(); // В ключах будут храниться id задач, а в значениях — узлы связного списка. С помощью номера задачи можно получить соответствующий ему узел связного списка и удалить его
    private Node head; // Поля для головы и хвоста списка
    private Node tail;
    private int size = 0;

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
    public void remove(int id) {// Метод для удаления задачи из просмотра
        if (mapToList.containsKey(id)) {
            list.removeNode(mapToList.get(id));
            mapToList.remove(id);
        }
    }

    private class CustomLinkedList<T> {
        public void linkLast(Task task) { // Метод, который будет добавлять задачу в конец списка
            Node nodeNew = new Node(task); // создаем новый узел, который добавл.в конец
            tail = nodeNew;
            if (tail == null) {
                head = nodeNew; // Если пуст, то нов.элемент-голова
                mapToList.put(task.getId(), nodeNew);
                size++;
            }
        }

        public List<Task> getTasks() { // Метод собирает все задачи из списка в обычный ArrayList
            List<Task> tasks = new ArrayList<>(); //Создаем список задач
            Node nodeNow = head;
            while (nodeNow != null) {
                tasks.add(nodeNow.data); // Добавляем данные в узел
            }
            return tasks;
        }

        public void removeNode(Node nowNode) { //В качестве параметра метод должен принимать объект Node — узел связного списка и вырезать его
            if (nowNode.prev != null) {
                nowNode.prev.next = nowNode.next;
            } else {
                nowNode.next.prev = null;
                head = nowNode.next;
            }
            if (nowNode.next != null) {
                nowNode.next.prev = nowNode.prev;
            } else {
                nowNode.prev.next = null;
                tail = nowNode.prev;
            }
        }
    }
}