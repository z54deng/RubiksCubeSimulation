package game.piece;

import engine.GameItem;
import engine.graph.Mesh;

/**
 * Jimmy Deng, June 07, 2019
 * This class inherits from GameItem, and stores the color of the cube
 */

public class Piece extends GameItem {

    // Follows the order of Red facing User, and White facing up.

    public static final int RED = 0;
    public static final int ORANGE = 1;
    public static final int WHITE = 2;
    public static final int YELLOW = 3;
    public static final int BLUE = 4;
    public static final int GREEN = 5;

    public Piece(Mesh mesh) {
        super(mesh);
    }
}