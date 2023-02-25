package manager;

import model.Task;

class Node { // Класс для элементов-узлов, выделяем в отдельный класс

    public Task data; // Данные внутри элемента
    public Node next; // Ссылка на следующий узел
    public Node prev; // Ссылка на предыдущий узел

    public Node(Task data) { // Конструктор
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

