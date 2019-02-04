package game.piece;
import engine.graph.Mesh;

/**
 * Created by Adam on 5/19/16.
 */
public class CenterPiece extends Piece {


    public static CenterPiece createCenterPiece(int frontColor) {

        float[] positions = new float[]{
                //Grey Cube
                // FORNT FACE
                // VO
                -0.2f, -0.2f,  0.6f, // -1 , -1 ,3
                // V1
                0.2f, 0.2f,  0.6f,   // 1 ,  1  ,3
                // V2
                -0.2f, 0.2f,  0.6f,  // -1 , 1 , 3
                // V3
                0.2f,  -0.2f,  0.6f, // 1, -1, 3


                //BACK FACE
                // V4
                -0.2f, -0.2f,  0.2f, //-1,  -1 , 1
                // V5
                0.2f, 0.2f,  0.2f, // 1,1,1
                // V6
                -0.2f, 0.2f,  0.2f, // -1,1,1,
                // V7
                0.2f,  -0.2f,  0.2f, // 1,-1,1

                //Colored Panel
                // V8
                -0.195f, -0.195f,  0.601f, //left bottom
                // V9
                0.195f, 0.195f,  0.601f, // right top
                // V10
                -0.195f, 0.195f,  0.601f, // left top
                // V11
                0.195f,  -0.195f,  0.601f, //right bottom

        };
        float[] colors = new float[]{
                0.42f, 0.4f, 0.4f,
                0.4f, 0.42f, 0.4f,
                0.4f, 0.4f, 0.42f,
                0.42f, 0.42f, 0.4f,
                0.42f, 0.42f, 0.42f,
                0.4f, 0.42f, 0.42f,
                0.4f, 0.4f, 0.4f,
                0.42f, 0.4f, 0.4f,
                // Front Panel
                0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f,

        };

        Coloring.frontRecolor(colors,frontColor);

        int[] indices = new int[]{
                //Front
                0,1,2, 0,3,1,
                //Top
                2,5,6, 2,1,5,
                //Back
                6,7,4, 6,5,7,
                //Bottom
                4,3,0, 4,7,3,
                //Left
                4,2,6, 4,0,2,
                //Right
                3,5,1, 3,7,5,
                //Front panel
                8,9,10, 8,11,9
        };

        Mesh mesh = new Mesh(positions,colors,indices);

        return new CenterPiece(mesh);

    }

    private CenterPiece(Mesh mesh) {
        super(mesh);

    }
}