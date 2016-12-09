import java.util.ArrayList;

/**
 * Гончаревич Андрей 8 группа
 */
public class Juice {
    private ArrayList<String> components = new ArrayList<>();

    public void addComponent(String component) {
        components.add(component);
    }

    public void sortByName() {
        components.sort((o1, o2) -> o1.compareTo(o2));
    }

    public ArrayList<String> getComponents() {
        return components;
    }
}
