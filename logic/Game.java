package logic;

public class Game {

    public static final int MAX_VEHICLES = 5;
    
    public Game(int numberOfVehicles) {
        
        if (numberOfVehicles > MAX_VEHICLES)
            throw new RuntimeException("Creating a game with too many players (vehicles) + " 
                    + numberOfVehicles + ". Only " + MAX_VEHICLES + " available");
        
        this.board = new Board(numberOfVehicles);
    }

    public Board board;

    public void findWinner() {

    }

    public void start() {

    }

}
