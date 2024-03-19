// Dion Calim
// Squared

import java.util.Random;
// Used to generate random numbers

import java.util.Scanner;
// Used to prompt the player

public class Squared
{
    public static final String textRed = "\u001B[31m";
    // This is used to change print statement colours to red
    // Will be used at the end of the game if player loses

    public static final String textCyan = "\u001B[36m";
    // This is used to change print statement colours to cyan
    // Will be used at the end of the game if player beats squared

    public static Scanner keyboard = new Scanner(System.in);
    // Used as a public method so that Scanner keyboard does not have to be reinitialized

    public static Random randNum = new Random();
    // Used as a public method so that Random randNum does not have to be reinitialized

    public static void main(String[] args)
    {
        //How to play
        System.out.println("\nHow to play: ");
        System.out.println("The general goal of this game is to get a number tile with the value 1024. In this game, " +
                "\nthe program will generate a 4x4 grid and also a number tile of 2 or 4 in one of the spots on the grid." +
                " \nThe program will ask the you how you would like to move the tiles up, down, left or right. After " +
                "\nevery move a new number tile will be generated. If the user decides to move a number tile down where " +
                "\nanother tile of the same value is then the 2 tiles will merge into 1 tile with the new value being the" +
                " \nsum of the 2(ex, number tile of 4 can merge with another tile of 4 creating a tile of 8, but a tile" +
                " \nof 4 cannot merge with a tile of 2).");
        System.out.println();


       int numberTile = generateNumberTile();
       // assigns numberTile to the method generateNumberTile

        int tiles [][] = new int[4][4];
        // tiles is a 2 dimensional array used to hold the values of every number in the grid

        int userInput;
        // This variable is used to hold the values inputted by the user

        System.out.print("Type 1 to play Squared: ");
        userInput = keyboard.nextInt();
        // Initializing whatever the user's input is into a integer variable

        while(userInput != 1)
        // If the player does not type 1 this will keep on outputting
        {
            System.out.print("Please type 1 to play Squared: ");
            userInput = keyboard.nextInt();
            // Initializing whatever the user's input is into a integer variable
            System.out.println("UserInput: " + userInput);
        }

        boolean WINNER = false;
        boolean LOSER = false;

        int score = 1;
        // used to hold the score of the move

        while (WINNER == false && LOSER == false)
        {
            generateNumberTile();
            numberTile = generateNumberTile();
            generateArrayIndex(tiles, numberTile);
            generateGrid(tiles, numberTile);

            System.out.println("1-up 2-down 3-left 4-right");
            System.out.print("How would you like to move the tiles: ");
            userInput = keyboard.nextInt();
            // Initializing whatever the user's input is into a integer variable
            // The user's input will affect how the tiles move

            System.out.println();
            switch (userInput)
            {
                case 1:
                    // If the user input's "1"
                    processPlayerMoveUp(tiles);
                    System.out.println("SCORE: " + score);
                    score++;
                    break;

                case 2:
                    // If the user input's "2"
                    processPlayerMoveDown(tiles);
                    System.out.println("SCORE: " + score);
                    score++;
                    break;

                case 3:
                    // If the user input's "3"
                    processPlayerMovesLeft(tiles);
                    System.out.println("SCORE: " + score);
                    score++;
                    break;

                case 4:
                    // If the user input's "4"
                    processPlayerMoveRight(tiles);
                    System.out.println("SCORE: " + score);
                    score++;
                    break;

                default:
                    // If the user inputs a number other than 1, 2, 3, or 4
                    System.out.println("1-up 2-down 3-left 4-right");
                    System.out.print("Please type the corresponding number for the move you would like to do: ");
                    userInput = keyboard.nextInt();
                    break;


            }

            WINNER = winner(tiles);
            // Assigns boolean WINNER to the method boolean winner

            LOSER = loser(tiles, WINNER);
            // Assigns boolean LOSER to the method boolean loser
        }

        if (WINNER == true)
            
        // checks if WINNER changed to true
        {
            generateGrid(tiles, numberTile);
            // generates the grid one last time

            System.out.println(textCyan + "FINAL SCORE: " + (score-1));
            // displays the final score

            System.out.println(textCyan + "CONGRATS! YOU HAVE BEAT SQUARED");
            System.out.println(textCyan + "Please restart program to play again.");
        }
        else if(LOSER == true)
        // checks if LOSER changed to true
        {
            generateGrid(tiles, numberTile);
            // generates the grid one last time

            System.out.println(textRed + "FINAL SCORE: " + (score-1));
            // displays the final score

            System.out.println(textRed + "SORRY! GAME OVER");
            System.out.println(textRed + "Please restart program to play again.");
        }




        keyboard.close();
    }

    public static int generateNumberTile()
            // This method will generate weather the random tile holds the value of 2 or 4;
    {
        int numberTile;
        Random randNum = new Random();
        numberTile = randNum.nextInt(2) + 1;
        // Random randNum is used to generate a random number
        // The random number generated(1 or 2) will be initialized to the integer variable numberTile which will
        // then be used to equal 2 or 4 in the if statements below. Integer variable numberTile will be used in the
        // generateGrid method
        if(numberTile == 1)
        {
            numberTile = 2;
            // If the random number generated is 1 then numberTile will then equal 2 which will eventually be
            // generated into the 4x4 grid
        }
        else
        // else = if the random number generated is 2
        {
            numberTile = 4;
            // If the random number generated is 2 then numberTile will then equal 4 which will eventually be
            // generated into the 4x4 grid
        }
        return numberTile;
    }

    public static void generateArrayIndex(int tile[][], int numberTile)
            // This method will generate a random array index and apply the numbertile integer to the index
    {

        int row;
        // this integer will hold which row of the grid the number tile will be generated in

        int col;
        // This integer will hold which column of the grid the number tile will be generated in

        row = randNum.nextInt(4);
        // generate a random number from 0 through 3

        col = randNum.nextInt(4);
        // generate a random number from 0 through 3

        while (tile[row][col] != 0)
        // this while loop will check if the array index of the grid is 0
            // If the array index holds another value that is not 0 this while loop will run again until
            // the array index holds 0
        {
            row = randNum.nextInt(4);
            // generate a random number from 0 through 3

            col = randNum.nextInt(4);
            // generate a random number from 0 through 3
        }
        tile[row][col] = numberTile;
        // Once the array index is not a 0 it will apply the integer numberTile which is 2 or 4 to that index

    }

    public static void generateGrid(int tile[][], int numberTile)
            // This method will generate the grid with all the values
    {
        // The Grid
        System.out.println("-----------------------------------------------------------------");
        System.out.println("|\t" + tile [0][0] + "\t|\t" + tile [0][1] + "\t|\t" + tile[0][2] + "\t|\t" + tile[0][3] + "\t|");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("|\t" + tile [1][0] + "\t|\t" + tile [1][1] + "\t|\t" + tile[1][2] + "\t|\t" + tile[1][3] + "\t|");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("|\t" + tile [2][0] + "\t|\t" + tile [2][1] + "\t|\t" + tile[2][2] + "\t|\t" + tile[2][3] + "\t|");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("|\t" + tile [3][0] + "\t|\t" + tile [3][1] + "\t|\t" + tile[3][2] + "\t|\t" + tile[3][3] + "\t|");
        System.out.println("-----------------------------------------------------------------");

    }

    public static boolean winner(int tile[][])
    // this method is a boolean method to check if the player has won and
    // holds the parameters of the grid as tile[][]
    {
        boolean winner = false;
        // this boolean is used to check when the player wins the game

        final int GOAL = 1024;
        // GOAL is held as a final int because 1024 is the goal value and will never change

        for (int row = 0; row <= tile.length - 1; row++)
        // this for loop will check every row in the grid
        {
            for (int col = 0; col <= tile.length - 1; col++)
            // this for loop will check every column in the grid
            {
                if(tile[row][col] >= GOAL)
                // This will check if any index of the grid holds the value of GOAL which is 1024
                {
                    System.out.println();
                    winner = true;
                    // changes the boolean to true
                }
            }
        }
        return winner;
        // Returns the winner boolean back to the main method
    }

    public static boolean loser(int tile[][], boolean winner)
    // This method is a boolean method which checks if the user has loss and
    // holds the parameters of the grid(tile[][]) and boolean variable winner
    {
        boolean loser = false;
        // This boolean checks if the player has loss

        boolean emptySpot = false;
        // This boolean checks if the grid has a empty spot

        for (int row = 0; row <= tile.length - 1; row++)
        // For loop will check every row of the grid
        {
            for (int col = 0; col <= tile.length - 1; col++)
            // For loop will check every column of the grid
            {
                if(tile[row][col] == 0)
                // Will check if the index has a 0
                {
                    emptySpot = true;
                    // When the if statement is true the boolean empty spot also changes to true
                }

            }
        }

        if(winner == false && emptySpot == false)
        // Checks if winner is false and if emptySpot is false
        {
            System.out.println();
            loser = true;
            // When the if statement is true the boolean loser will change to true
        }
        return loser;
        // This will return the boolean loser back to the main method
    }

    public static int[][] processPlayerMoveUp(int tile[][])
    // This method shows how the tiles will move up and add together
    {

        for (int col = 0; col < tile.length; col++)
        // Checks every column in the grid
        {

            for (int row = 0; row <= tile.length - 1; row++)
            // Checks every row in the grid
            {
                int curr = row;
                // curr is the current column we are trying to shift

                while (curr != 0)
                // while current position is not at the edge of the grid
                {
                    if (tile[curr][col] != 0 && tile[curr - 1][col] == 0)
                     // Will check if the index does not hold a value of 0 AND if the row(curr) above holds value 0
                    {
                        tile[curr - 1][col] = tile[curr][col];
                        tile[curr][col] = 0;
                        // Changes the tile above the index it is currently on to the value of the
                        // index it is currently on
                        // Then changes the current index value to 0


                    }
                    else if (tile[curr][col] == tile[curr - 1][col])
                    // Checks if the current index holds the same value of the index above it
                    {
                        tile[curr - 1][col] += tile[curr][col];
                        // this is equal to saying tile[curr - 1][col] = tile[curr - 1][col] + tile[curr][col]
                        // This will add the 2 tiles together
                        tile[curr][col] = 0;
                        // Then initialize the current index with the value of 0
                    }
                    curr--;
                    // will subtract curr by 1 everytime the while loop goes through
                }

            }

        }
        return tile;
        // returns tile which is the grid back to the main method
    }

    public static int[][] processPlayerMoveDown(int tile[][])
    // This method shows how the tiles will move down and add together
    {
        for (int col = 0; col < tile[0].length; col++)
        // Checks every column in the grid
        {
            for (int row = tile.length - 1; row >= 0; row--)
            // Checks every row in the grid
            {
                int curr = row;
                // curr is the current row we are trying to shift

                while (curr != tile[0].length - 1)
                // while current position is not at the edge of the grid
                {
                    if (tile[curr][col] != 0 && tile[curr + 1][col] == 0)
                    // Will check if the index does not hold a value of 0 AND if the row(curr) below holds value 0
                    {
                        tile[curr + 1][col] = tile[curr][col];
                        tile[curr][col] = 0;
                        // Changes the tile below the index it is currently on to the value of the
                        // index it is currently on
                        // Then changes the current index value to 0

                    }
                    else if (tile[curr + 1][col] == tile[curr][col])
                    // Checks if the index below holds the same value of the current index
                    {
                        tile[curr + 1][col] = tile[curr + 1][col] + tile[curr][col];
                        // this is equal to saying tile[curr + 1][col] = tile[curr + 1][col] + tile[curr][col]
                        // This will add the 2 tiles together

                        tile[curr][col] = 0;
                        // Then initialize the current index with the value of 0
                    }
                    curr++;
                    // will add curr by 1 everytime the while loop goes through
                }

            }

        }
        return tile;
        // returns tile which is the grid back to the main method
    }

    public static int[][] processPlayerMovesLeft(int tile[][])
    // This method shows how the tiles will move left and add together
    {

        for (int row = 0; row < tile.length; row++)
        // Checks every row in the grid
        {

            for (int col = 0; col <= tile[0].length - 1; col++)
            // Checks every column in the grid
            {
                int curr = col;
                // curr is the current column we are trying to shift

                while (curr != 0)
                // while current position is not at the edge of the grid
                {

                    if (tile[row][curr] != 0 && tile[row][curr - 1] == 0)
                    // Will check if the index does not hold a value of 0 AND if the col(curr) to the left holds value 0
                    {

                        tile[row][curr - 1] = tile[row][curr];
                        tile[row][curr] = 0;
                        // Changes the tile to the left of the index it is currently on to the value of the
                        // index it is currently on
                        // Then changes the current index value to 0
                    }
                    else if (tile[row][curr - 1] == tile[row][curr])
                    // Checks if the index to the left holds the same value of the current index
                    {
                        tile[row][curr - 1] += tile[row][curr];
                        // this is equal to saying tile[row][curr - 1] = tile[row][curr - 1] + tile[curr][col]
                        // This will add the 2 tiles together

                        tile[row][curr] = 0;
                        // Then initialize the current index with the value of 0
                    }
                    curr--;
                    // will subtract curr by 1 everytime the while loop goes through
                }

            }

        }
        return tile;
        // returns tile which is the grid back to the main method
    }

    public static int [][] processPlayerMoveRight(int [][] tile)
    // This method shows how the tiles will move right and add together
    {

        for (int row = 0; row < tile.length; row++)
        // Checks every row in the grid
        {
            for (int col = tile[0].length - 1; col >= 0; col--)
            // Checks every column in the grid
            {
                int curr = col;
                // curr is the current column we are trying to shift

                while (curr != tile[0].length - 1)
                // while current position is not at the edge of the grid
                {
                    if (tile[row][curr] != 0 && tile[row][curr + 1] == 0)
                    // Will check if the index does not hold a value of 0 AND if the col(curr) to the right holds value 0
                    {
                        tile[row][curr + 1] = tile[row][curr];
                        tile[row][curr] = 0;
                        // Changes the tile to the right of the index it is currently on to the value of the
                        // index it is currently on
                        // Then changes the current index value to 0

                    }
                    else if (tile[row][curr + 1] == tile[row][curr])
                    // Checks if the index to the right holds the same value of the current index
                    {
                        tile[row][curr + 1] += tile[row][curr];
                        // this is equal to saying tile[row][curr + 1] = tile[row][curr + 1] + tile[curr][col]
                        // This will add the 2 tiles together

                        tile[row][curr] = 0;
                        // Then initialize the current index with the value of 0
                    }
                    curr++;
                    // will add curr by 1 everytime the while loop goes through
                }
            }

        }
        return tile;
        // returns tile which is the grid back to the main method
    }
}