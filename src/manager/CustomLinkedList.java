package manager;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomLinkedList<T extends Task> { // Создаем собственный список, воспользовалась для примера стандарт. реализацией из IDEA
    private final Map<Integer, Node<Task>> mapToList = new HashMap<>();  // В ключах будут храниться id задач, а в значениях — узлы связного списка. С помощью номера задачи можно получить соответствующий ему узел связного списка и удалить его
    int size = 0;
    Node<Task> first; // Первый узел
    Node<Task> last; // Последний узел

    public void linkLast(Task task) { // Метод, который будет добавлять задачу в конец списка, пример из LL
        final Node<Task> l = last; // Новый элемент станет хвостом
        final Node<Task> newNode = new Node<>(l, task, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
            size++;
        }
        mapToList.put(task.getId(), last);
    }


    public List<Task> getTasks() { // Метод собирает все задачи из списка в обычный ArrayList
        List<Task> tasks = new ArrayList<>(); //Создаем список задач
        Node<Task> newNode = first;
        while (newNode != null) {
            tasks.add(newNode.item);
            newNode = newNode.next;
        }
        return tasks;
    }

    public void removeNode(Node<Task> newNode) { //В качестве параметра метод должен принимать объект Node — узел связного списка и вырезать его
        if (newNode != null) {
            Node<Task> next = newNode.next;
            Node<Task> prev = newNode.prev;

            if (prev == null) {
                first = next;
            } else {
                prev.next = newNode;
                newNode.prev = null;
            }
            if (next == null) {
                last = prev;
            } else {
                next.prev = next;
                newNode.next = null;
            }
            newNode.item = null;
            size--;
        }
    }

    public void removeId(int id) {
        removeNode(mapToList.remove(id));
    }

    private static class Node<Task> { // Добавила по рекомендации в класс CustomLinkedList, сделав его статическим и приватным.
        Task item; // Использовала стандартную реализацию LL
        Node<Task> next;
        Node<Task> prev;

        Node(Node<Task> prev, Task element, Node<Task> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
