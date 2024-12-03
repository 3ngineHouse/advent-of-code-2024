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
        int mulCount = 0;

        while (stringEnd = false) {
            int nextMul = commandString.indexOf("mul(");
            if (nextMul > -1) {
                commandString = commandString.substring(nextMul);
                mulCount++;
            } else {
                stringEnd = true;
            }
        }
        System.out.println(mulCount);
    }
}
