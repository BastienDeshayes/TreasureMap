package engine;

import dataObject.Board;
import dataObject.boardObject.Adventurer;
import dataObject.boardObject.Mountain;
import dataStructure.Coordinates;
import exceptions.IllegalOrientationException;
import exceptions.ImpossibleToAddToBordException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationStateTest {

    @Test
    void addObjectWithCorrectCoordinates() throws IllegalOrientationException {
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

    @Test
    void addImmovableToSameCoordinates() {
        final Board board = new Board(3, 3);
        final SimulationState simulationState = new SimulationState(board);
        final Mountain mountain = new Mountain();
        simulationState.addObject(mountain, new Coordinates(0, 0));
        final Mountain secondMountain = new Mountain();
        assertThrows(ImpossibleToAddToBordException.class, () -> simulationState.addObject(secondMountain, new Coordinates(0, 0)));
    }
}