package writter;

import dataObject.Board;
import dataObject.boardObject.Adventurer;
import dataObject.boardObject.IBoardEntity;
import dataObject.boardObject.Mountain;
import dataObject.boardObject.Treasure;
import dataStructure.Coordinates;
import engine.SimulationState;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameStateWriter {
    public static void writeFile(final SimulationState simulator, final String outputFile) {
        try {
            File file = new File(outputFile);
            FileWriter fileReader = new FileWriter(file); // A stream that connects to the text file
            BufferedWriter bufferedWriter = new BufferedWriter(fileReader); // Connect the FileWriter to the BufferedWriter

            final Board board = simulator.getCarte();

            bufferedWriter.write("C - " + board.getHorizontalLength() + " - " + board.getVerticalLength());
            bufferedWriter.newLine();
            board.getAllObjectSortedByCoordonate().forEach((entry) -> {
                final IBoardEntity key = entry.getKey();
                final Coordinates value = entry.getValue();
                try {
                    if (key instanceof Mountain) {
                        bufferedWriter.write("M - " + value.x() + " - " + value.y());
                        bufferedWriter.newLine();
                    } else if (key instanceof final Treasure treasure) {
                        bufferedWriter.write("T - " + value.x() + " - " + value.y() + " - " + treasure.getNumberOfTreasure());
                        bufferedWriter.newLine();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            simulator.getQueue().forEach(entry -> {
                if (entry instanceof final Adventurer adventurer) {
                    Coordinates coordinates = board.getCoordinatesFromObject(adventurer);
                    try {
                        bufferedWriter.write("A - " + adventurer.getName() + " - " + coordinates.x() + " - " + coordinates.y() + " - " + adventurer.getOrientation() + " - " + adventurer.getNumberOfTreasure());
                        bufferedWriter.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            bufferedWriter.close (); // Close the stream
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
