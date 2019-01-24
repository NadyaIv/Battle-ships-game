package entities;

import constants.Constants;

import java.util.Random;

public class Battleship extends BaseEntity{
    private final Integer COUNT_SQUARE=5;


    public Battleship() {
        super.setSquare(COUNT_SQUARE);

    }

}
