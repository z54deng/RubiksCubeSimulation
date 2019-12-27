package game;

import engine.*;
import engine.graph.Camera;
import game.piece.*;
import game.solver.FindPath;
import game.solver.LoadCube;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Jimmy Deng, June 07, 2019
 * This class uses the provided shaders and renders the data in the gameItem array
 */
public class RubiksCubeGame implements IGameLogic {

    //private static final float MOUSE_SENSITIVITY = 0.2f;
    private static final float ANIMATION_SPEED = 5f;
    private static final int SOLUTION_LENGTH = 22;
    private static final float CAMERA_POS_STEP = 0.05f;
    // TYPES OF MANEUVERS
    private static final int SHIFT = 0;
    private static final int ROTATE = 1;
    private static final int SOLVE = 2;


    private final Vector3f cameraInc;
    private final Renderer renderer;
    private final Camera camera;
    LinkedList<PieceCoordinate> coords;
    //these are testing stuff
    private GameItem[] gameItems;
    private boolean inManeuver = false;
    //private boolean superManeuver = false;
    private int stepsLeft = 0;
    private int superSteps = 0;
    private Matrix4f maneuverMultiplier;
    private PieceManager pieceManager;
    private LinkedList<Piece> piecesInManeuver;
    Iterator<PieceCoordinate> coordIterator;

    Iterator<Integer> solutionIterator;

    public RubiksCubeGame() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector3f(0, 0, 0);
        pieceManager = new PieceManager();
        coords = new LinkedList<>();
        piecesInManeuver = new LinkedList<>();
    }

    private void cameraSetting(){
        pieceManager.allPiece.stream().forEach(piece -> {
            if (piece != null) {
                //set the position and angle of the camera
                this.camera.setPosition((float) 2.3552158, (float) 1.8499994, (float) 0.8242665);
                this.camera.setRotation((float) 30.000008, (float) -37.80002, 0);
                piece.setPosition(0, 0, -2);
            }
        });
    }

    @Override
    public void init(Window window) throws Exception {

        // First set of center pieces
        this.coords.add(new PieceCoordinate(1, 1, 0)); //red  front
        this.coords.add(new PieceCoordinate(2, 1, 1)); //blue  right
        this.coords.add(new PieceCoordinate(1, 1, 2)); //orange  back
        this.coords.add(new PieceCoordinate(0, 1, 1)); //green   left

        // Next 2 center pieces
        this.coords.add(new PieceCoordinate(1, 0, 1));//white  Up
        this.coords.add(new PieceCoordinate(1, 2, 1));//yellow DOWN


        //First set of edge pieces (front face)
        this.coords.add(new PieceCoordinate(1, 0, 0)); //red-white    // front-up
        this.coords.add(new PieceCoordinate(2, 0, 1)); //blue white   // right-up
        this.coords.add(new PieceCoordinate(1, 0, 2)); //orange white // back-up
        this.coords.add(new PieceCoordinate(0, 0, 1)); //green white  // left-up

        // Second set of edge pieces (back face)
        this.coords.add(new PieceCoordinate(1, 2, 0)); //red-yellow   //front-down
        this.coords.add(new PieceCoordinate(2, 2, 1)); //blue yellow  //right-down
        this.coords.add(new PieceCoordinate(1, 2, 2)); //orange yellow //back-down
        this.coords.add(new PieceCoordinate(0, 2, 1)); //green yellow  //left-down

        // Third set of edge pieces (middle slice)
        this.coords.add(new PieceCoordinate(2, 1, 0)); //red-blue      //front-right
        this.coords.add(new PieceCoordinate(2, 1, 2)); //blue orange   //right-back
        this.coords.add(new PieceCoordinate(0, 1, 2));// orange green  //back-left
        this.coords.add(new PieceCoordinate(0, 1, 0));// green red     //left-front


        // First set of Corner pieces (front face)
        this.coords.add(new PieceCoordinate(0, 0, 0)); //white red green
        this.coords.add(new PieceCoordinate(2, 0, 0)); //white blue red
        this.coords.add(new PieceCoordinate(2, 0, 2)); //white orange blue
        this.coords.add(new PieceCoordinate(0, 0, 2)); //white green orange

        //Second set of corner pieces (back face)
        this.coords.add(new PieceCoordinate(2, 2, 0)); //red blue yellow
        this.coords.add(new PieceCoordinate(2, 2, 2)); //blue orange yellow
        this.coords.add(new PieceCoordinate(0, 2, 2)); //orange green yellow
        this.coords.add(new PieceCoordinate(0, 2, 0)); // green red yellow

        coordIterator = this.coords.iterator();

        renderer.init(window);

        LoadCube.setup();
        LoadCube.loadCenterPieces(coordIterator,this.pieceManager);
        LoadCube.loadEdgePieces(coordIterator,this.pieceManager);
        LoadCube.loadCornerPieces(coordIterator,this.pieceManager);

        cameraSetting();

        gameItems = new GameItem[pieceManager.allPiece.size()];
        //System.out.println(gameItems.toString());
        gameItems = pieceManager.allPiece.toArray(gameItems);

    }

    public void input(Window window, MouseInput mouseInput)  {
        // cameraInc.set(0, 0, 0);

        if(superSteps>0 && !inManeuver){
            if(window.isKeyPressed(GLFW_KEY_S)){
                if(this.solutionIterator.hasNext()){
                    int direction = this.solutionIterator.next();
                    startManeuver(this.ROTATE,direction);
                    this.superSteps--;
                }
            }
        }

        else if (!inManeuver && superSteps==0) {
            //Solver
            if(window.isKeyPressed(GLFW_KEY_S)){
                startSuperManeuver(this.SOLVE);
            }
            //COUNTER CLOCKWISE SHIFTS and roatation
            else if (window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
                if (window.isKeyPressed(GLFW_KEY_X)) {
                    startManeuver(this.SHIFT, PieceManager.XPrime);
                } else if (window.isKeyPressed(GLFW_KEY_Y)) {
                    startManeuver(this.SHIFT, PieceManager.YPrime);
                } else if (window.isKeyPressed(GLFW_KEY_Z)) {
                    startManeuver(this.SHIFT, PieceManager.ZPrime);
                } else if (window.isKeyPressed(GLFW_KEY_U)) {
                    startManeuver(this.ROTATE, PieceManager.UPrime);
                }
                else if (window.isKeyPressed(GLFW_KEY_D)) {
                    startManeuver(this.ROTATE, PieceManager.DPrime);
                } else if (window.isKeyPressed(GLFW_KEY_F)) {
                    startManeuver(this.ROTATE, PieceManager.FPrime);
                } else if (window.isKeyPressed(GLFW_KEY_B)) {
                    startManeuver(this.ROTATE, PieceManager.BPrime);
                } else if (window.isKeyPressed(GLFW_KEY_R)) {
                    startManeuver(this.ROTATE, PieceManager.RPrime);
                } else if (window.isKeyPressed(GLFW_KEY_L)) {
                    startManeuver(this.ROTATE, PieceManager.LPrime);
                }
            }
            //clockwise rotation and shift
            else if (window.isKeyPressed(GLFW_KEY_X)) {
                startManeuver(this.SHIFT, PieceManager.X);
            } else if (window.isKeyPressed(GLFW_KEY_Y)) {
                startManeuver(this.SHIFT, PieceManager.Y);
            } else if (window.isKeyPressed(GLFW_KEY_Z)) {
                startManeuver(this.SHIFT, PieceManager.Z);
            }
            //Rotation
            else if (window.isKeyPressed(GLFW_KEY_U)) {
                startManeuver(this.ROTATE, PieceManager.U);
            } else if (window.isKeyPressed(GLFW_KEY_D)) {
                startManeuver(this.ROTATE, PieceManager.D);
            } else if (window.isKeyPressed(GLFW_KEY_F)) {
                startManeuver(this.ROTATE, PieceManager.F);
            } else if (window.isKeyPressed(GLFW_KEY_B)) {
                startManeuver(this.ROTATE, PieceManager.B);
            } else if (window.isKeyPressed(GLFW_KEY_R)) {
                startManeuver(this.ROTATE, PieceManager.R);
            } else if (window.isKeyPressed(GLFW_KEY_L)) {
                startManeuver(this.ROTATE, PieceManager.L);
            }
        }
    }


    //SOLVE
    private void startSuperManeuver(int type){
        if(type == this.SOLVE){
            for (GameItem gameItem : gameItems) {
                gameItem.setScale(0);
            }
           // renderer.init(window);
            this.coordIterator = this.coords.iterator();
            LoadCube.setup();
            // set the color according to file, and return the color string (int integer format)
            String intString = LoadCube.proccessString();
            //set the data for new cube
            LoadCube.loadCenterPieces(coordIterator,this.pieceManager);
            LoadCube.loadEdgePieces(coordIterator,this.pieceManager);
            LoadCube.loadCornerPieces(coordIterator,this.pieceManager);
            cameraSetting();
            gameItems = new GameItem[pieceManager.allPiece.size()];
            //System.out.println(gameItems.toString());
            gameItems = pieceManager.allPiece.toArray(gameItems);
            //
            //System.out.println(intString);
            String cubeStr = FindPath.intToString(intString);
            String[] unformattedSolution = FindPath.findSolution(cubeStr,SOLUTION_LENGTH);
            List<Integer> solutionList = FindPath.format(unformattedSolution);
            this.solutionIterator = solutionList.iterator();
            this.superSteps = solutionList.size();
//            if(this.solutionIterator.hasNext())
//                System.out.println(solutionIterator.next());


//            for (Integer direction : solutionList) {
//                if(!inManeuver)
//                    startManeuver(this.ROTATE, direction);
//            }
        }
    }
    //SHIFT AND ROTATION
    private void startManeuver(int type, int direction) {
        this.inManeuver = true;
        stepsLeft = (int) (90f / ANIMATION_SPEED);
        //type 0 is shift all, type 1 in rotate
        if (type == SHIFT) {
            if (direction == PieceManager.X) {
                maneuverMultiplier = GameItem.getRotationMultiplier(ANIMATION_SPEED, 0, 0);
            } else if (direction == PieceManager.XPrime) {
                maneuverMultiplier = GameItem.getRotationMultiplier(-ANIMATION_SPEED, 0, 0);
            } else if (direction == PieceManager.Y) {
                maneuverMultiplier = GameItem.getRotationMultiplier(0, ANIMATION_SPEED, 0);
            } else if (direction == PieceManager.YPrime) {
                maneuverMultiplier = GameItem.getRotationMultiplier(0, -ANIMATION_SPEED, 0);
            } else if (direction == PieceManager.Z) {
                maneuverMultiplier = GameItem.getRotationMultiplier(0, 0, ANIMATION_SPEED);
            } else if (direction == PieceManager.ZPrime) {
                maneuverMultiplier = GameItem.getRotationMultiplier(0, 0, -ANIMATION_SPEED);
            }

            piecesInManeuver = pieceManager.getAllPieces();
            pieceManager.shiftAll(direction);
        }

        //TEST
        else if (type == ROTATE) {
            if (direction == PieceManager.U) {
                maneuverMultiplier = GameItem.getRotationMultiplier(0, ANIMATION_SPEED, 0);
                piecesInManeuver = pieceManager.getHorizontalSlice(PieceManager.topLayer);
            } else if (direction == PieceManager.UPrime) {
                maneuverMultiplier = GameItem.getRotationMultiplier(0, -ANIMATION_SPEED, 0);
                piecesInManeuver = pieceManager.getHorizontalSlice(PieceManager.topLayer);
            }
            //bottom layer
            else if (direction == PieceManager.D) {
                maneuverMultiplier = GameItem.getRotationMultiplier(0, -ANIMATION_SPEED, 0);
                piecesInManeuver = pieceManager.getHorizontalSlice(PieceManager.bottomLayer);
            } else if (direction == PieceManager.DPrime) {
                maneuverMultiplier = GameItem.getRotationMultiplier(0, ANIMATION_SPEED, 0);
                piecesInManeuver = pieceManager.getHorizontalSlice(PieceManager.bottomLayer);
            }
            //front layer clockwise
            else if (direction == PieceManager.F) {
                maneuverMultiplier = GameItem.getRotationMultiplier(0, 0, ANIMATION_SPEED);
                piecesInManeuver = pieceManager.getFrontFace(PieceManager.frontLayer);
            } else if (direction == PieceManager.FPrime) {
                maneuverMultiplier = GameItem.getRotationMultiplier(0, 0, -ANIMATION_SPEED);
                piecesInManeuver = pieceManager.getFrontFace(PieceManager.frontLayer);
            }
            //back layer clockwise
            else if (direction == PieceManager.B) {
                maneuverMultiplier = GameItem.getRotationMultiplier(0, 0, -ANIMATION_SPEED);
                piecesInManeuver = pieceManager.getFrontFace(PieceManager.backLayer);
            } else if (direction == PieceManager.BPrime) {
                maneuverMultiplier = GameItem.getRotationMultiplier(0, 0, ANIMATION_SPEED);
                piecesInManeuver = pieceManager.getFrontFace(PieceManager.backLayer);
            }
            //right layer clockwise
            else if (direction == PieceManager.R) {
                maneuverMultiplier = GameItem.getRotationMultiplier(ANIMATION_SPEED, 0, 0);
                piecesInManeuver = pieceManager.getVerticalSlice(PieceManager.rightSlice);
            } else if (direction == PieceManager.RPrime) {
                maneuverMultiplier = GameItem.getRotationMultiplier(-ANIMATION_SPEED, 0, 0);
                piecesInManeuver = pieceManager.getVerticalSlice(PieceManager.rightSlice);
            }
            //left layer clockwise
            else if (direction == PieceManager.L) {
                maneuverMultiplier = GameItem.getRotationMultiplier(-ANIMATION_SPEED, 0, 0);
                piecesInManeuver = pieceManager.getVerticalSlice(PieceManager.leftSlice);
            } else if (direction == PieceManager.LPrime) {
                maneuverMultiplier = GameItem.getRotationMultiplier(ANIMATION_SPEED, 0, 0);
                piecesInManeuver = pieceManager.getVerticalSlice(PieceManager.leftSlice);
            }
        }
        pieceManager.rotate(direction);
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        // Update camera position
        this.camera.movePosition(this.cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);


        if (stepsLeft == 0) {
            inManeuver = false;
        }
        else if (inManeuver) {
            piecesInManeuver.stream().forEach(piece -> {
                if (piece != null)
                    piece.addRotation(maneuverMultiplier);
            });
            stepsLeft--;
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