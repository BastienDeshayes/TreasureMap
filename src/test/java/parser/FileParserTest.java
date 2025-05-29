package parser;

import dataObject.Board;
import dataObject.boardObject.Adventurer;
import dataObject.boardObject.IBoardEntity;
import dataObject.boardObject.Mountain;
import dataObject.boardObject.Treasure;
import dataStructure.Coordinates;
import engine.SimulationState;
import exceptions.IllegalOrientationException;
import exceptions.WrongFileFormatException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class FileParserTest {

    @Test
    void parseFile() throws IllegalOrientationException, WrongFileFormatException {
        final SimulationState simulationState = FileParser.parseFile("src/test/resources/parserTestFiles/test.txt");
        assertNotNull(simulationState);
        assertEquals(1, simulationState.getQueue().size());
        final Board board = simulationState.getCarte();
        assertNotNull(board);
        final Adventurer adventurer = (Adventurer) simulationState.getQueue().poll();
        assertNotNull(adventurer);
        assertEquals("Lara", adventurer.getName());
        assertEquals("S", adventurer.getOrientation());
        assertEquals("AADADAGGA", adventurer.getMovementSequence());

        final List<IBoardEntity> shouldContainMountain = board.getObjectAtCoordinates(new Coordinates(1, 0));
        assertFalse(shouldContainMountain.isEmpty());
        assertTrue(shouldContainMountain.stream().anyMatch(mountain -> mountain instanceof Mountain));
        final List<IBoardEntity> shouldContainTreasure = board.getObjectAtCoordinates(new Coordinates(1, 3));
        assertFalse(shouldContainMountain.isEmpty());
        final Optional<IBoardEntity> shouldBeTreasure = shouldContainTreasure.stream().filter(entity -> entity instanceof Treasure).findFirst();
        assertTrue(shouldBeTreasure.isPresent());
        assertInstanceOf(Treasure.class, shouldBeTreasure.get());
        final Treasure treasure = (Treasure) shouldBeTreasure.get();
        assertEquals(3, treasure.getNumberOfTreasure());
    }

    @Test
    void parseFileWithCommentBeforeMap() throws IllegalOrientationException, WrongFileFormatException {
        final SimulationState simulationState = FileParser.parseFile("src/test/resources/parserTestFiles/inputWithCommentBeforeMap.txt");
        assertNotNull(simulationState);
        assertEquals(1, simulationState.getQueue().size());
        final Board board = simulationState.getCarte();
        assertNotNull(board);
        final Adventurer adventurer = (Adventurer) simulationState.getQueue().poll();
        assertNotNull(adventurer);
        assertEquals("Lara", adventurer.getName());
        assertEquals("S", adventurer.getOrientation());
        assertEquals("AADADAGGA", adventurer.getMovementSequence());

        final List<IBoardEntity> shouldContainMountain = board.getObjectAtCoordinates(new Coordinates(1, 0));
        assertFalse(shouldContainMountain.isEmpty());
        assertTrue(shouldContainMountain.stream().anyMatch(mountain -> mountain instanceof Mountain));
        final List<IBoardEntity> shouldContainTreasure = board.getObjectAtCoordinates(new Coordinates(1, 3));
        assertFalse(shouldContainMountain.isEmpty());
        final Optional<IBoardEntity> shouldBeTreasure = shouldContainTreasure.stream().filter(entity -> entity instanceof Treasure).findFirst();
        assertTrue(shouldBeTreasure.isPresent());
        assertInstanceOf(Treasure.class, shouldBeTreasure.get());
        final Treasure treasure = (Treasure) shouldBeTreasure.get();
        assertEquals(3, treasure.getNumberOfTreasure());
    }

    @Test
    void parseFileWithCommentBetweenLines() throws IllegalOrientationException, WrongFileFormatException {
        final SimulationState simulationState = FileParser.parseFile("src/test/resources/parserTestFiles/inputWithCommentBetweenLines.txt");
        assertNotNull(simulationState);
        assertEquals(1, simulationState.getQueue().size());
        final Board board = simulationState.getCarte();
        assertNotNull(board);
        final Adventurer adventurer = (Adventurer) simulationState.getQueue().poll();
        assertNotNull(adventurer);
        assertEquals("Lara", adventurer.getName());
        assertEquals("S", adventurer.getOrientation());
        assertEquals("AADADAGGA", adventurer.getMovementSequence());

        final List<IBoardEntity> shouldContainMountain = board.getObjectAtCoordinates(new Coordinates(1, 0));
        assertFalse(shouldContainMountain.isEmpty());
        assertTrue(shouldContainMountain.stream().anyMatch(mountain -> mountain instanceof Mountain));
        final List<IBoardEntity> shouldContainTreasure = board.getObjectAtCoordinates(new Coordinates(1, 3));
        assertFalse(shouldContainMountain.isEmpty());
        final Optional<IBoardEntity> shouldBeTreasure = shouldContainTreasure.stream().filter(entity -> entity instanceof Treasure).findFirst();
        assertTrue(shouldBeTreasure.isPresent());
        assertInstanceOf(Treasure.class, shouldBeTreasure.get());
        final Treasure treasure = (Treasure) shouldBeTreasure.get();
        assertEquals(3, treasure.getNumberOfTreasure());
    }

    @Test
    void parseFileWithInvalidMountainLinesBeforeMapLine() {
        assertThrows(WrongFileFormatException.class,() -> FileParser.parseFile("src/test/resources/parserTestFiles/inputInvalidMountainLinesBeforeMapLine.txt"));
    }

    @Test
    void parseFileWithInvalidLine() {
        assertThrows(WrongFileFormatException.class,() -> FileParser.parseFile("src/test/resources/parserTestFiles/inputInvalidLines.txt"));
    }

    @Test
    void parseFileWithEmptyFile() {
        assertThrows(WrongFileFormatException.class,() -> FileParser.parseFile("src/test/resources/parserTestFiles/inputEmptyFile.txt"));
    }
}