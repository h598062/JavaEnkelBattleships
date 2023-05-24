package no.h598062.battleships;

public class Main {

	public static void main(String[] args) {
		MessageDisplay.println("Welcome to Battleships!");
		BattleshipsGame game = BattleshipsGame.createGame();
		game.start();
	}
}
