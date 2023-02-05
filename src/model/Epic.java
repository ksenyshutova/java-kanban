package model;

import service.Status;
import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Subtask> subtaskEpic = new ArrayList<>(); // Список подзадач в эпике

    public Epic(String name, String description, Status status) {
        super(name, description, status);
    }

    public List<Subtask> getSubtaskEpic() {
        return subtaskEpic;
    }

    public void setSubtaskEpic(List<Subtask> subtaskEpic) {
        this.subtaskEpic = subtaskEpic;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", subtaskEpic=" + subtaskEpic +
                '}';
    }
}
