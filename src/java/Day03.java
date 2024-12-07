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
        boolean sumsEnabled = true;
        int total = 0;

        while (!stringEnd) {
            int nextMul = commandString.indexOf("mul(");
            int nextDo = commandString.indexOf("do()");
            int nextDont = commandString.indexOf("don't()");

            if (nextMul > -1 && (nextMul < nextDo || nextDo == -1) && (nextMul < nextDont || nextDont == -1)) {
                commandString = commandString.substring(nextMul + 4);

                // Check the remaining string is of the format [any number],[any number])[anything]
                if (commandString.substring(0,commandString.indexOf(")")).matches("^\\d+,\\d+")) {
                    String[] factors = commandString.substring(0, commandString.indexOf(')')).split(",");
                    if (sumsEnabled) {
                        total += Integer.parseInt(factors[0]) * Integer.parseInt(factors[1]);
                    } 
                }
            } else if (nextDo > -1 && (nextDo < nextMul || nextMul == -1) && (nextDo < nextDont || nextDont == -1)) {
                sumsEnabled = true;
                commandString = commandString.substring(nextDo + 4);
            } else if (nextDont > -1 && (nextDont < nextMul || nextMul == -1) && (nextDont < nextDo || nextDo == -1)) {
                sumsEnabled = false;
                commandString = commandString.substring(nextDont + 7);
            } else {
                stringEnd = true;
            }
        }
        System.out.println(total);
    }
}
