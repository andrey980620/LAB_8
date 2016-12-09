import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Гончаревич Андрей 8 группа
 */
public class Utils {
    public static void getDataFromFile(ArrayList<Juice> juices, Juice required, String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner in = new Scanner(file);
        int currentIndex = -1;
        while (in.hasNextLine()) {
            juices.add(new Juice());
            currentIndex++;
            String[] components = in.nextLine().split(" ");
            for (String unit : components) {
                if (!Objects.equals(unit, ""))
                    juices.get(currentIndex).addComponent(unit);
                if (!required.getComponents().contains(unit))
                    required.addComponent(unit);
            }
        }

    }

    public static int calculateMinimalCleanings(ArrayList<Juice> juices) {
        int min = juices.size();

        juices.sort((o1, o2) -> {
            int minSize = (o1.getComponents().size() < o2.getComponents().size())
                    ? o1.getComponents().size() : o2.getComponents().size();
            for (int i = 0; i < minSize; i++) {
                int flag = o1.getComponents().get(i).compareTo(o2.getComponents().get(i));
                if (flag != 0) return flag;
            }
            return o1.getComponents().size() - o2.getComponents().size();
        });

        for (int i = 1; i < juices.size(); i++) {
            if (juices.get(i).getComponents().containsAll(juices.get(i - 1).getComponents()))
                min--;
        }
        return min;
    }

    public static void writeToFile(String path, ArrayList<String> data) throws IOException {
        try {
            File newFile = new File(path);
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
            FileWriter out = new FileWriter(newFile);
            for (String unit : data) {
                out.write(unit);
                out.write("\r\n");
            }
            out.close();
        } catch (IOException e) {
            System.out.println("\n!!! Unable to  create a file with path:  " + path);
        }
    }

    public static void writeToFile(String path, int data) throws IOException {
        try {
            File newFile = new File(path);
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
            FileWriter out = new FileWriter(newFile);
            out.write(String.valueOf(data));
            out.close();
        } catch (IOException e) {
            System.out.println("\n!!! Unable to  create a file with path:  " + path);
        }
    }
}
