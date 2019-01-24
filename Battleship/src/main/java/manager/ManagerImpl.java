package manager;


import constants.Constants;
import entities.Grid;
import entities.Player;
import interfaces.Manager;
import io.Reader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManagerImpl implements Manager {
    private Reader reader;
    private List<Player> players;
    private Grid grid;

    public ManagerImpl() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.players = new ArrayList<Player>();
        this.grid =new Grid();
        this.reader=new Reader();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }

    private void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Grid getGrid() {
        return this.grid;
    }

    private void setGrid(Grid grid) {
        this.grid = grid;
    }
    public void addPlayer(Player player){
        this.players.add(player);
    }
    /// start the game
    public void play() throws IOException {
       // count of current successful shots
        int countShots=0;
        // count of all shots to the end of the game;
        int countSteps=0;
        while(true){
            // check if all ships are sinked;
            if (isShootedAll() && countShots==this.grid.getCountSquaresShip()) {
                System.out.println("Sunk");
                this.grid.printGrid();
                System.out.printf("Well done! You completed the game in %d shots",countSteps);
                break;
            }
            /// palyers starts to shot one after another
            for (int i = 1; i <= players.size() ; i++) {

                System.out.println("Show shootedShips(Yes/No)");
                String show=reader.readLine();
                if(show.toUpperCase().equals("Yes".toUpperCase())){
                    // show grid with sinked ships
                    this.grid.showSinkedShips();
                   // this.grid.printGrid();
                }
                System.out.printf("Player %d\n",i);
                int row=0;
                while(true) {
                    System.out.printf("Enter number from 1 to %d: ", Constants.COUNT__ROWS);
                  // enter row
                   row = Integer.parseInt(reader.readLine());
                   // check if row is in necessary scope
                    if (row > 0 && row <=this.getGrid().getGrid().length) {
                        break;
                    }
                }
                int column=0;
                while(true) {
                    System.out.printf("Enter number from 1 to %d: ", Constants.COUNT_COLUMNS);
                    // enter column
                    column = Integer.parseInt(reader.readLine());
                    // check if row is in necessary scope
                    if (column > 0 && column <=this.getGrid().getGrid()[0].length) {
                        break;
                    }
                }
                // check if this shot is in ship
                if (this.grid.getGrid()[row][column].equals("S")) {
                        countShots++;
                        countSteps++;
                        this.grid.getGrid()[row][column] = "X";
                        System.out.println("Shot");
                    }
                // check if this shot is miss
                    if (this.grid.getGrid()[row][column].equals(".")) {
                        this.grid.getGrid()[row][column] = "-";
                        countSteps++;
                        System.out.println("Miss");
                    }


            }
        }
    }
// method to check if all ship are sinked
    private boolean isShootedAll() {
        boolean isHave=false;
        for (int i = 0; i <this.grid.getGrid().length ; i++) {
            for (int j = 0; j <this.grid.getGrid()[0].length; j++) {
                if(this.grid.getGrid()[i][j].equals("S")){
                    isHave=true;
                }
            }
        }
        return !isHave;
    }
}
