package parser;

import dataObject.Board;
import dataObject.boardObject.IBoardEntity;
import dataStructure.Coordinates;
import dataStructure.Tuple;
import engine.SimulationState;
import exceptions.IllegalOrientationException;
import exceptions.WrongFileFormatException;
import factory.MapObjectFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileParser {
    public static SimulationState parseFile(final String fileName) throws WrongFileFormatException, IllegalOrientationException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));

            String line = reader.readLine();
            while (line != null && line.startsWith("#")) {
                line = reader.readLine();
            }

            if (line == null || !line.startsWith("C")) {
                throw new WrongFileFormatException("First line except comments should start with the letter C");
            }
            final List<String> boardValues = Arrays.asList(line.split(" - "));
            final Board board = new Board(Integer.parseInt(boardValues.get(1)), Integer.parseInt(boardValues.get(2)));
            final SimulationState simulationState = new SimulationState(board);
            line = reader.readLine();

            while (line != null) {
                if (line.startsWith("#")) {
                    line = reader.readLine();
                    continue;
                }
                final List<String> values = Arrays.asList(line.split(" - "));
                final Tuple<IBoardEntity, Coordinates> tuple = MapObjectFactory.createMapObject(values);
                simulationState.addObject(tuple.x(), tuple.y());
                // read next line
                line = reader.readLine();
            }

            reader.close();
            return simulationState;
        } catch (IOException e) {
            throw  new IllegalStateException(e);
        }
    }
}
