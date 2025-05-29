package factory;

import dataObject.boardObject.Adventurer;
import dataObject.boardObject.IBoardEntity;
import dataObject.boardObject.Mountain;
import dataObject.boardObject.Treasure;
import dataStructure.Coordinates;
import dataStructure.Tuple;
import exceptions.IllegalOrientationException;
import exceptions.WrongFileFormatException;

import java.util.List;

public class MapObjectFactory {
    public static Tuple<IBoardEntity, Coordinates> createMapObject(final List<String> splittedLine) throws WrongFileFormatException, IllegalOrientationException {
        IBoardEntity mapObject;
        int xCoordonatePos = 1;
        int yCoordonatePos = 2;
        switch (splittedLine.get(0)) {
            case "A":
                //do something
                xCoordonatePos = 2;
                yCoordonatePos = 3;
                if (!List.of("N", "S", "O", "E").contains(splittedLine.get(4))) {
                    throw new IllegalOrientationException("This orientation is not available. Available orientations are: N, S, E, O");
                }
                mapObject = new Adventurer(splittedLine.get(1), splittedLine.get(4), splittedLine.get(5));
                break;
            case "M":
                mapObject = new Mountain();
                break;
            case "T":
                int remainingTreasure;
                try {
                    remainingTreasure = Integer.parseInt(splittedLine.get(3));
                }
                catch (NumberFormatException e) {
                    throw new WrongFileFormatException("Number of treasure should be a number");
                }
                if(remainingTreasure <= 0) {
                    throw new WrongFileFormatException("Treasure can't be negative");
                }
                mapObject = new Treasure(remainingTreasure);
                break;
            default:
                throw new WrongFileFormatException("Line does not start with a correct letter. Possible cases are: A, T, M or C");
        }

        int x;
        int y;
        try {
            x = Integer.parseInt(splittedLine.get(xCoordonatePos));
            y = Integer.parseInt(splittedLine.get(yCoordonatePos));
        }
        catch (NumberFormatException e) {
            throw new WrongFileFormatException("Coordinates should be written in mumbers");
        }
        return new Tuple<>(mapObject, new Coordinates(x, y));
    }
}
