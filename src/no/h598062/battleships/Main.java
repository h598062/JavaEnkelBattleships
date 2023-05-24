package no.h598062.battleships;

import no.h598062.battleships.game.BattleshipsGame;
import no.h598062.battleships.util.MessageDisplay;

public class Main {

	public static void main(String[] args) {
		MessageDisplay.println("Welcome to Battleships!");
		BattleshipsGame game = BattleshipsGame.createGame();
		game.start();
	}
}
