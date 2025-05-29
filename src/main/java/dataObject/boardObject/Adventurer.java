package dataObject.boardObject;

import exceptions.IllegalOrientationException;

/**
 * This class represent an adventurer on the map
 */
public class Adventurer extends AMovableBoardEntity implements IObstacle, ICanCollect, IHaveTreasure {
    private final String name;
    private int numberOfTreasuresCollected = 0;

    public Adventurer(final String name, final String orientation, final String mouvementSequence) throws IllegalOrientationException {
        super(orientation, mouvementSequence);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getNumberOfTreasure() {
        return numberOfTreasuresCollected;
    }

    @Override
    public void collect(final ICollectible collectible) {
        collectible.removeResource();
        numberOfTreasuresCollected++;
    }
}
