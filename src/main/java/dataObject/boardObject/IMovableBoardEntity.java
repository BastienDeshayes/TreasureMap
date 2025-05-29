package dataObject.boardObject;

/**
 * this interface represent entities that can move on the board
 */
public interface IMovableBoardEntity extends IBoardEntity {
    String getMovementSequence();
    void setMovementSequence(String movementSequence);
    String getOrientation();
    void setOrientation(String orientation);
}
