package game.piece;



/**
 * Jimmy Deng, June 07, 2019
 * This class stores the information about the coordinates of each block
 */
public class PieceCoordinate {
    private int x;
    private int y;
    private int z;




    /*
        front face coordinates (z = 0) (red face)
        (x,y)

        0,0  (red,white,green)   1,0 (red,white)     2,0 (red,white,blue)
        0,1  (red,green)         1,1 (red)    2,1 (red,blue)
        0,2  (red,yellow,green)  1,2 (red,yellow)    2,2 (red,yellow,blue)



        middle face coordinates(z = 1) ( middle )
        0,0 (white,green)    1,0  (white)   2,0 (white,blue)
        0,1 (green)          empty              2,1 (blue)
        0,2 (green,yellow)   1,2 (yellow)   2,2 (yellow,blue)

        back face coordinations( z = 2) (orange)
        0,0 (white,orange,green)   1,0 (white,orange)    2,0 (white,orange,blue)
        0,1 (orange,green)         1,1 (orange)   2,1 (orange,blue)
        0,2 (orange, yellow,green)   1,2 (yellow,orange)    2,2 (orange,yellow,blue)


     */
    public PieceCoordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }




}


