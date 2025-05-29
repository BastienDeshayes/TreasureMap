package factory;

import dataObject.boardObject.Adventurer;
import dataObject.boardObject.IBoardEntity;
import dataObject.boardObject.Mountain;
import dataObject.boardObject.Treasure;
import dataStructure.Coordinates;
import dataStructure.Tuple;
import exceptions.IllegalOrientationException;
import exceptions.WrongFileFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapObjectFactoryTest {

    @Test
    void createAdventurer() throws WrongFileFormatException, IllegalOrientationException {
        final List<String> splittedLine = List.of("A", "TestName", "2", "4", "S", "ADDGAAGA");
        final Tuple<IBoardEntity, Coordinates> mapObject = MapObjectFactory.createMapObject(splittedLine);
        Assertions.assertNotNull(mapObject);
        assertInstanceOf(Adventurer.class, mapObject.x());
        final Adventurer adventurer = (Adventurer) mapObject.x();
        assertEquals("TestName", adventurer.getName());
        assertEquals("S", adventurer.getOrientation());
        assertEquals("ADDGAAGA", adventurer.getMovementSequence());
        final Coordinates coordinates = mapObject.y();
        assertEquals(2, coordinates.x());
        assertEquals(4, coordinates.y());
    }

    @Test
    void createMountain() throws WrongFileFormatException, IllegalOrientationException {
        final List<String> splittedLine = List.of("M", "2", "4");
        final Tuple<IBoardEntity, Coordinates> mapObject = MapObjectFactory.createMapObject(splittedLine);
        Assertions.assertNotNull(mapObject);
        assertInstanceOf(Mountain.class, mapObject.x());
        final Coordinates coordinates = mapObject.y();
        assertEquals(2, coordinates.x());
        assertEquals(4, coordinates.y());
    }

    @Test
    void createTreasure() throws WrongFileFormatException, IllegalOrientationException {
        final List<String> splittedLine = List.of("T", "2", "4", "1");
        final Tuple<IBoardEntity, Coordinates> mapObject = MapObjectFactory.createMapObject(splittedLine);
        Assertions.assertNotNull(mapObject);
        assertInstanceOf(Treasure.class, mapObject.x());
        final Treasure treasure = (Treasure) mapObject.x();
        assertEquals(1, treasure.getNumberOfTreasure());
        final Coordinates coordinates = mapObject.y();
        assertEquals(2, coordinates.x());
        assertEquals(4, coordinates.y());
    }

    @Test
    void createTreasureWithNegativeNumber() {
        final List<String> splitLine = List.of("T", "2", "4", "-1");
        assertThrows(WrongFileFormatException.class, () -> MapObjectFactory.createMapObject(splitLine));
    }

    @Test
    void createTreasureWithInvalidNumberTreasure() {
        final List<String> splitLine = List.of("T", "2", "4", "Toto");
        assertThrows(WrongFileFormatException.class, () -> MapObjectFactory.createMapObject(splitLine));
    }

    @Test
    void createWithLetterInsteadOfNumber() {
        final List<String> splitLine = List.of("T", "2", "WrongFormat", "1");
        assertThrows(WrongFileFormatException.class, () -> MapObjectFactory.createMapObject(splitLine));
    }

    @Test
    void createWithWrongIdentifierMethod() {
        final List<String> splitLine = List.of("WRONG", "2", "WrongFormat", "1");
        assertThrows(WrongFileFormatException.class, () -> MapObjectFactory.createMapObject(splitLine));
    }

    @Test
    void createAdventurerWithWrongOrientation() {
        final List<String> splitLine = List.of("A", "TestName", "2", "4", "Z", "ADDGAAGA");
        assertThrows(IllegalOrientationException.class, () -> MapObjectFactory.createMapObject(splitLine));
    }
}