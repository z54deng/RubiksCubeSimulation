package game.piece;

import engine.graph.Mesh;

/**
 * Created by Adam on 5/26/16.
 */
public class EdgePiece extends Piece {

    public static EdgePiece createEdgePiece(int frontColor, int sideColor) {

        float[] positions = new float[]{
                //Grey Cube
                // VO
                -0.6f, -0.2f,  0.6f, // -3,-1,3
                // V1
                -0.2f, 0.2f,  0.6f, // -1,1,3
                // V2
                -0.6f, 0.2f,  0.6f, //-3,1,3
                // V3
                -0.2f,  -0.2f,  0.6f, // -1,-1,3


                // V4
                -0.6f, -0.2f,  0.2f, //-3,-1,1
                // V5
                -0.2f, 0.2f,  0.2f, // -1, 1 ,1
                // V6
                -0.6f, 0.2f,  0.2f, // -3, 1, 1
                // V7
                -0.2f,  -0.2f,  0.2f, //-1,-1,1


                //Colored Front Panel
                // V8
                -0.6f, -0.195f,  0.601f, // left bottom
                // V9
                -0.205f, 0.195f,  0.601f, // right top
                // V10
                -0.6f, 0.195f,  0.601f, // left top
                // V11
                -0.205f,  -0.195f,  0.601f, //right left

                //Colored Side Panel
                // V12
                -0.601f, -0.195f,  0.205f, // bottom inside
                // V13
                -0.601f, 0.195f,  0.6f, // top outside
                // V14
                -0.601f, 0.195f,  0.205f, // top inside
                // V15
                -0.601f,  -0.195f,  0.6f, // bottom outside

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

        };

        // FRONT PIECE RECOLOR
        Coloring.frontRecolor(colors,frontColor);

        // SIDE PIECE RECOLOR
        Coloring.sideRecolor(colors,sideColor);


        //

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
        };

        Mesh mesh = new Mesh(positions,colors,indices);
        return new EdgePiece(mesh);
    }

    public EdgePiece(Mesh mesh) {
        super(mesh);
    }
}