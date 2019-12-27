package game.piece;
import engine.graph.Mesh;

/**
 * Jimmy Deng, June 07, 2019
 * Corner piece vertex values
 */
public class CornerPiece extends Piece {

    public static CornerPiece createCornerPiece(int frontColor, int sideColor, int topColor) {

        float[] positions = new float[]{
                //Grey Cube
                // VO
                -0.6f, 0.2f,  0.6f, //  -3, 1 ,3
                // V1
                -0.2f, 0.6f,  0.6f,  //-1,3,3
                // V2
                -0.6f, 0.6f,  0.6f, //-1 ,1 , 1
                // V3
                -0.2f,  0.2f,  0.6f,


                // V4
                -0.6f, 0.2f,  0.2f,
                // V5
                -0.2f, 0.6f,  0.2f,
                // V6
                -0.6f, 0.6f,  0.2f,
                // V7
                -0.2f,  0.2f,  0.2f,

                //Colored Front Panel
                // V8
                -0.6f, 0.205f,  0.601f,  // bottom left
                // V9
                -0.205f, 0.6f,  0.601f,  // top right
                // V10
                -0.6f, 0.6f,  0.601f,   //top let
                // V11
                -0.205f,  0.205f,  0.601f, // bottom right

                //Colored Side Panel (left)
                // V12
                -0.601f, 0.205f,  0.205f, // bottom far
                // V13
                -0.601f, 0.6f,  0.6f, // top near
                // V14
                -0.601f, 0.6f,  0.205f, //top, far
                // V15
                -0.601f,  0.205f,  0.6f, // bottom near

                //Colored Top Panel
                // V16
                -0.6f, 0.601f,  0.6f, // left near
                // V17
                -0.205f, 0.601f,  0.205f, // right far
                // V18
                -0.6f, 0.601f,  0.205f, // left far
                // V19
                -0.205f,  0.601f,  0.6f // right near

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
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                //Side Panel
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                // Top Panel
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,

        };

        // FRONT PIECE RECOLOR
        Coloring.frontRecolor(colors,frontColor);

        // SIDE PIECE RECOLOR
        Coloring.sideRecolor(colors,sideColor);

        // Top Piece Recolor
        Coloring.topRecolor(colors,topColor);


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
                8,9,10, 8,11,9,
                //Side panel
                12,13,14, 12,15,13,
                //Top panel
                16,17,18, 16,19,17,
        };




        Mesh mesh = new Mesh(positions,colors,indices);
        return new CornerPiece(mesh);
    }

    public CornerPiece(Mesh mesh) {
        super(mesh);
    }
}