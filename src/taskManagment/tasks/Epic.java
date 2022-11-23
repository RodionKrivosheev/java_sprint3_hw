package taskManagment.tasks;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {

    protected ArrayList<Integer> subtaskIds = new ArrayList<>();


    public Epic(String name, String description, String status) {
        super(name, description, status);
    }

    public boolean isEpic() {
        return true;
    }
    public void addSubtaskId(int id) {
        subtaskIds.add(id);
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }
    public void cleanSubtaskIds() {
        subtaskIds.clear();
    }

    public void removeSubtaskIds(int id) {
        subtaskIds.remove(Integer.valueOf(id));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subtaskIds, epic.subtaskIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subtaskIds);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
