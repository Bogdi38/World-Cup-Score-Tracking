
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Thread.sleep;
public class WorldCup {
    private static final int GROUP_SIZE = 4;

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to keep tracks of games or analyse possible scenarios?");
//        TODO
//        sleep(5000);
        System.out.println("Press T for tracking and A for analysis");
        String text = input.nextLine();
        boolean analysis = false;
        if (text.equals("A")||text.equals("a")) {
            analysis = true;
        }else if (text.equals("T")||text.equals("t")) {
//            analysis = false;
        }
            text = input.nextLine();
            char team1 = 'z', team2 = 'z';
            int[] points = new int[GROUP_SIZE];
            boolean[] played= new boolean[GROUP_SIZE * (GROUP_SIZE - 1) / 2];
            int[][] matrix = new int[GROUP_SIZE][GROUP_SIZE], owns = new int[GROUP_SIZE][GROUP_SIZE];
            int score1 = 0, score2 = 0;
            boolean position1_taken = false;
            while (!Objects.equals(text, "0")) {
                if (text.equals("R")) {
                    Arrays.fill(points, 0);
                    Arrays.fill(played, false);
                    for (int i = 0; i < GROUP_SIZE; i++) {
                        Arrays.fill(matrix[i], 0);
                        Arrays.fill(owns[i], 0);
                    }
                    System.out.println("You have reset the points");
                    print_result_matrix(matrix, points, owns);
                    print_points(points, matrix);
                    print_owns(owns);
                    text = input.nextLine();
                } else {
                    if (text.contains("A")) {
                        if (!position1_taken) {
                            score1 = text.charAt(text.indexOf("A") + 1);
                            position1_taken = true;
                            team1 = 'A';
                        } else {
                            score2 = text.charAt(text.indexOf("A") + 1);
                            team2 = 'A';
                        }
                    }
                    if (text.contains("B")) {
                        if (!position1_taken) {
                            score1 = text.charAt(text.indexOf("B") + 1);
                            position1_taken = true;
                            team1 = 'B';
                        } else {
                            score2 = text.charAt(text.indexOf("B") + 1);
                            team2 = 'B';
                        }
                    }
                    if (text.contains("C")) {
                        if (!position1_taken) {
                            score1 = text.charAt(text.indexOf("C") + 1);
                            position1_taken = true;
                            team1 = 'C';
                        } else {
                            score2 = text.charAt(text.indexOf("C") + 1);
                            team2 = 'C';
                        }
                    }
                    if (text.contains("D")) {
                        if (!position1_taken) {
                            score1 = text.charAt(text.indexOf("D") + 1);
                            position1_taken = true;
                            team1 = 'D';
                        } else {
                            score2 = text.charAt(text.indexOf("D") + 1);
                            team2 = 'D';
                        }
                    }
                    int i = team1 - 'A';
                    int j = team2 - 'A';
                    if (!analysis && played[i * GROUP_SIZE - i * (i + 1) / 2 + (j - i - 1)]) {
                        System.out.println("This game was already recorded");
                    } else {
                        matrix[team1 - 'A'][team2 - 'A'] = score1 - score2;
                        matrix[team2 - 'A'][team1 - 'A'] = score2 - score1;
                        played[i * GROUP_SIZE - i * (i + 1) / 2 + (j - i - 1)] = true;
                        print_result_matrix(matrix, points, owns);
                        print_points(points, matrix);
                        print_owns(owns);
                    }
//            TODO
//            prevent changing scores
//            Add amount of games played, 3 max for each
//            Add points for draws, but not before the game is played
//            Reset points with R
//            Team Names
//            Find when scenario hurts (people complain, USA won, Turkey out)
//            And when it's good, i.e. the h2h W goes through
//            Maybe simulate all options and find which one's more common
                    position1_taken = false;
                    text = input.nextLine();
                }
            }
    }

    private static void print_result_matrix(int[][] matrix, int[] points, int[][] owns) {
        System.out.println("    A  B  C  D");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print((char) ('A' + i) + "  ");
            for (int j = 0; j < matrix.length; j++) {

                if (matrix[i][j] > 0) {
                    points[i] += 3;
                    System.out.print(" " + matrix[i][j] + " ");
                    if (matrix[i][j] > 2) {
                        owns[i][j] = 1;
                    }
                } else if (matrix[i][j] == 0) {
//                        points[i]+=1;
                    System.out.print(" " + matrix[i][j] + " ");
                } else if (matrix[i][j] < 0) {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private static void print_owns(int[][] owns) {
        for(int i = 0; i< owns.length; i++){
            for(int j = 0; j< owns[i].length; j++){
                if(owns[i][j]==1){
                    System.out.println("Team " + (char)('A'+i) + " owns team " + (char)('A'+j ));
                }
            }
        }
    }

    private static void print_points(int[] points, int[][] matrix) {
        for (int current_team_index = 0; current_team_index < points.length; current_team_index++) {
            char current_team = (char) ('A' + current_team_index);
            System.out.println(current_team + " has " + points[current_team_index] + " points, GD " + sum(matrix[current_team_index]));
            points[current_team_index] = 0;
        }
    }

    private static int sum(int[] matrix) {
        int result = 0;
        for (int i : matrix) {
            result += i;
        }
        return result;
    }
}
