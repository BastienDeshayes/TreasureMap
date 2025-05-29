package engine;

import dataObject.Board;
import dataObject.boardObject.Adventurer;
import dataStructure.Coordinates;
import exceptions.IllegalOrientationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationStateTest {

    @Test
    void addObjectWithCorectCoordinates() throws IllegalOrientationException {
        final Board board = new Board(3, 3);
        final SimulationState simulationState = new SimulationState(board);
        final Adventurer adventurer = new Adventurer("Toto", "E", "AADA");
        simulationState.addObject(adventurer, new Coordinates(2, 1));
        final Coordinates coordinates = board.getMap().get(adventurer);
        assertNotNull(coordinates);
        assertEquals(2, coordinates.x());
        assertEquals(1, coordinates.y());
    }

    @Test
    void addObjectWithIncorrectCoordinates() throws IllegalOrientationException {
        final Board board = new Board(3, 3);
        final SimulationState simulationState = new SimulationState(board);
        final Adventurer adventurer = new Adventurer("Toto", "E", "AADA");
        assertThrows(IllegalStateException.class, () -> simulationState.addObject(adventurer, new Coordinates(-2, -1)));
    }
}