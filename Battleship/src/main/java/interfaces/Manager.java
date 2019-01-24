package interfaces;

import entities.Player;

import java.io.IOException;

public  interface Manager {
    public void addPlayer(Player player);
    public void play() throws IOException;
}
