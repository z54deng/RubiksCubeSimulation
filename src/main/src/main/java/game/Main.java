package game;

import engine.GameEngine;
import engine.IGameLogic;


public class Main {

    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new RubiksCubeGame();
            GameEngine gameEng = new GameEngine("3D Rubik’s Cube Simulator and Solver", 600, 480, vSync, gameLogic);
            gameEng.start();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
//        System.out.println(FindPath.stringToInt("DUUBULDBFRBFRRULLLBRDFFFBLURDBFDFDRFRULBLUFDURRBLBDUDL"));
//        System.out.println(FindPath.stringToInt("UBULURUFURURFRBRDRFUFLFRFDFDFDLDRDBDLULBLFLDLBUBRBLBDB"));
        //System.out.println(FindPath.stringToInt("FBLLURRFBUUFBRFDDFUULLFRDDLRFBLDRFBLUUBFLBDDBUURRBLDDR"));
        //GenerateCube.generate();

    }
}