package dataObject.boardObject;

/**
 * This interface represent entities that can collect a resource (for now only treasure)
 */
public interface ICanCollect extends IBoardEntity {
    void collect(final ICollectible collectible);
}
