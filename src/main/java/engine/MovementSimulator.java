package engine;

import dataObject.boardObject.*;
import dataStructure.Coordinates;
import exceptions.IllegalOrientationException;

import java.util.List;

/**
 * This class is used to simulate movement on the map
 */
public class MovementSimulator {
    SimulationState simulationState;

    public MovementSimulator(SimulationState simulationState) {
        this.simulationState = simulationState;
    }
    // start simulation engine
    public void simulate() throws IllegalOrientationException {
        while (!isLastTurn()) {
            executeOneTurn();
        }
    }

    private boolean isLastTurn() {
        return simulationState.getQueue().stream().filter(e -> e instanceof final IMovableBoardEntity movableMapObject && movableMapObject.getMovementSequence().isEmpty()).toList().size() == simulationState.getQueue().size();
    }

    private void executeOneTurn() throws IllegalOrientationException {
        int queueSize = this.simulationState.getQueue().size();
        for (int i = 0; i < queueSize; i++) {
            final IMovableBoardEntity movableMapObject = this.simulationState.getQueue().poll();
            moveAdventurerFromMap(movableMapObject);
        }
    }

    // end simulation engine

    private void moveAdventurerFromMap(final IMovableBoardEntity adventurer) throws IllegalOrientationException {
        final String movementSequence = adventurer.getMovementSequence();
        if (movementSequence != null && !movementSequence.isEmpty()) {
            adventurer.setMovementSequence(adventurer.getMovementSequence().substring(1));
            switch (movementSequence.substring(0, 1)) {
                case "A":
                    Coordinates newCoordinates = calculateCoordinates(adventurer);
                    if (isMoveBlocking(adventurer, newCoordinates)) {
                        moveAdventurerFromMap(adventurer);
                    } else {
                        moveAdventurerForward(adventurer, newCoordinates);
                    }
                    break;
                case "G":
                    turnLeft(adventurer);

                    simulationState.getQueue().offer(adventurer);
                    break;
                case "D":
                    turnRight(adventurer);
                    simulationState.getQueue().offer(adventurer);
                    break;
                default:
                    break;
            }
        }
    }

    private Coordinates calculateCoordinates(final IMovableBoardEntity adventurer) {
        final Coordinates coordinates = simulationState.getCarte().getCoordinatesFromObject(adventurer);
        final String orientation = adventurer.getOrientation();
        return switch (orientation) {
            case "N" -> new Coordinates(coordinates.x(), coordinates.y() - 1);
            case "S" -> new Coordinates(coordinates.x(), coordinates.y() + 1);
            case "E" -> new Coordinates(coordinates.x() + 1, coordinates.y());
            case "O" -> new Coordinates(coordinates.x() - 1, coordinates.y());
            default -> throw new IllegalStateException("Invalid orientation");
        };
    }

    private boolean isMoveBlocking(final IMovableBoardEntity adventurer, final Coordinates coordinates) {
        if (coordinates.isInvalid(simulationState.getCarte())) {
            return true;
        }
        final List<IBoardEntity> entities = simulationState.getCarte().getObjectAtCoordinates(coordinates);
        return !entities.stream().filter(entity -> entity instanceof IObstacle).toList().isEmpty();
    }

    private void moveAdventurerForward(final IMovableBoardEntity adventurer, final Coordinates newCoordinates) {
        if (adventurer instanceof final ICanCollect entityAbleToCollect) {
            final List<IBoardEntity> entities = simulationState.getCarte().getObjectAtCoordinates(newCoordinates);
            for (IBoardEntity entity : entities) {
                if (entity instanceof final ICollectible collectible) {
                    entityAbleToCollect.collect(collectible);
                    if (collectible.isEmpty()) {
                        simulationState.removeObject(entity);
                    }
                }
            }
        }
        simulationState.removeObject(adventurer);
        simulationState.addObject(adventurer, newCoordinates);
    }

    private void turnLeft(final IMovableBoardEntity movableMapObject) throws IllegalOrientationException {
        switch (movableMapObject.getOrientation()) {
            case "N":
                movableMapObject.setOrientation("O");
                break;
            case "E":
                movableMapObject.setOrientation("N");
                break;
            case "S":
                movableMapObject.setOrientation("E");
                break;
            case "O":
                movableMapObject.setOrientation("S");
                break;
            default:
                throw new IllegalOrientationException("This orientation is not available. Available orientations are: N, S, E, O");
        }
    }

    private void turnRight(final IMovableBoardEntity movableMapObject) throws IllegalOrientationException {
        switch (movableMapObject.getOrientation()) {
            case "N":
                movableMapObject.setOrientation("E");
                break;
            case "E":
                movableMapObject.setOrientation("S");
                break;
            case "S":
                movableMapObject.setOrientation("O");
                break;
            case "O":
                movableMapObject.setOrientation("N");
                break;
            default:
                throw new IllegalOrientationException("This orientation is not available. Available orientations are: N, S, E, O");
        }
    }
}
