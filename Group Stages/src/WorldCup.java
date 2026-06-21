
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class WorldCup {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String text = input.nextLine();
        char team1='z', team2='z';
        int[] points = new int[4];
        int[][] matrix = new int[4][4];
        int a = 0, b = 0, c = 0;
        int score1=0, score2=0;
        boolean position1_taken = false;
        while (!Objects.equals(text, "0")){
            if (text.contains("A")) {
                if(!position1_taken) {
                    score1 = text.charAt(text.indexOf("A") + 1);
                    position1_taken = true;
                    team1 = 'A';
                }else {
                    score2 = text.charAt(text.indexOf("A") + 1);
                    team2 = 'A';
                }
            }if (text.contains("B")) {
                if(!position1_taken) {
                    score1 = text.charAt(text.indexOf("B") + 1);
                    position1_taken = true;
                    team1 = 'B';
                }else {
                    score2 = text.charAt(text.indexOf("B") + 1);
                    team2 = 'B';
                }
            }if (text.contains("C")) {
                if(!position1_taken) {
                    score1 = text.charAt(text.indexOf("C") + 1);
                    position1_taken = true;
                    team1 = 'C';
                }else {
                    score2 = text.charAt(text.indexOf("C") + 1);
                    team2 = 'C';
                }
            }if (text.contains("D")) {
                if(!position1_taken) {
                    score1 = text.charAt(text.indexOf("D") + 1);
                    position1_taken = true;
                    team1 = 'D';
                }else {
                    score2 = text.charAt(text.indexOf("D") + 1);
                    team2 = 'D';
                }
            }
            matrix[team1-'A'][team2-'A']= score1 - score2;
            matrix[team2-'A'][team1-'A']= score2 - score1;
            System.out.println("    A  B  C  D");
            for (int i = 0; i < matrix.length; i++) {
                System.out.print((char)('A'+i) + "  ");
                for (int j = 0; j < matrix.length; j++) {

                    if(matrix[i][j] > 0) {
                        points[i]+=3;
                        System.out.print(" " + matrix[i][j] + " ");
                    }
                    else if (matrix[i][j] == 0) {
//                        points[i]+=1;
                        System.out.print(" " +matrix[i][j] + " ");
                    }
                    else  if(matrix[i][j] < 0) {
                        points[j]+=3;
                        System.out.print(matrix[i][j] + " ");
                    }
                }
                System.out.println();
            }
            for (int i = 0; i < points.length; i++) {
                int current_team_index = i;
                char current_team =  (char) ('A' + i);
                System.out.println(current_team + " has "+ points[i] + " points, GD " + sum(matrix[current_team_index]));
                points[i]=0;
            }
//            TODO
//            Team A owns Team B if goal diff (score1-2) >2
//            Add amount of games played, 3 max for each
//            Add points for draws, but not before the game is played
            position1_taken = false;
            text = input.nextLine();
        }
    }

    private static int sum(int[] matrix) {
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            result += matrix[i];
        }
        return result;
    }
}
