package game.solver;

import engine.Utils;
import game.PieceManager;
import game.piece.*;

import java.util.Iterator;

public class LoadCube {


//    PieceManager newPieceManager;
//
//    public LoadCube(){
//        newPieceManager = new PieceManager();
//    }

    static int[] colorArr = {Piece.RED, Piece.BLUE, Piece.ORANGE, Piece.GREEN, Piece.RED, Piece.BLUE, Piece.ORANGE, Piece.GREEN};
    static int[] uColor = new int[9];
    static int[] rColor = new int[9];
    static int[] fColor = new int[9];
    static int[] dColor = new int[9];
    static int[] lColor = new int[9];
    static int[] bColor = new int[9];

    public static void setup(){
        for (int i = 0; i < 9; i++) {
            uColor[i] = Piece.WHITE;
            rColor[i] = Piece.BLUE;
            fColor[i] = Piece.RED;
            dColor[i] = Piece.YELLOW;
            lColor[i] = Piece.GREEN;
            bColor[i] = Piece.ORANGE;
        }
    }


    public static String proccessString(){
        try {
            String cubeStr = Utils.loadResource("/cube.txt");
            String[] tempU = cubeStr.substring(0,9).split("");
            String[] tempR = cubeStr.substring(9,18).split("");
            String[] tempF = cubeStr.substring(18,27).split("");
            String[] tempD = cubeStr.substring(27,36).split("");
            String[] tempL = cubeStr.substring(36,45).split("");
            String[] tempB = cubeStr.substring(45,54).split("");
            for (int i = 0; i < 9; i++) {
                uColor[i] = Integer.parseInt(tempU[i]);
                rColor[i] = Integer.parseInt(tempR[i]);
                fColor[i] = Integer.parseInt(tempF[i]);
                dColor[i] = Integer.parseInt(tempD[i]);
                lColor[i] = Integer.parseInt(tempL[i]);
                bColor[i] = Integer.parseInt(tempB[i]);
            }
            return cubeStr;
        } catch(Exception ex){
            System.out.println("FILE NOT FOUND");
        }
        return "";
    }

    public static void loadCenterPieces(Iterator<PieceCoordinate> coordIterator, PieceManager pieceManager){

        for (int i = 0; i < 4; i++) {
            CenterPiece newCenterPiece = CenterPiece.createCenterPiece(colorArr[i]);
            newCenterPiece.setPosition(0, 0, -2);
            newCenterPiece.setRotation(0, -i * 90, 0);
            PieceCoordinate c = coordIterator.next();
            pieceManager.addPiece(newCenterPiece, c);
        }
        {
            //Center piece for White
            CenterPiece whiteCenterPiece = CenterPiece.createCenterPiece(Piece.WHITE);
            whiteCenterPiece.setPosition(0, 0, -2);
            whiteCenterPiece.setRotation(90, 0, 0);
            PieceCoordinate c = coordIterator.next();
            pieceManager.addPiece(whiteCenterPiece, c);
        }
        {
            //Center piece for Yellow
            CenterPiece yellowCenterPiece = CenterPiece.createCenterPiece(Piece.YELLOW);
            yellowCenterPiece.setPosition(0, 0, -2);
            yellowCenterPiece.setRotation(-90, 0, 0);
            PieceCoordinate c = coordIterator.next();
            pieceManager.addPiece(yellowCenterPiece, c);
        }
    }

    public static void loadEdgePieces(Iterator<PieceCoordinate> coordIterator, PieceManager pieceManager){
        //edge piece
        //r b o g
        int[] uEdgeOrder = {uColor[7],uColor[5],uColor[1],uColor[3]};
        int[] uEdgeCorrespond = {fColor[1],rColor[1],bColor[1],lColor[1]};
        for (int i = 0; i < 4; i++) {
            EdgePiece newEdgePiece = EdgePiece.createEdgePiece(uEdgeCorrespond[i], uEdgeOrder[i]);
            newEdgePiece.setPosition(0, 0, -2);
            newEdgePiece.setRotation(0, -i * 90, 90);
            PieceCoordinate c = coordIterator.next();
            pieceManager.addPiece(newEdgePiece, c);
        }
        //r b o g
        int[] dEdgeOrder = {dColor[1],dColor[5],dColor[7],dColor[3]};
        int[] dEdgeCorrespond = {fColor[7],rColor[7],bColor[7],lColor[7]};
        for (int i = 0; i < 4; i++) {
            EdgePiece newEdgePiece = EdgePiece.createEdgePiece(dEdgeCorrespond[i], dEdgeOrder[i]);
            newEdgePiece.setPosition(0, 0, -2);
            newEdgePiece.setRotation(0, -i * 90, -90);
            PieceCoordinate c = coordIterator.next();
            pieceManager.addPiece(newEdgePiece, c);
        }
        int[] mEdgeOrder = {fColor[5],rColor[5],bColor[5],lColor[5]};
        int[] mEdgeCorrespond = {rColor[3],bColor[3],lColor[3],fColor[3]};
        for (int i = 0; i < 4; i++) {
            EdgePiece newEdgePiece = EdgePiece.createEdgePiece(mEdgeOrder[i], mEdgeCorrespond[i]);
            newEdgePiece.setPosition(0, 0, -2);
            newEdgePiece.setRotation(0, -i * 90, 180);
            PieceCoordinate c = coordIterator.next();
            pieceManager.addPiece(newEdgePiece, c);
        }
    }

    public static void loadCornerPieces(Iterator<PieceCoordinate> coordIterator, PieceManager pieceManager){
        //corner piece test

        //r b o g r b o g
        //red, green, white
        //blue, red, white,
        //orange, blue, white
        //green, orange white

        int[] uCornerOrder = {uColor[6],uColor[8],uColor[2],uColor[0]};
        int[] uCornerCorrespond1 = {fColor[0],rColor[0],bColor[0],lColor[0]};
        int[] uCornerCorrespond2 = {lColor[2],fColor[2],rColor[2],bColor[2]};
        for (int i = 0; i < 4; i++) {
            CornerPiece newCornerPiece = CornerPiece.createCornerPiece(uCornerCorrespond1[i], uCornerCorrespond2[i], uCornerOrder[i]);
            newCornerPiece.setPosition(0, 0, -2);
            newCornerPiece.setRotation(0, -i * 90, 0);
            PieceCoordinate c = coordIterator.next();
            pieceManager.addPiece(newCornerPiece, c);
        }

        //red, blue, yellow
        //blue, orange, yellow,
        //orange, green, yellow
        //green, red yellow
        int[] dCornerOrder = {dColor[2],dColor[8],dColor[6],dColor[0]};
        int[] dCornerCorrespond1 = {fColor[8],rColor[8],bColor[8],lColor[8]};
        int[] dCornerCorrespond2 = {rColor[6],bColor[6],lColor[6],fColor[6]};
        for (int i = 0; i < 4; i++) {
            //front side top
            CornerPiece newCornerPiece = CornerPiece.createCornerPiece(dCornerCorrespond1[i], dCornerCorrespond2[i], dCornerOrder[i]);
            newCornerPiece.setPosition(0, 0, -2);
            newCornerPiece.setRotation(180, i * 90 + 180, 0);
            PieceCoordinate c = coordIterator.next();
            pieceManager.addPiece(newCornerPiece, c);
        }
    }

    public void setData(String cubeStr){




    }
}
