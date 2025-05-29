package dataObject.boardObject;

/**
 * This class represent a treasure on the map
 */
public class Treasure implements ICollectible, IHaveTreasure {
    private int remainingTreasures;

    public Treasure(int remainingTreasures) {
        this.remainingTreasures = remainingTreasures;
    }

    public void removeTreasure() {
        remainingTreasures--;
    }

    @Override
    public int getNumberOfTreasure() {
        return remainingTreasures;
    }

    @Override
    public void removeResource() {
        removeTreasure();
    }

    @Override
    public boolean isEmpty() {
        return remainingTreasures == 0;
    }
}
