package game.piece;


/**
 * Jimmy Deng, June 07, 2019
 * defines the color information
 */
public class Coloring {
    public static void frontRecolor(float[] colors,int frontColor) {
        if (frontColor== Piece.RED) {
            for (int i = 24; i <= 33; i+=3) {
                colors[i] = 1.0f;
                colors[i+1] = 0.0f;
                colors[i+2] = 0.0f;
            }
        }
        else if (frontColor== Piece.ORANGE) {
            for (int i = 24; i <= 33; i+=3) {
                colors[i] = 1.0f;
                colors[i+1] = 0.5f;
                colors[i+2] = 0.0f;
            }
        }
        else if (frontColor== Piece.WHITE) {
            for (int i = 24; i <= 33; i+=3) {
                colors[i] = 1.0f;
                colors[i+1] = 1.0f;
                colors[i+2] = 1.0f;
            }
        }
        else if (frontColor== Piece.YELLOW) {
            for (int i = 24; i <= 33; i+=3) {
                colors[i] = 1.0f;
                colors[i+1] = 1.0f;
                colors[i+2] = 0.0f;
            }
        }
        else if (frontColor== Piece.BLUE) {
            for (int i = 24; i <= 33; i+=3) {
                colors[i] = 0.0f;
                colors[i+1] = 0.0f;
                colors[i+2] = 1.0f;
            }
        }
        else if (frontColor== Piece.GREEN) {
            for (int i = 24; i <= 33; i+=3) {
                colors[i] = 0.0f;
                colors[i+1] = 1.0f;
                colors[i+2] = 0.0f;
            }
        }
    }

    public static void sideRecolor(float[] colors,int sideColor) {
        if (sideColor== Piece.RED) {
            for (int i = 36; i <= 45; i+=3) {
                colors[i] = 1.0f;
                colors[i+1] = 0.0f;
                colors[i+2] = 0.0f;
            }
        }
        else if (sideColor== Piece.ORANGE) {
            for (int i = 36; i <= 45; i+=3) {
                colors[i] = 1.0f;
                colors[i+1] = 0.5f;
                colors[i+2] = 0.0f;
            }
        }
        else if (sideColor== Piece.WHITE) {
            for (int i = 36; i <= 45; i+=3) {
                colors[i] = 1.0f;
                colors[i+1] = 1.0f;
                colors[i+2] = 1.0f;
            }
        }
        else if (sideColor== Piece.YELLOW) {
            for (int i = 36; i <= 45; i+=3) {
                colors[i] = 1.0f;
                colors[i+1] = 1.0f;
                colors[i+2] = 0.0f;
            }
        }
        else if (sideColor== Piece.BLUE) {
            for (int i = 36; i <= 45; i+=3) {
                colors[i] = 0.0f;
                colors[i+1] = 0.0f;
                colors[i+2] = 1.0f;
            }
        }
        else if (sideColor== Piece.GREEN) {
            for (int i = 36; i <= 45; i+=3) {
                colors[i] = 0.0f;
                colors[i+1] = 1.0f;
                colors[i+2] = 0.0f;
            }
        }
    }

    public static void topRecolor(float[] colors, int topColor){
        if (topColor== Piece.RED) {
            for (int i = 48; i <= 57; i+=3) {
                colors[i] = 1.0f;
                colors[i+1] = 0.0f;
                colors[i+2] = 0.0f;
            }
        }
        else if (topColor== Piece.ORANGE) {
            for (int i = 48; i <= 57; i+=3) {
                colors[i] = 1.0f;
                colors[i+1] = 0.5f;
                colors[i+2] = 0.0f;
            }
        }
        else if (topColor== Piece.WHITE) {
            for (int i = 48; i <= 57; i+=3) {
                colors[i] = 1.0f;
                colors[i+1] = 1.0f;
                colors[i+2] = 1.0f;
            }
        }
        else if (topColor== Piece.YELLOW) {
            for (int i = 48; i <= 57; i+=3) {
                colors[i] = 1.0f;
                colors[i+1] = 1.0f;
                colors[i+2] = 0.0f;
            }
        }
        else if (topColor== Piece.BLUE) {
            for (int i = 48; i <= 57; i+=3) {
                colors[i] = 0.0f;
                colors[i+1] = 0.0f;
                colors[i+2] = 1.0f;
            }
        }
        if (topColor== Piece.GREEN) {
            for (int i = 48; i <= 57; i += 3) {
                colors[i] = 0.0f;
                colors[i + 1] = 1.0f;
                colors[i + 2] = 0.0f;
            }
        }
    }
}
