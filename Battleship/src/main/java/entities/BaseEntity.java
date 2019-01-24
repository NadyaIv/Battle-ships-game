package entities;

import constants.Constants;
import interfaces.Ship;

import java.util.Random;

public abstract class BaseEntity implements Ship {
    private Random random;
    private Integer square;
    private Integer firstRowPoint;
    private Integer firstColumnPoint;

    public BaseEntity() {
        this.random = new Random();
    }

    public Integer getFirstRowPoint() {
        return  this.random.nextInt(Constants.COUNT__ROWS);
    }

    private void setFirstRowPoint(Integer firstRowPoint) {
        this.firstRowPoint = firstRowPoint;
    }

    public Integer getFirstColumnPoint() {
        return  this.random.nextInt(Constants.COUNT_COLUMNS);
    }

    private void setFirstColumnPoint(Integer firstColumnPoint) {
        this.firstColumnPoint = firstColumnPoint;
    }

    public Integer getSquare() {
        return this.square;
    }

    protected void setSquare(Integer square) {
        this.square = square;
    }


}
