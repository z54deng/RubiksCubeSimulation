package game.solver;

import game.PieceManager;
import kociemba.Search;

import java.util.LinkedList;
import java.util.List;

public class FindPath {
    // takes in String format Cube
    public static String[] findSolution(String x, int length){
        String result = new Search().solution(x, length, 100000000, 0, 0);
        System.out.println(result);// R2 U2 B2 L2 F2 U' L2 R2 B2 R2 D  B2 F  L' F  U2 F' R' D' L2 R'
        String[] arr = result.split("\\s+");
        return arr;
    }


    public static List<Integer> format(String[] solutionArr){
        List<Integer> formattedSolutionList = new LinkedList<>();
        for (String temp : solutionArr) {

            //  Up rotation formatting
            if(temp.equals("U"))
                formattedSolutionList.add(PieceManager.U);
            else if(temp.equals("U'"))
                formattedSolutionList.add(PieceManager.UPrime);
            else if(temp.equals("U2")){
                formattedSolutionList.add(PieceManager.U);
                formattedSolutionList.add(PieceManager.U);
            }

            //  Down rotation formatting
            else if(temp.equals("D"))
                formattedSolutionList.add(PieceManager.D);
            else if(temp.equals("D'"))
                formattedSolutionList.add(PieceManager.DPrime);
            else if(temp.equals("D2")){
                formattedSolutionList.add(PieceManager.D);
                formattedSolutionList.add(PieceManager.D);
            }

            //  Front rotation formatting
            else if(temp.equals("F"))
                formattedSolutionList.add(PieceManager.F);
            else if(temp.equals("F'"))
                formattedSolutionList.add(PieceManager.FPrime);
            else if(temp.equals("F2")){
                formattedSolutionList.add(PieceManager.F);
                formattedSolutionList.add(PieceManager.F);
            }

            //  Back rotation formatting
            else if(temp.equals("B"))
                formattedSolutionList.add(PieceManager.B);
            else if(temp.equals("B'"))
                formattedSolutionList.add(PieceManager.BPrime);
            else if(temp.equals("B2")){
                formattedSolutionList.add(PieceManager.B);
                formattedSolutionList.add(PieceManager.B);
            }

            //  Right rotation formatting
            else if(temp.equals("R"))
                formattedSolutionList.add(PieceManager.R);
            else if(temp.equals("R'"))
                formattedSolutionList.add(PieceManager.RPrime);
            else if(temp.equals("R2")){
                formattedSolutionList.add(PieceManager.R);
                formattedSolutionList.add(PieceManager.R);
            }

            //  Left rotation formatting
            else if(temp.equals("L"))
                formattedSolutionList.add(PieceManager.L);
            else if(temp.equals("L'"))
                formattedSolutionList.add(PieceManager.LPrime);
            else if(temp.equals("L2")){
                formattedSolutionList.add(PieceManager.L);
                formattedSolutionList.add(PieceManager.L);
            }
        }

        return formattedSolutionList;
    }

    //Change the Colors of the Cube that is in String form into Integer Form
    public static String stringToInt(String cubeStr){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<cubeStr.length();i++){
            char character = cubeStr.charAt(i);
            if(character == 'F'){
                sb.append(0);
            }
            else if(character == 'B'){
                sb.append(1);
            }
            else if(character == 'U'){
                sb.append(2);
            }
            else if(character == 'D'){
                sb.append(3);
            }
            else if(character == 'R'){
                sb.append(4);
            }
            else if(character == 'L'){
                sb.append(5);
            }
        }
        return sb.toString();
    }
    //Change the Colors of the Cube that is in Integer form into String Form
    public static String intToString(String intStr){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<intStr.length();i++){
            int color = Integer.parseInt(intStr.substring(i,i+1));

            if(color == 0){
                sb.append("F");
            }
            else if(color == 1){
                sb.append("B");
            }
            else if(color == 2){
                sb.append("U");
            }
            else if(color == 3){
                sb.append("D");
            }
            else if(color == 4){
                sb.append("R");
            }
            else if(color == 5){
                sb.append("L");
            }
        }
        return sb.toString();
    }
}
