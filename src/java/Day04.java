import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day04 {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("src/resources/Day04.txt");
        List<List<Character>> grid = new ArrayList<>();

        int gridWidth = 0;
        for (String line: Files.readAllLines(filePath)) {
            List<Character> charList = new ArrayList<>();
            for (char c : line.toCharArray()) {
                charList.add(c);
            }
            if (gridWidth == 0) {
                gridWidth = charList.size();
            } else {
                if (charList.size() != gridWidth) {
                    throw new IllegalArgumentException("Grid is not square");
                }
            }
            grid.add(charList);
        }

        // Part 1
        int xmasCount = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                // Check for horizontal, forwards
                if (j < gridWidth - 3) {
                    if (xmasCheck(
                        grid.get(i).get(j), 
                        grid.get(i).get(j+1),
                        grid.get(i).get(j+2),
                        grid.get(i).get(j+3)                                                
                    )) {
                        xmasCount++;
                    }
                }
                // Check for horizontal, backwards
                if (j >= 3) {
                    if (xmasCheck(
                        grid.get(i).get(j), 
                        grid.get(i).get(j-1),
                        grid.get(i).get(j-2),
                        grid.get(i).get(j-3)                                                
                    )) {
                        xmasCount++;
                    }
                }
                // Check upwards
                if (i >= 3) {
                    if (xmasCheck(
                        grid.get(i).get(j), 
                        grid.get(i-1).get(j),
                        grid.get(i-2).get(j),
                        grid.get(i-3).get(j)                                                
                    )) {
                        xmasCount++;
                    }
                }
                // Check downwards
                if (i < grid.size()-3) {
                    if (xmasCheck(
                        grid.get(i).get(j), 
                        grid.get(i+1).get(j),
                        grid.get(i+2).get(j),
                        grid.get(i+3).get(j)                                                
                    )) {
                        xmasCount++;
                    }
                }
                // Check diagonal up and left
                if (i >= 3 && j >= 3) {
                    if (xmasCheck(
                        grid.get(i).get(j), 
                        grid.get(i-1).get(j-1),
                        grid.get(i-2).get(j-2),
                        grid.get(i-3).get(j-3)                                                
                    )) {
                        xmasCount++;
                    }
                }
                // Check diagonal up and right
                if (i >= 3 && j < gridWidth - 3) {
                    if (xmasCheck(
                        grid.get(i).get(j), 
                        grid.get(i-1).get(j+1),
                        grid.get(i-2).get(j+2),
                        grid.get(i-3).get(j+3)                                                
                    )) {
                        xmasCount++;
                    }
                }
                // Check diagonal down and left
                if (i < grid.size()-3 && j >= 3) {
                    if (xmasCheck(
                        grid.get(i).get(j), 
                        grid.get(i+1).get(j-1),
                        grid.get(i+2).get(j-2),
                        grid.get(i+3).get(j-3)                                                
                    )) {
                        xmasCount++;
                    }
                }
                // Check diagonal down and left
                if (i < grid.size()-3 && j < gridWidth-3) {
                    if (xmasCheck(
                        grid.get(i).get(j), 
                        grid.get(i+1).get(j+1),
                        grid.get(i+2).get(j+2),
                        grid.get(i+3).get(j+3)                                                
                    )) {
                        xmasCount++;
                    }
                }
            }
        }
        System.out.println(xmasCount);

        // Part 2
        int xCount = 0;
        for (int i = 1; i < grid.size() - 1; i++) {
            for (int j = 1; j < grid.get(i).size() - 1; j++) {
                if (grid.get(i).get(j).equals('A')) {
                    if (masCheck(
                        grid.get(i-1).get(j-1), 
                        grid.get(i-1).get(j+1), 
                        grid.get(i+1).get(j-1), 
                        grid.get(i+1).get(j+1)
                    )) {
                        xCount++;
                    }
                }
            }
        }
        System.out.println(xCount);
    }

    private static boolean masCheck(Character topLeft, Character topRight, Character bottomLeft, Character bottomRight) {
        if (topLeft.equals('M')) {
            if (bottomRight.equals('S')) {
                return ((topRight.equals('M') && bottomLeft.equals('S')) || (topRight.equals('S') && bottomLeft.equals('M')));
            } else {
                return false;
            }
        } else if (topLeft.equals('S')) {
            if (bottomRight.equals('M')) {
                return ((topRight.equals('M') && bottomLeft.equals('S')) || (topRight.equals('S') && bottomLeft.equals('M')));
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean xmasCheck(Character first, Character second, Character third, Character fourth) {
        return first.equals('X') && second.equals('M') && third.equals('A') && fourth.equals('S');
    }
}