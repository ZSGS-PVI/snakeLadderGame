package snakeandladder.game;

import java.util.Scanner;

import java.util.*;

public class SnakeAndLadderGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get input for board size
        System.out.print("Enter the board size: ");
        int boardSize = scanner.nextInt();

        // Get input for the number of snakes and their head-tail positions
        System.out.print("Enter the number of snakes: ");
        int numSnakes = scanner.nextInt();
        Map<Integer, Integer> snakes = new HashMap<>();
        for (int i = 0; i < numSnakes; i++) {
            System.out.print("Enter the head position for snake " + (i + 1) + ": ");
            int head = scanner.nextInt();
            System.out.print("Enter the tail position for snake " + (i + 1) + ": ");
            int tail = scanner.nextInt();
            snakes.put(head, tail);
        }

        // Get input for the number of ladders and their bottom-top positions
        System.out.print("Enter the number of ladders: ");
        int numLadders = scanner.nextInt();
        Map<Integer, Integer> ladders = new HashMap<>();
        for (int i = 0; i < numLadders; i++) {
            System.out.print("Enter the bottom position for ladder " + (i + 1) + ": ");
            int bottom = scanner.nextInt();
            System.out.print("Enter the top position for ladder " + (i + 1) + ": ");
            int top = scanner.nextInt();
            ladders.put(bottom, top);
        }

        // Get input for the number of players and their names
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        Map<String, Integer> playerPositions = new HashMap<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter the name for player " + (i + 1) + ": ");
            String playerName = scanner.next();
            playerPositions.put(playerName, 0); // Initialize player position to 0
        }

        // Start the game
        while (true) {
            for (String playerName : playerPositions.keySet()) {
                int diceRoll = rollDice();
                int currentPosition = playerPositions.get(playerName);
                int newPosition = currentPosition + diceRoll;

                // Check if newPosition is within the board size
                if (newPosition <= boardSize * boardSize) {
                    newPosition = getNewPosition(newPosition, snakes, ladders);

                    System.out.println(playerName + " rolled a " + diceRoll +
                            " and moved from " + currentPosition + " to " + newPosition + ".");

                    // Update player position in the map
                    playerPositions.put(playerName, newPosition);

                    // Check if the player has won
                    if (newPosition == boardSize * boardSize) {
                        System.out.println(playerName + " wins the game!");
                        scanner.close();
                        return;
                    }
                }
            }
        }
    }

    // Method to simulate a dice roll and return the result
    private static int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1; // Returns a random number between 1 and 6
    }

    private static int getNewPosition(int position, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders) {
        if (snakes.containsKey(position)) {
            return snakes.get(position);
        } else if (ladders.containsKey(position)) {
            return ladders.get(position);
        }
        return position;
    }
}



