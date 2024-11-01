import java.util.*;

public class Main {
    static ArrayList<Integer> playerPosition = new ArrayList<>();
    static ArrayList<Integer> computerPosition = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose game mode:");
        System.out.println("1 - Play one round");
        System.out.println("2 - Play best of 3 rounds");

        int gameMode = input.nextInt();
        int playerWins = 0;
        int computerWins = 0;
        int rounds = (gameMode == 2) ? 3 : 1;

        for (int i = 1; i <= rounds; i++) {
            char[][] gameBoard = {
                    {' ', '|', ' ', '|', ' '},
                    {'-', '+', '-', '+', '-'},
                    {' ', '|', ' ', '|', ' '},
                    {'-', '+', '-', '+', '-'},
                    {' ', '|', ' ', '|', ' '}
            };
            playerPosition.clear();
            computerPosition.clear();

            System.out.printf("Round %d start!\n", i);
            printBoard(gameBoard);

            while (true) {
                System.out.println("Please choose a number from 1-9:");
                int playerPos = input.nextInt();
                while (playerPosition.contains(playerPos) || computerPosition.contains(playerPos)) {
                    System.out.println("Position taken, please choose another one:");
                    playerPos = input.nextInt();
                }
                placement(gameBoard, playerPos, "player");

                String result = winner();
                if (!result.isEmpty()) {
                    printBoard(gameBoard);
                    System.out.println(result);
                    if (result.contains("won")) playerWins++;
                    else if (result.contains("Computer wins")) computerWins++;
                    break;
                }

                Random ran = new Random();
                int computerPos = ran.nextInt(9) + 1;
                while (playerPosition.contains(computerPos) || computerPosition.contains(computerPos)) {
                    computerPos = ran.nextInt(9) + 1;
                }
                placement(gameBoard, computerPos, "computer");

                printBoard(gameBoard);
                result = winner();
                if (!result.isEmpty()) {
                    System.out.println(result);
                    if (result.contains("won")) playerWins++;
                    else if (result.contains("Computer wins")) computerWins++;
                    break;
                }
            }

            System.out.printf("Score after Round %d: Player %d - Computer %d\n", i, playerWins, computerWins);
            if (rounds == 3 && (playerWins == 2 || computerWins == 2)) {
                System.out.println("We have a winner for the best of 3 rounds!");
                break;
            }
        }

        System.out.printf("Final Score: Player %d - Computer %d\n", playerWins, computerWins);
        if (playerWins > computerWins) {
            System.out.println("Winner: Player");
        } else if (computerWins > playerWins) {
            System.out.println("Winner: Computer");
        } else {
            System.out.println("Result: Tie");
        }
    }

    public static void printBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placement(char[][] gameBoard, int choice, String user) {
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            playerPosition.add(choice);
        } else if (user.equals("computer")) {
            symbol = 'O';
            computerPosition.add(choice);
        }
        switch (choice) {
            case 1 -> gameBoard[0][0] = symbol;
            case 2 -> gameBoard[0][2] = symbol;
            case 3 -> gameBoard[0][4] = symbol;
            case 4 -> gameBoard[2][0] = symbol;
            case 5 -> gameBoard[2][2] = symbol;
            case 6 -> gameBoard[2][4] = symbol;
            case 7 -> gameBoard[4][0] = symbol;
            case 8 -> gameBoard[4][2] = symbol;
            case 9 -> gameBoard[4][4] = symbol;
        }
    }

    public static String winner() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> bottomRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> crossOne = Arrays.asList(1, 5, 9);
        List<Integer> crossTwo = Arrays.asList(7, 5, 3);
        List<List<Integer>> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(crossOne);
        winning.add(crossTwo);

        for (List<Integer> l : winning) {
            if (playerPosition.containsAll(l)) {
                return "Congratulations, you won!!";
            } else if (computerPosition.containsAll(l)) {
                return "Computer wins :( Try again";
            }
        }

        if (playerPosition.size() + computerPosition.size() == 9) {
            return "MEOW! It's a CAT!";
        }

        return "";
    }
}
