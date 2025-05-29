package dataObject.boardObject;

/**
 * This interface represent entities that can be collected (for now only treasures)
 */
public interface ICollectible extends IBoardEntity {
    void removeResource();
    boolean isEmpty();
}
