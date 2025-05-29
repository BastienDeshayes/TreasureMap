package dataObject.boardObject;

public abstract class AMovableBoardEntity implements IMovableBoardEntity {
    private String orientation;
    // this is assuming that all the movable entities can go to the 4 cardinal directions
    private String movementSequence;

    AMovableBoardEntity(final String orientation, final String mouvementSequence) {
        this.orientation = orientation;
        this.movementSequence = mouvementSequence;
    }

    @Override
    public String getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    @Override
    public String getMovementSequence() {
        return movementSequence;
    }

    public void setMovementSequence(final String movementSequence) {
        this.movementSequence = movementSequence;
    }
}
