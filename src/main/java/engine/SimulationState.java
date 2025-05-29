package engine;

import dataObject.Board;
import dataObject.boardObject.IBoardEntity;
import dataObject.boardObject.IMovableBoardEntity;
import dataStructure.Coordinates;

import java.util.LinkedList;
import java.util.Queue;

public class SimulationState {
    private final Board board;
    private final Queue<IMovableBoardEntity> queue = new LinkedList<>();

    public SimulationState(final Board board) {
        this.board = board;
    }

    public void removeObject(final IBoardEntity mapObject) {
        board.removeObject(mapObject);
    }

    public void addObject(final IBoardEntity mapObject, final Coordinates coordinates) {
        if (coordinates.isInvalid(board)) {
            throw new IllegalStateException("Invalid coordinates");
        }
        if (mapObject instanceof IMovableBoardEntity) {
            queue.add((IMovableBoardEntity) mapObject);
        }
        board.addObject(mapObject, coordinates);
    }

    public Board getCarte() {
        return board;
    }

    public Queue<IMovableBoardEntity> getQueue() {
        return queue;
    }
}
