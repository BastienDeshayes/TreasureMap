package dataObject;

import dataObject.boardObject.IBoardEntity;
import dataStructure.Coordinates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represent the Board of the island
 */
public class Board {
    private final Map<IBoardEntity, Coordinates> map = new HashMap<>();
    private final int horizontalLength;
    private final int verticalLength;

    // Constructor for the map from text file
    public Board(final int horizontalLength, final int verticalLength) {
        this.horizontalLength = horizontalLength;
        this.verticalLength = verticalLength;
    }

    public Map<IBoardEntity, Coordinates> getMap() {
        return map;
    }

    public int getHorizontalLength() {
        return horizontalLength;
    }

    public int getVerticalLength() {
        return verticalLength;
    }

    /**
     *
     * @return All the object sorted by coordonates x axis then y axis
     */
    public List<Map.Entry<IBoardEntity, Coordinates>> getAllObjectSortedByCoordonate() {
        return map.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
    }

    /**
     *
     * @param mapObject the object we want to retrieve the coordinate from
     * @return The coordinate of the specified object
     */
    public Coordinates getCoordinatesFromObject(final IBoardEntity mapObject) {
        return map.get(mapObject);
    }

    /**
     *
     * @param coordinates the coordinate we want to get the object from
     * @return the objects present to the given coordinates
     */
    public List<IBoardEntity> getObjectAtCoordinates(final Coordinates coordinates) {
        final List<IBoardEntity> objects = new ArrayList<>();
        for (Map.Entry<IBoardEntity, Coordinates> mapObjectEntry : map.entrySet()) {
            if (mapObjectEntry.getValue().equals(coordinates)) {
                objects.add(mapObjectEntry.getKey());
            }
        }
        return objects;
    }

    public void removeObject(IBoardEntity mapObject) {
        map.remove(mapObject);
    }

    public void addObject(IBoardEntity mapObject, Coordinates coordinates) {
        map.put(mapObject, coordinates);
    }
}
