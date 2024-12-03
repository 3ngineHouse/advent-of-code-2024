import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day01 {

    public static void main(String[] args) throws IOException {
        // Load the file into lists we can manipulate
        Path filePath = Paths.get("src/resources/Day01.txt");
        List<Integer> leftColumn = new ArrayList<>();
        List<Integer> rightColumn = new ArrayList<>();

        Files.readAllLines(filePath).forEach(line -> {
            leftColumn.add(Integer.parseInt(line.split("   ")[0]));
            rightColumn.add(Integer.parseInt(line.split("   ")[1]));
        });

        leftColumn.sort(Comparator.naturalOrder());
        rightColumn.sort(Comparator.naturalOrder());

        if (leftColumn.size() != rightColumn.size()) {
            throw new IOException("Number provided without paired number");
        }

        // Calculate the total distance
        int totalDistance = 0;
        for (int i = 0; i < leftColumn.size(); i++) {
            totalDistance += Math.abs(leftColumn.get(i) - rightColumn.get(i));
        }
        System.out.println("Total distance: " + totalDistance);

        // Calculate the total similarity score
        int totalSimilarity = 0;
        for (int leftItem : leftColumn) {
            int currentSimilarity = 0;

            boolean matchFound = false;
            for (int rightItem: rightColumn) {
                if (leftItem == rightItem) {
                    currentSimilarity += leftItem;
                    matchFound = true;
                }
                if (leftItem < rightItem && matchFound) {
                    // We've pass the block of matching numbers
                    break;
                }
            }
            totalSimilarity += currentSimilarity;
        };
        System.out.println("Total similarity: " + totalSimilarity);
    }
}