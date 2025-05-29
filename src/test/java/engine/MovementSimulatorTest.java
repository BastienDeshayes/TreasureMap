package engine;

import dataObject.Board;
import dataObject.boardObject.Adventurer;
import dataObject.boardObject.Mountain;
import dataObject.boardObject.Treasure;
import dataStructure.Coordinates;
import exceptions.IllegalOrientationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementSimulatorTest {

    @Test
    void simulateSimpleCase() throws IllegalOrientationException {
        final Board board = new Board(3, 3);
        final SimulationState simulationState = new SimulationState(board);
        final Adventurer adventurer = new Adventurer("Toto", "S", "AGADA");
        simulationState.addObject(adventurer, new Coordinates(1, 0));
        final MovementSimulator simulation = new MovementSimulator(simulationState);
        simulation.simulate();
        final Coordinates coordinates = simulationState.getCarte().getCoordinatesFromObject(adventurer);
        assertNotNull(coordinates);
        assertEquals(2, coordinates.y());
        assertEquals(2, coordinates.x());
    }

    @Test
    void simulateAdventurerCollectTreasure() throws IllegalOrientationException {
        final Board board = new Board(3, 3);
        final SimulationState simulationState = new SimulationState(board);
        final Adventurer adventurer = new Adventurer("Toto", "S", "AGADA");
        simulationState.addObject(adventurer, new Coordinates(1, 0));
        final Treasure treasure = new Treasure(3);
        simulationState.addObject(treasure, new Coordinates(1, 1));
        final MovementSimulator simulation = new MovementSimulator(simulationState);
        simulation.simulate();
        final Coordinates coordinates = simulationState.getCarte().getCoordinatesFromObject(adventurer);
        assertNotNull(coordinates);
        assertEquals(2, coordinates.y());
        assertEquals(2, coordinates.x());
        assertEquals(2, treasure.getNumberOfTreasure());
        assertEquals(1, adventurer.getNumberOfTreasure());
    }

    @Test
    void simulateAdventurerCollectTreasureWithTreasureDisappearing() throws IllegalOrientationException {
        final Board board = new Board(3, 3);
        final SimulationState simulationState = new SimulationState(board);
        final Adventurer adventurer = new Adventurer("Toto", "S", "AGADA");
        simulationState.addObject(adventurer, new Coordinates(1, 0));
        final Treasure treasure = new Treasure(1);
        simulationState.addObject(treasure, new Coordinates(1, 1));
        final MovementSimulator simulation = new MovementSimulator(simulationState);
        simulation.simulate();
        final Coordinates coordinates = simulationState.getCarte().getCoordinatesFromObject(adventurer);
        assertNotNull(coordinates);
        assertEquals(2, coordinates.y());
        assertEquals(2, coordinates.x());
        assertEquals(0, treasure.getNumberOfTreasure());
        assertNull(simulationState.getCarte().getCoordinatesFromObject(treasure));
        assertEquals(1, adventurer.getNumberOfTreasure());
    }

    @Test
    void simulateAdventurerCollectMultipleTreasure() throws IllegalOrientationException {
        final Board board = new Board(3, 3);
        final SimulationState simulationState = new SimulationState(board);
        final Adventurer adventurer = new Adventurer("Toto", "S", "AAGGAA");
        simulationState.addObject(adventurer, new Coordinates(1, 0));
        final Treasure treasure = new Treasure(3);
        simulationState.addObject(treasure, new Coordinates(1, 1));
        final MovementSimulator simulation = new MovementSimulator(simulationState);
        simulation.simulate();
        final Coordinates coordinates = simulationState.getCarte().getCoordinatesFromObject(adventurer);
        assertNotNull(coordinates);
        assertEquals(0, coordinates.y());
        assertEquals(1, coordinates.x());
        assertEquals(1, treasure.getNumberOfTreasure());
        assertEquals(2, adventurer.getNumberOfTreasure());
    }

    @Test
    void simulateAdventurerCollectStayingOnTreasure() throws IllegalOrientationException {
        final Board board = new Board(3, 3);
        final SimulationState simulationState = new SimulationState(board);
        final Adventurer adventurer = new Adventurer("Toto", "S", "AGGDDA");
        simulationState.addObject(adventurer, new Coordinates(1, 0));
        final Treasure treasure = new Treasure(3);
        simulationState.addObject(treasure, new Coordinates(1, 1));
        final MovementSimulator simulation = new MovementSimulator(simulationState);
        simulation.simulate();
        final Coordinates coordinates = simulationState.getCarte().getCoordinatesFromObject(adventurer);
        assertNotNull(coordinates);
        assertEquals(2, coordinates.y());
        assertEquals(1, coordinates.x());
        assertEquals(2, treasure.getNumberOfTreasure());
        assertEquals(1, adventurer.getNumberOfTreasure());
    }

    @Test
    void simulateAdventurerBlockedByMountain() throws IllegalOrientationException {
        final Board board = new Board(3, 3);
        final SimulationState simulationState = new SimulationState(board);
        final Adventurer adventurer = new Adventurer("Toto", "S", "AAAGA");
        simulationState.addObject(adventurer, new Coordinates(1, 0));
        final Mountain mountain = new Mountain();
        simulationState.addObject(mountain, new Coordinates(1, 1));
        final MovementSimulator simulation = new MovementSimulator(simulationState);
        simulation.simulate();
        final Coordinates coordinates = simulationState.getCarte().getCoordinatesFromObject(adventurer);
        assertNotNull(coordinates);
        assertEquals(0, coordinates.y());
        assertEquals(2, coordinates.x());
    }

    @Test
    void simulateTwoAdventurersWithConflict() throws IllegalOrientationException {
        final Board board = new Board(3, 3);
        final SimulationState simulationState = new SimulationState(board);
        final Adventurer adventurer = new Adventurer("Toto", "E", "AADA");
        simulationState.addObject(adventurer, new Coordinates(0, 0));
        final Adventurer secondAdventurer = new Adventurer("Tata", "O", "AAGA");
        simulationState.addObject(secondAdventurer, new Coordinates(2, 0));
        final MovementSimulator simulation = new MovementSimulator(simulationState);
        simulation.simulate();
        final Coordinates coordinatesFirstAdventurer = simulationState.getCarte().getCoordinatesFromObject(adventurer);
        assertNotNull(coordinatesFirstAdventurer);
        assertEquals(1, coordinatesFirstAdventurer.y());
        assertEquals(1, coordinatesFirstAdventurer.x());
        final Coordinates coordinatesSecondAdventurer = simulationState.getCarte().getCoordinatesFromObject(secondAdventurer);
        assertNotNull(coordinatesSecondAdventurer);
        assertEquals(1, coordinatesSecondAdventurer.y());
        assertEquals(2, coordinatesSecondAdventurer.x());
    }

    @Test
    void simulateTwoAdventurersWithOneOnATreasureTheOtherMovingOnTreasure() throws IllegalOrientationException {
        final Board board = new Board(3, 3);
        final SimulationState simulationState = new SimulationState(board);
        final Adventurer adventurer = new Adventurer("Toto", "E", "AA");
        simulationState.addObject(adventurer, new Coordinates(1, 0));
        final Adventurer secondAdventurer = new Adventurer("Tata", "E", "AA");
        simulationState.addObject(secondAdventurer, new Coordinates(0, 0));
        final Treasure treasure = new Treasure(3);
        simulationState.addObject(treasure, new Coordinates(2, 0));
        final MovementSimulator simulation = new MovementSimulator(simulationState);
        simulation.simulate();
        final Coordinates coordinatesFirstAdventurer = simulationState.getCarte().getCoordinatesFromObject(adventurer);
        assertNotNull(coordinatesFirstAdventurer);
        assertEquals(0, coordinatesFirstAdventurer.y());
        assertEquals(2, coordinatesFirstAdventurer.x());
        assertEquals(1, adventurer.getNumberOfTreasure());
        final Coordinates coordinatesSecondAdventurer = simulationState.getCarte().getCoordinatesFromObject(secondAdventurer);
        assertNotNull(coordinatesSecondAdventurer);
        assertEquals(0, coordinatesSecondAdventurer.y());
        assertEquals(1, coordinatesSecondAdventurer.x());
        assertEquals(0, secondAdventurer.getNumberOfTreasure());

        assertEquals(2, treasure.getNumberOfTreasure());
    }

    @Test
    void simulateTwoAdventurersMoveInCorrectOrder() throws IllegalOrientationException {
        final Board board = new Board(3, 3);
        final SimulationState simulationState = new SimulationState(board);
        final Adventurer adventurer = new Adventurer("Toto", "E", "AA");
        simulationState.addObject(adventurer, new Coordinates(0, 0));
        final Adventurer secondAdventurer = new Adventurer("Tata", "E", "AA");
        simulationState.addObject(secondAdventurer, new Coordinates(1, 0));
        final MovementSimulator simulation = new MovementSimulator(simulationState);
        simulation.simulate();
        final Coordinates coordinatesFirstAdventurer = simulationState.getCarte().getCoordinatesFromObject(adventurer);
        assertNotNull(coordinatesFirstAdventurer);
        assertEquals(0, coordinatesFirstAdventurer.y());
        assertEquals(0, coordinatesFirstAdventurer.x());
        final Coordinates coordinatesSecondAdventurer = simulationState.getCarte().getCoordinatesFromObject(secondAdventurer);
        assertNotNull(coordinatesSecondAdventurer);
        assertEquals(0, coordinatesSecondAdventurer.y());
        assertEquals(2, coordinatesSecondAdventurer.x());
    }
}