import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
public class omok {
    public static void main(String[] args){
        Console console = new Console();
        console.welcome();
        int[][] table = new int[15][15]; //creates 2D array to keep track of movements
        Board board = new Board(table); //instantiate the board
        SmartStrategy strat = new SmartStrategy(board);
        int mode = console.scanMode(); //scanning game mode
        Game match = new Game(mode, console, strat); //creates game
        boolean victory = false;
        for(int i = 0; i <= 625; i += 2){
            for(var p: match.players){
                int[]x;
                System.out.println("player: " + p.getPlayerNumber());
                if(mode == 1 || p.getPlayerNumber() == 1)
                    console.askCoordinates();
                x = p.createMove();
                if(x[0] == 1000 || x[1] == 1000) {
                    console.endMessage();
                    return;
                }
                while (!(board.validMovement(x[0], x[1], p.getPlayerNumber()))) {
                    System.out.println("segunda impresion player: " + p.getPlayerNumber());
                    console.invalidInput();
                    x = p.createMove();
                }
                console.printBoard(table);
                if(board.hasWon(x[0], x[1],p.getPlayerNumber())){
                    victory = true;
                    console.victoryMessage(p.getPlayerNumber());
                    break;
                }
            }
            if(victory) {
                break;
            }
        }
        if(!victory)
            console.drawMessage();
    }
}
/** Interactive user class. This class allows Gomoku implementation to interact during run time with user using methods to print messages or scan input.
 * @author Emilio Andre Rojero
 * @version 1.0 (02/26/2023)
 */
class Console{

    /**Enables scanning function for methods. Defines scanner's name with "s".
     * @param System.in Standard input
     */
    Scanner s = new Scanner(System.in);

    /**Builds object using default settings.*/
    public Console(){ }

    /**Prints welcome message tells the user how to choose game mode.
     */
    public void welcome(){
        System.out.println("Welcome to Omok");
        System.out.print("Type 1 for 2 players. Type 2 to play vs computer. ---->>>>");
    }

    /**Scans game mode and checks if it's valid, if not it scans again.
     * @return Integer type, game mode the user wants to play
     */
    public int scanMode(){
        int mode = s.nextInt();
        while(!(mode >= 1 && mode <= 2)){
            System.out.print("Invalid game mode. Type 1 for 2 players. Type 2 to play vs computer. ---->>>>");
            mode = s.nextInt();
        }
        return mode;
    }

    /**Scans two integers from user input.
     * @return Integer array containing the coordinate the user wants to put his/her stone in.
     */
    public int[] getInput(){
        int[] x = new int[2];
        x[0] = s.nextInt();
        x[1] = s.nextInt();
        return x;
    }

    /**Prints victory message to advice x (player number entered as parameter) player that he/she won.
     * @param playerNumber integer which is the number of the player who won to include it in the message.
     */
    public void victoryMessage(int playerNumber){
        System.out.println("\nCONGRATULATIONS PLAYER " + playerNumber + " YOU WON!!!");
    }

    /**Prompts the user to type the next coordinates he/she wants to play.
     */
    public void askCoordinates(){
        System.out.println("Enter coordinates (x y) No comma, and numbers from 0 to 14. Type 1000 to end game.");
    }

    /**Tells the user if his/her coordinate is not valid. This may be caused by an input out of bounds or position already in used.
     */
    public void invalidInput(){
        System.out.print("Invalid input. Enter x y:");
    }

    /**Prints a message telling the user(s) that the game ended in a draw.
     */
    public void drawMessage(){ System.out.println("It's a draw. :/"); }

    /**Tells the user(s) that the game has ended.
     */
    public void endMessage(){ System.out.println("Game ended."); }

    /**Draws the board using ASCII characters and information kept at 2D array parameter.
     * @param table A 2D array which keeps track of the used and empty espaces in the board.
     */
    public void printBoard(int[][] table){

        System.out.println("x  y| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10| 11| 12| 13| 14|");
        System.out.println("----------------------------------------------------------------");
        for(int i = 0; i < 15; i++){
            if(i < 10)
                System.out.print("  " + i + " |");
            else
                System.out.print(" " + i + " |");
            for(int j = 0; j < 15; j++){
                if (table[i][j] == 1)
                    System.out.print(" X |");
                else if(table[i][j] == 2)
                    System.out.print(" 0 |");
                else
                    System.out.print("   |");
            }
            System.out.println();
        }
    }
}
class Game{
    List<Player> players = new ArrayList<>(2);
    public Game(int mode, Console ui, SmartStrategy strat){
        players.add(new Human(ui, 1));
        switch (mode) {
            case 1:
                players.add(new Human(ui, 2));
                break;
            default:
                players.add(new AI(strat, 2));

        }
    }
}
abstract class Player{
    public Player(){ }
    public abstract int[] createMove();
    public abstract int getPlayerNumber();

}
class Human extends Player{
    private Console ui;
    private int playerNumber;
    public Human(Console ui, int playerNumber){
        this.ui = ui;
        this.playerNumber = playerNumber;
    }
    @Override
    public int[] createMove(){
        return ui.getInput();
    }
    @Override
    public int getPlayerNumber(){ return playerNumber; }

}
class AI extends Player{
    private int playerNumber;
    SmartStrategy strat;
    public AI(SmartStrategy strat, int playerNumber){
        this.strat = strat;
        this.playerNumber = playerNumber;
    }
    @Override
    public int[] createMove(){
        return strat.makeMove();
    }
    @Override
    public int getPlayerNumber(){ return playerNumber; }
}

//abstract class Strategy{
//    abstract int[] makeMove();
//}
class SmartStrategy{
    private Board board;
    public SmartStrategy(Board board){
        this.board = board;
    }
    public int[] makeMove(){
        int[] last = board.getPrevPlayer1Mov();
        int[] adjacent = {last[0] - 1, last[1] - 1, last[0], last[1] - 1, last[0] + 1, last[1] - 1, last[0] - 1, last[1], last[0] + 1, last[1], last[0] - 1, last[1] + 1, last[0], last[1] + 1, last[0] + 1, last[1] + 1};
        int[] checking = new int[2];
        int[] checkOpposite = new int[2];
        int[] move = new int[2];
        for(int i = 0; i < 16; i += 2){
            checking[0] = adjacent[i];
            checking[1] = adjacent[i + 1];
            checkOpposite[0] = adjacent[15 - i - 1];
            checkOpposite[1] = adjacent[15 - i];
            if(checkOpposite[0] < 0 || checkOpposite[1] < 0 || checkOpposite[0] >= 15 || checkOpposite[1] >= 15 || checking[0] < 0 || checking[1] < 0 || checking[0] >= 15 || checking[1] >= 15)
                continue;
            if(board.table[checking[0]][checking[1]] == 1){
                if(board.table[checkOpposite[0]][checkOpposite[1]] == 0){
                    move[0] = checkOpposite[0];
                    move[1] = checkOpposite[1];
                    return move;
                }
            }
        }
        last = board.getPrevPlayer2Mov();
        for(int i = 0; i < 16; i+= 2){
            checking[0] = adjacent[i];
            checking[1] = adjacent[i + 1];
            checkOpposite[0] = adjacent[15 - i - 1];
            checkOpposite[1] = adjacent[15 - i];
            if(checkOpposite[0] < 0 || checkOpposite[1] < 0 || checkOpposite[0] >= 15 || checkOpposite[1] >= 15 || checking[0] < 0 || checking[1] < 0 || checking[0] >= 15 || checking[1] >= 15)
                continue;
            if(board.table[checking[0]][checking[1]] == 2) {
                if (board.table[checkOpposite[0]][checkOpposite[1]] == 0) {
                    move[0] = checkOpposite[0];
                    move[1] = checkOpposite[1];
                    return move;
                }
            }
        }
        System.out.println("esto " + last[0] + " , " + last[1]);
        for(int i = 0; i < 16; i+= 2){
            checkOpposite[0] = adjacent[15 - i - 1];
            checkOpposite[1] = adjacent[15 - i];
            if(checkOpposite[0] < 0 || checkOpposite[1] < 0 || checkOpposite[0] >= 15 || checkOpposite[1] >= 15)
                continue;
            if (board.table[checkOpposite[0]][checkOpposite[1]] == 0) {
                move[0] = checkOpposite[0];
                move[1] = checkOpposite[1];
                return move;
            }
        }
        Random rand = new Random();
        int[] x = {rand.nextInt(15), rand.nextInt(15)};
        return x;
    }
}
class Board {
    int[][] table;
    private int[] prevPlayer1Mov = new int[2];
    private int[] prevPlayer2Mov = new int[2];
    public Board(int[][] table){
        this.table = table;
    }
    public boolean validMovement(int x, int y, int player){
        if(x < 0 || x > 14 || y < 0 || y > 14)
            return false;
        if (table[x][y] > 0) //if board[x][y] is true, position is not valid
            return false;
        table[x][y] = player;
        if(player == 1) {
            prevPlayer1Mov[0] = x;
            prevPlayer1Mov[1] = y;
        }
        else {
            prevPlayer2Mov[0] = x;
            prevPlayer2Mov[1] = y;
        }
        return true;
    }
    public int[] getPrevPlayer1Mov(){ return prevPlayer1Mov; }
    public int[] getPrevPlayer2Mov(){ return prevPlayer2Mov; }

    public boolean hasWon(int x, int y, int player){
        if(helper(x, y, 1, 0, player) >= 5) { return true;}
        if(helper(x, y, 0, 1, player) >= 5) { return true;}
        if(helper(x, y, 1, 1, player) >= 5) { return true;}
        if(helper(x, y, 1, -1, player) >= 5) { return true;}
        return false;
    }
    private int helper(int x, int y, int dx, int dy, int player){
        int count = 0;
        int initX = x;
        int initY = y;
        while(table[x][y] == player) {
            count++;
            x += dx;
            y += dy;
            if(x < 0 || y < 0 || x > 14 || y > 14)
                break;
        }
        x = initX - dx;
        y = initY - dy;
        if(x < 0 || y < 0 || x > 14 || y > 14)
            return count;
        while(table[x][y] == player){
            count++;
            x -= dx;
            y -= dy;
            if(x < 0 || y < 0 || x > 14 || y > 14)
                break;
        }
        return count;
    }
}

