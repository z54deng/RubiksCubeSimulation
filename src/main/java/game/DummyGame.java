package game;

import engine.GameItem;
import engine.IGameLogic;
import engine.MouseInput;
import engine.Window;
import engine.graph.Camera;
import game.piece.CenterPiece;
import game.piece.CornerPiece;
import game.piece.EdgePiece;
import game.piece.Piece;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;


public class DummyGame implements IGameLogic {

    private static final float MOUSE_SENSITIVITY = 0.2f;

    private final Vector3f cameraInc;

    private final Renderer renderer;

    private final Camera camera;

    private GameItem[] gameItems;

    private static final float CAMERA_POS_STEP = 0.05f;

    public DummyGame() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector3f(0, 0, 0);
    }
    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        ArrayList<GameItem> pieceList = new ArrayList<>();


        // Center piece for Red, Orange, Blue, Green
        int[] colorArr = {Piece.RED, Piece.BLUE, Piece.ORANGE, Piece.GREEN, Piece.RED, Piece.BLUE, Piece.ORANGE, Piece.GREEN};

        for (int i = 0; i < 4; i++) {
            CenterPiece newCenterPiece = CenterPiece.createCenterPiece(colorArr[i]);
            newCenterPiece.setPosition(0, 0, -2);
            newCenterPiece.setRotation(0,-i*90,0);
            pieceList.add(newCenterPiece);
        }

        //Center piece for White
        CenterPiece whiteCenterPiece = CenterPiece.createCenterPiece(Piece.WHITE);
        whiteCenterPiece.setPosition(0, 0, -2);
        whiteCenterPiece.setRotation(90,0,0);
        pieceList.add(whiteCenterPiece);

        //Center piece for Yellow
        CenterPiece yellowCenterPiece = CenterPiece.createCenterPiece(Piece.YELLOW);
        yellowCenterPiece.setPosition(0, 0, -2);
        yellowCenterPiece.setRotation(-90,0,0);
        pieceList.add(yellowCenterPiece);


        //edge piece
        for (int i = 0; i < 4; i++) {
            EdgePiece newEdgePiece = EdgePiece.createEdgePiece(colorArr[i], Piece.WHITE);
            newEdgePiece.setPosition(0, 0, -2);
            newEdgePiece.setRotation(0,-i*90,90);
            pieceList.add(newEdgePiece);
        }

        for (int i = 0; i < 4; i++) {
            EdgePiece newEdgePiece = EdgePiece.createEdgePiece(colorArr[i], Piece.YELLOW);
            newEdgePiece.setPosition(0, 0, -2);
            newEdgePiece.setRotation(0,-i*90,-90);
            pieceList.add(newEdgePiece);
        }
        for (int i = 0; i < 4; i++) {
            EdgePiece newEdgePiece = EdgePiece.createEdgePiece(colorArr[i],colorArr[i+1]);
            newEdgePiece.setPosition(0, 0, -2);
            newEdgePiece.setRotation(0,-i*90,180);
            pieceList.add(newEdgePiece);
        }

        //corner piece test
        for (int i = 0; i < 4; i++) {
            //front side top
            CornerPiece newCornerPiece = CornerPiece.createCornerPiece(colorArr[i],colorArr[i+3], Piece.WHITE);
            newCornerPiece.setPosition(0, 0, -2);
            newCornerPiece.setRotation(0,-i*90,0);
            pieceList.add(newCornerPiece);
        }

        for (int i = 0; i < 4; i++) {
            //front side top
            CornerPiece newCornerPiece = CornerPiece.createCornerPiece(colorArr[i],colorArr[i+1], Piece.YELLOW);
            newCornerPiece.setPosition(0, 0, -2);
            newCornerPiece.setRotation(180,i*90 + 180,0);
            pieceList.add(newCornerPiece);
        }





        //Center face without rotation
        /*CenterPiece newCenterPiece = CenterPiece.createCenterPiece(Piece.RED);
        newCenterPiece.setPosition(0, 0, -2);
        newCenterPiece.setRotation(0,0,0);
        pieceList.add(newCenterPiece);*/

        //front face left edge without Rotation.
        /*EdgePiece newEdgePiece = EdgePiece.createEdgePiece(Piece.RED,Piece.BLUE);
        newEdgePiece.setPosition(0, 0, -2);
        newEdgePiece.setRotation(0,0,0);
        pieceList.add(newEdgePiece);*/

        //new Corner Piece
       /* CornerPiece newCornerPiece = CornerPiece.createCornerPiece(Piece.BLUE,Piece.RED,Piece.WHITE);
        newCornerPiece.setPosition(0, 0, -2);
        newCornerPiece.setRotation(0,0,0);
        pieceList.add(newCornerPiece);*/

        gameItems = new GameItem[pieceList.size()];
        gameItems = pieceList.toArray(gameItems);
    }

/*    @Override
    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0, 0);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = 1;
        }
    }*/


    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0, 0);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = 1;
        }
    }
    @Override
    public void update(float interval, MouseInput mouseInput) {
        // Update camera position
        this.camera.movePosition(this.cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);

        // Update camera based on mouse
        if (mouseInput.isRightButtonPressed()) {
            Vector2f rotVec = mouseInput.getDisplVec();
            this.camera.moveRotation(rotVec.x * this.MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, this.camera, this.gameItems);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }
}