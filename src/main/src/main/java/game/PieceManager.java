package game;

import game.piece.Piece;
import game.piece.PieceCoordinate;

import java.util.LinkedList;


/**
 *  Jimmy Deng, June 07, 2019
 * This class stores the current location of the cubelets.
 */


public class PieceManager {

    public static final int X = 0;
    public static final int XPrime = 1;
    public static final int Y = 2;
    public static final int YPrime = 3;
    public static final int Z = 4;
    public static final int ZPrime = 5;

    public static final int U = 10;
    public static final int UPrime = 100;
    public static final int D = 15;
    public static final int DPrime = 150;
    public static final int F = 12;
    public static final int FPrime = 120;
    public static final int B = 14;
    public static final int BPrime = 140;
    public static final int R = 13;
    public static final int RPrime = 130;
    public static final int L = 11;
    public static final int LPrime = 110;


    public static final int topLayer = 0;
    public static final int bottomLayer = 2;
    public static final int frontLayer = 0;
    public static final int backLayer = 2;
    public static final int leftSlice = 0;
    public static final int rightSlice = 2;
    public LinkedList<Piece> allPiece;
    private Piece[][][] pieces;

    public PieceManager() {
        pieces = new Piece[3][3][3];
        this.allPiece = new LinkedList<>();
    }

    public void addPiece(Piece piece, PieceCoordinate pc) {
        this.allPiece.add(piece);
        this.pieces[pc.getX()][pc.getY()][pc.getZ()] = piece;

    }

    public void shiftAll(int direction) {
        Piece[][][] shifted = new Piece[3][3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    shifted[x][y][z] = pieces[x][y][z];
                }
            }
        }

        if (direction == X) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    // Front Face Shift
                    shifted[x][0][2 - y] = pieces[x][y][0];
                    // Back Face
                    shifted[x][2][2 - y] = pieces[x][y][2];
                    //Side 1
                }
                shifted[x][1][2] = pieces[x][0][1];
                shifted[x][1][0] = pieces[x][2][1];
            }
        } else if (direction == XPrime) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    // Front Face Shift
                    shifted[x][2][y] = pieces[x][y][0];
                    // Back Face
                    shifted[x][0][y] = pieces[x][y][2];
                    //Side 1
                }
                // Top Stripe
                shifted[x][1][0] = pieces[x][0][1];
                // Bottom Stripe
                shifted[x][1][2] = pieces[x][2][1];
            }
        } else if (direction == Y) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    // Front Face Shift
                    shifted[0][y][2 - x] = pieces[x][y][0];
                    // Back Face
                    shifted[2][y][2 - x] = pieces[x][y][2];
                }
                // Right Stripe
                shifted[1][y][0] = pieces[2][y][1];
                // Left Stripe
                shifted[1][y][2] = pieces[0][y][1];
            }
        } else if (direction == YPrime) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    // Front Face Shift
                    shifted[2][y][x] = pieces[x][y][0];
                    // Back Face
                    shifted[0][y][x] = pieces[x][y][2];
                }
                // Right Stripe
                shifted[1][y][2] = pieces[2][y][1];
                // Left Stripe
                shifted[1][y][0] = pieces[0][y][1];
            }
        } else if (direction == Z) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    for (int z = 0; z < 3; z++) {
                        shifted[2 - y][x][z] = pieces[x][y][z];
                        // x becomes 2 - y, y becomes x, and z remains constant
                    }
                }
            }
        } else if (direction == ZPrime) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    for (int z = 0; z < 3; z++) {
                        shifted[y][2 - x][z] = pieces[x][y][z];
                    }
                }
            }
        }
        this.pieces = shifted;
    }

    public void rotate(int direction) {
        Piece[][][] modified = new Piece[3][3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    modified[x][y][z] = pieces[x][y][z];
                }
            }
        }
        //TEST
        if (direction == U) {
            for (int x = 0; x < 3; x++) {
                for (int z = 0; z < 3; z++) {
                    //original -> modified
                    //(x,y,z)  -> (z,y,2-x)

                    modified[z][0][2 - x] = pieces[x][0][z];
                }
            }
        } else if (direction == UPrime) {
            for (int x = 0; x < 3; x++) {
                for (int z = 0; z < 3; z++) {
                    /*
                    original -> modified
                    (x,y,z) -> (2-z,y,x)    */
                    modified[2 - z][0][x] = pieces[x][0][z];
                }
            }
        } else if (direction == D) {
            for (int x = 0; x < 3; x++) {
                for (int z = 0; z < 3; z++) {
                    /*
                    original -> modified
                    (x,y,z) -> (2-z,y,x)    */
                    modified[2 - z][2][x] = pieces[x][2][z];
                }
            }
        } else if (direction == DPrime) {
            for (int x = 0; x < 3; x++) {
                for (int z = 0; z < 3; z++) {
                    modified[z][2][2 - x] = pieces[x][2][z];
                }
            }
        }

        // FONRT FACE
        else if (direction == F) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    // (x,y,z) -> (2-y,x,z)
                    modified[2 - y][x][0] = pieces[x][y][0];
                }
            }
        } else if (direction == FPrime) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    modified[y][2 - x][0] = pieces[x][y][0];
                }
            }
        }
        // back face
        else if (direction == B) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    // (x,y,z) -> (y,2-x,z)
                    modified[y][2 - x][2] = pieces[x][y][2];
                }
            }
        } else if (direction == BPrime) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    modified[2 - y][x][2] = pieces[x][y][2];
                }
            }
        } else if (direction == R) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    //(x,y,z) -> (x,z,2-y)
                    modified[2][z][2 - y] = pieces[2][y][z];
                }
            }
        } else if (direction == RPrime) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {

                    modified[2][2 - z][y] = pieces[2][y][z];
                }
            }
        } else if (direction == L) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    //(x,y,z) -> (x,2-z,y)
                    modified[0][2 - z][y] = pieces[0][y][z];
                }
            }
        } else if (direction == LPrime) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    //(x,y,z) -> (x,2-z,y)
                    modified[0][z][2 - y] = pieces[0][y][z];
                }
            }
        }

        this.pieces = modified;
    }

    public LinkedList<Piece> getAllPieces() {
        return allPiece;
    }

    public LinkedList<Piece> getFrontFace(int layer) {
        LinkedList<Piece> frontFace = new LinkedList<>();

        int i = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                frontFace.add(pieces[x][y][layer]);
                i++;
            }
        }
        return frontFace;
    }

    public LinkedList<Piece> getHorizontalSlice(int layer) {
        LinkedList<Piece> slice = new LinkedList<>();

        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                slice.add(pieces[x][layer][z]);
            }
        }
        return slice;
    }

    public LinkedList<Piece> getVerticalSlice(int layer) {
        LinkedList<Piece> slice = new LinkedList<>();

        for (int z = 0; z < 3; z++) {
            for (int y = 0; y < 3; y++) {
                slice.add(pieces[layer][y][z]);
            }
        }
        return slice;
    }

    public Piece getPiece(int x, int y, int z) {
        return pieces[x][y][z];
    }

}
