import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day03 {
    public static void main(String[] args) throws IOException {
        // Load the file as a string we can manipulate
        Path filePath = Paths.get("src/resources/Day03.txt");
        String commandString = Files.readString(filePath);
        
        boolean stringEnd = false;
        int total = 0;

        while (!stringEnd) {
            int nextMul = commandString.indexOf("mul(");
            if (nextMul > -1) {
                commandString = commandString.substring(nextMul + 4);


                // Check the remaining string is of the format [any number],[any number])[anything]
                if (commandString.substring(0,commandString.indexOf(")")).matches("^\\d+,\\d+")) {
                    String[] factors = commandString.substring(0, commandString.indexOf(')')).split(",");
                    total += Integer.parseInt(factors[0]) * Integer.parseInt(factors[1]);
                }
            } else {
                stringEnd = true;
            }
        }
        System.out.println(total);
    }
}
