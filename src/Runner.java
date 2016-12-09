import java.io.IOException;
import java.util.ArrayList;

/**
 * Гончаревич Андрей 8 группа
 */
public class Runner {
    public static void main(String[] args) throws IOException {

        ArrayList<Juice> toProduction = new ArrayList<>();
        Juice requiredIngredients = new Juice();
        Utils.getDataFromFile(toProduction, requiredIngredients, "src//juice.in");
        Utils.writeToFile("src//juice1.out", requiredIngredients.getComponents());

        Thread t1 = new Thread(() -> requiredIngredients.sortByName());
        t1.run();
        t1.interrupt();
        Utils.writeToFile("src//juice2.out", requiredIngredients.getComponents());

        Thread t2 = new Thread(() -> {
            for (Juice juice : toProduction) juice.sortByName();
        });
        t2.run();
        t2.interrupt();
        int minimalCleanings = Utils.calculateMinimalCleanings(toProduction);
        Utils.writeToFile("src//juice3.out", minimalCleanings);
    }
}
