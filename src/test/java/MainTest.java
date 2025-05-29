import exceptions.IllegalOrientationException;
import exceptions.WrongFileFormatException;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void mainWithWrongArgs() throws IllegalOrientationException, WrongFileFormatException {
        assertThrows(IllegalArgumentException.class, () -> Main.main(new String[]{}));
    }

    @Test
    void mainWithCorrectArgs() throws IllegalOrientationException, WrongFileFormatException {
        Main.main(new String[]{"src/test/resources/mainTestFiles//inputMainTest.txt", "src/test/resources/mainTestFiles//outputMainTest.txt"});
        final String outputFileContent = readFile("src/test/resources/mainTestFiles//outputMainTest.txt");
        assertTrue(outputFileContent.contains("C - 3 - 4"));
        assertTrue(outputFileContent.contains("M - 1 - 0"));
        assertTrue(outputFileContent.contains("M - 2 - 1"));
        assertTrue(outputFileContent.contains("T - 1 - 3 - 2"));
        assertTrue(outputFileContent.contains("A - Lara - 0 - 3 - S - 3"));
    }

    private String readFile(final String path) {
        // read file and return content
        final StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));

            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = reader.readLine();
            }

            reader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw  new IllegalStateException(e);
        }
    }
}