package main.java.model.constants;

public enum Constants {
    TILE_SIZE(32),
    SCALE(3),
    EMPTY(0),
    PLAYER(1),
    WALL(2),
    SPAWN(3),
    ENEMY(4),
    ITEM(5);

    private final int size;

    Constants(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
