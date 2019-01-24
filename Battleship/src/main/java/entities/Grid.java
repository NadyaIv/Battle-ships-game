package entities;

import constants.Constants;
import interfaces.Ship;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Grid {
    private static final int COUNT_DIRECTION = 4;
    private static final int LEFT_DIRECTION = 0;
    private static final int RIGHT_DIRECTION = 1;
    private static final int DOWN_DIRECTION = 2;
    private static final int UP_DIRECTION = 3;
    private Random random;
    private String[][] grid;
    private List<Ship> ships;


    public Grid() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this.random = new Random();
        this.ships = new ArrayList<Ship>();
        setGrid(new String[Constants.COUNT__ROWS+1][Constants.COUNT_COLUMNS+1]);
        addShips();
        fillGridWithShips();
    }
// create different type of ships;
 //   return created List<Ships>
    private Ship createShip(Class<?> classShip) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Ship> ships = new ArrayList<Ship>();
        return (Ship) classShip.getConstructor().newInstance();

    }
    // add created List<Ship> to private filed ships;
    private void addShips() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Ship ship=this.createShip(Battleship.class);
        Ship ship2=this.createShip(Destroyer.class);
        Ship ship3=this.createShip(Destroyer.class);
        List<Ship> ships=new ArrayList<Ship>();
        ships.add(ship);
        ships.add(ship2);
       ships.add(ship3);
       this.setShips(ships);
    }

    public List<Ship> getShips() {
        return Collections.unmodifiableList(this.ships);
    }

    private void setShips(List<Ship> ships) {
        this.ships = ships;
    }


    public String[][] getGrid() {
        return this.grid;
    }
// create initial empty Grid
    private void setGrid(String[][] grid) {
        this.grid = grid;
        List<String> alphabet= Arrays.asList("A","B","C","D","E","F","G","H","I","J");
        grid[0][0]="  ";
        for (int i = 1; i<grid.length ; i++) {
            grid[0][i]=String.valueOf(i-1);
        }
        for (int i = 1; i <grid.length; i++) {
            grid[i][0]=alphabet.get(i-1)+" ";
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                grid[i][j] = ".";
            }
        }

    }
// fill grid with created list<Ship>
    private void fillGridWithShips() {
        for (Ship ship : this.ships) {
            boolean isFill = false;
            while (true) {
                // if set ship -> break, and go to set other ship
                if (isFill) {
                    break;
                }
                // get initial RowPoint of the ship
                int row = ship.getFirstRowPoint()+1;
                // get initial ColumnPoint of the ship
                int column = ship.getFirstColumnPoint()+1;
                // get count of squares of the ship
                int countSquare = ship.getSquare();
                // get direction at random to set the ship from firstInitalpoint
                int getDirection = this.random.nextInt(COUNT_DIRECTION - 1);
                // if direction is left and have enough count of free squares in to the  left from intitial point -> set there the  ship
                if (getDirection == LEFT_DIRECTION && column >= ship.getSquare()) {
                    if (isEmptyLeft(row, column, countSquare)) {
                        for (int j = 0; j < countSquare; j++) {
                            this.grid[row][column--] = "S";
                        }
                        isFill = true;
                    }
                }
                // if direction is right and have enough count of free squares to the  right from intitial point -> set there the  ship
                if (getDirection == RIGHT_DIRECTION && (Constants.COUNT_COLUMNS - column) >= countSquare) {
                    if (isEmptyRight(row, column, countSquare)) {
                        for (int j = 0; j < countSquare; j++) {
                            this.grid[row][column++] = "S";

                        }
                        isFill = true;
                    }
                }
               // if direction is down and have enough count of free squares to the  down from intitial point -> set there the  ship
                if (getDirection == DOWN_DIRECTION && (Constants.COUNT__ROWS - row) >= countSquare) {
                    if (isEmptyDown(row, column, countSquare)) {
                        for (int j = 0; j < countSquare; j++) {
                            this.grid[row++][column] = "S";
                        }
                        isFill = true;
                    }
                }
                // if direction is up and have enough count of free squares to the up from intitial point -> set there the  ship
                if (getDirection == UP_DIRECTION && row >= countSquare) {
                    if (isEmptyUP(row, column, countSquare)) {
                        for (int j = 0; j < countSquare; j++) {
                            this.grid[row--][column] = "S";
                        }
                        isFill = true;
                    }
                }
            }
        }

    }
// check if Up from initialPoint have others ssquares of others ships;
  //  depend from s squares of the ship
    private boolean isEmptyUP(int row, int column, int countSquare) {
        for (int j = 0; j < countSquare; j++) {
            if (this.grid[row][column].equals("S")) {
                return false;
            }
            ;
            row--;
        }
        return true;
    }
    // check if Down from initialPoint have others squares of others ships;
    //  depend from  squares of the ship
    private boolean isEmptyDown(int row, int column, int countSquare) {
        for (int j = 0; j < countSquare; j++) {
            if (this.grid[row][column].equals("S")) {
                return false;
            }
            ;
            row++;
        }
        return true;
    }
    // check if Right  from initialPoint have others squares of others ships;
    //  depend from  squares of the ship
    private boolean isEmptyRight(int row, int column, int countSquare) {
        for (int j = 0; j < countSquare; j++) {
            if (this.grid[row][column].equals("S")) {
                return false;
            }
            column++;

        }
        return true;
    }
    // check if Left from initialPoint have others squares of others ships;
    //  depend from  squares of the ship
    private boolean isEmptyLeft(int row, int column, int countSquare) {
        for (int j = 0; j < countSquare; j++) {
            if (this.grid[row][column].equals("S")) {
                return false;
            }
            column--;
        }

        return true;
    }
// take all numbers of squares of ships;
    // used in class Manager, to check if all ships are sinked ;
    public int getCountSquaresShip() {
        int count=0;
        for (Ship entity : this.ships) {
            count+=entity.getSquare();
        }
        return count;
    }
    // print which ships are sinked
public void showSinkedShips(){
    for (int i = 0; i < this.grid[0].length; i++) {
        System.out.print(this.grid[0][i]);
    }
    for (int i = 0; i < this.grid.length; i++) {
        System.out.print(this.grid[i][0]);
        for (int j = 0; j < this.grid[i].length; j++) {
            if(this.grid[i][j].equals("X")) {
                System.out.print(this.grid[i][j]);
            }
        }
        System.out.println();
    }
}
// print current status of grid : sinked and non-sinked ship, miss shot
    public void printGrid() {
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                System.out.print(this.grid[i][j]);
            }
            System.out.println();
        }
    }

}
