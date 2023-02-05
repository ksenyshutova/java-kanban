package model;

import service.Status;

public class Subtask extends Task {
    private int idEpic; // Определяем ID по эпику, к которому относится

    public Subtask(String name, String description, Status status, int idEpic) {
        super(name, description, status);
        this.idEpic = idEpic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", idEpic=" + idEpic +
                '}';
    }
}
