import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day02 {
    public static void main(String[] args) throws IOException {
        // Load the file into a list we can manipulate
        Path filePath = Paths.get("src/resources/Day02.txt");
        List<List<Integer>> reportList = new ArrayList<>();

        Files.readAllLines(filePath).forEach(line -> {
            List<Integer> report = new ArrayList<>();
            for(String level : line.split(" ")) {
                report.add(Integer.parseInt(level));    
            }
            reportList.add(report);
        });

        // Find the number of reports that are safe:
        int safeCount = 0;
        for (List<Integer> report : reportList) {
            if (reportIsSafe(report)) {
                safeCount++;
            }
        }
        System.out.println("Safe reports: " + safeCount);

        // Find the number of reports that are safe if one level can be removed from them
        safeCount = 0;
        for (List<Integer> report : reportList) {
            if (reportIsSafe(report)) {
                safeCount++;
            } else {
                for (int i = 0; i < report.size(); i++) {
                    List<Integer> modifiedReport = new ArrayList<>(report);
                    modifiedReport.remove(i);
                    if (reportIsSafe(modifiedReport)) {
                        safeCount++;
                        break;
                    }
                }
            }
        }
        System.out.println("Safe reports with Problem Dampener: " + safeCount);
    }

    private static boolean reportIsSafe(List<Integer> report) {
        int trendDirection = 0;
        int previousLevel = -1;
        for (int level : report) {
            if (previousLevel == -1) {
                previousLevel = level;
                continue;
            }
            if (Math.abs(level - previousLevel) > 3) {
                return false;
            }
            if (level - previousLevel < 0 && trendDirection <= 0) {
                trendDirection = -1;
            } else if (level - previousLevel > 0 && trendDirection >= 0) {
                trendDirection = 1;
            } else {
                return false;
            }
            previousLevel = level;
        }
        return true;
    }
}
