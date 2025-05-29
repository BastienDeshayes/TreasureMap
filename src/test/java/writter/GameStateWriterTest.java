package writter;

import dataObject.Board;
import dataObject.boardObject.Adventurer;
import dataObject.boardObject.Mountain;
import dataObject.boardObject.Treasure;
import dataStructure.Coordinates;
import engine.SimulationState;
import exceptions.IllegalOrientationException;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameStateWriterTest {

    @Test
    void writeFileWithTwoAdventurers() throws IllegalOrientationException {
        final Board board = new Board(3, 3);
        final SimulationState simulationState = new SimulationState(board);
        final Adventurer adventurer = new Adventurer("Toto", "E", "");
        simulationState.addObject(adventurer, new Coordinates(0, 0));
        final Adventurer secondAdventurer = new Adventurer("Tata", "O", "");
        simulationState.addObject(secondAdventurer, new Coordinates(2, 0));
        GameStateWriter.writeFile(simulationState, "src/test/resources/writerTestFiles/outputOnlyAdventurers.txt");
        final String outputContent = readFile("src/test/resources/writerTestFiles/outputOnlyAdventurers.txt");
        assertTrue(outputContent.contains("C - 3 - 3"));
        assertTrue(outputContent.contains("A - Toto - 0 - 0 - E - 0"));
        assertTrue(outputContent.contains("A - Tata - 2 - 0 - O - 0"));
    }

    @Test
    void writeFileWithMultipeElement() throws IllegalOrientationException {
        final Board board = new Board(3, 3);
        final SimulationState simulationState = new SimulationState(board);
        final Adventurer adventurer = new Adventurer("Toto", "E", "");
        simulationState.addObject(adventurer, new Coordinates(0, 0));
        final Mountain mountain = new Mountain();
        simulationState.addObject(mountain, new Coordinates(2, 1));
        final Treasure treasure = new Treasure(2);
        simulationState.addObject(treasure, new Coordinates(0, 2));
        GameStateWriter.writeFile(simulationState, "src/test/resources/writerTestFiles/outputMultipleElements.txt");
        final String outputContent = readFile("src/test/resources/writerTestFiles/outputMultipleElements.txt");
        assertTrue(outputContent.contains("C - 3 - 3"));
        assertTrue(outputContent.contains("A - Toto - 0 - 0 - E - 0"));
        assertTrue(outputContent.contains("M - 2 - 1"));
        assertTrue(outputContent.contains("T - 0 - 2"));
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