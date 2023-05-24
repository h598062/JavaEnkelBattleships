package no.h598062.battleships.game;

import no.h598062.battleships.util.MessageDisplay;
import no.h598062.battleships.util.PlayerInputHandler;
import no.h598062.battleships.util.Pos;
import no.h598062.battleships.player.AiPlayer;
import no.h598062.battleships.player.HumanPlayer;
import no.h598062.battleships.player.Player;
import no.h598062.battleships.ship.Ship;
import no.h598062.battleships.ship.ShipSize;

public class BattleshipsGame {
	Board  p1Board;
	Player p1;
	Board  p2Board;
	Player p2;

	int totalShips;
	int smallShips;
	int mediumShips;
	int largeShips;

	private BattleshipsGame(int boardSize, int smallShips, int mediumShips, int largeShips) {
		p1Board          = new Board(boardSize, boardSize);
		p2Board          = new Board(boardSize, boardSize);
		this.totalShips  = smallShips + mediumShips + largeShips;
		this.smallShips  = smallShips;
		this.mediumShips = mediumShips;
		this.largeShips  = largeShips;
	}

	public BattleshipsGame(GamePresets preset) {
		int boardSize = preset.getBoardsize();
		p1Board     = new Board(boardSize, boardSize);
		p2Board     = new Board(boardSize, boardSize);
		totalShips  = preset.getTotalShips();
		smallShips  = preset.getSmallShips();
		mediumShips = preset.getMediumShips();
		largeShips  = preset.getLargeShips();
	}

	public void addPlayer(Player p, boolean isPlayer1) {
		if (isPlayer1) {
			p1 = p;
		} else {
			p2 = p;
		}
	}

	public void start() {
		MessageDisplay.printSeparator(p1Board.getWidth() * 3 + 4);
		MessageDisplay.println("Time to start the game!");
		MessageDisplay.printSeparator(p1Board.getWidth() * 3 + 4);
		MessageDisplay.println("Each player will take turns to fire at each other's ships.");
		MessageDisplay.println("The first player to sink all of the opponent's ships wins!");
		MessageDisplay.printSeparator(p1Board.getWidth() * 3 + 4);
		playLoop();
	}

	public void playLoop() {
		play(p1, "Player 1", p2Board);
		if (p2Board.hasNoShipsLeft()) {
			MessageDisplay.println("Player 1 won!");
			return;
		}
		play(p2, "Player 2", p1Board);
		if (p1Board.hasNoShipsLeft()) {
			MessageDisplay.println("Player 2 won!");
			return;
		}
		playLoop();
	}

	public void play(Player p, String name, Board opponentBoard) {
		MessageDisplay.printSeparator(p.getBoard()
		                               .getWidth() * 3 + 4);
		MessageDisplay.println(name + ", please select a coordinate to fire at.");
		MessageDisplay.println(p.showAllShotsBoardString());
		Pos  pos     = p.getShootPosition();
		Ship hitShip = opponentBoard.checkHit(pos);
		if (hitShip != null) {
			hitShip.hit(pos);
			MessageDisplay.println("Hit!");
			p.addHitShot(pos);
			MessageDisplay.println(p.showAllShotsBoardString());
			if (hitShip.isSunk()) {
				opponentBoard.sinkShip(hitShip);
				MessageDisplay.println("Ship sunk!");
				MessageDisplay.println("Opponents board has " + opponentBoard.getShips()
				                                                             .size() + " ships left!");
			}
		} else {
			MessageDisplay.println("Miss!");
			p.addMissedShot(pos);
			MessageDisplay.println(p.showAllShotsBoardString());
		}
	}

	public static BattleshipsGame createGame() {
		int             preset = PlayerInputHandler.askForPreset();
		BattleshipsGame game;
		game = switch (preset) {
			case 1 -> new BattleshipsGame(GamePresets.SMALL5);
			case 2 -> new BattleshipsGame(GamePresets.SMALL7);
			case 3 -> new BattleshipsGame(GamePresets.SMALL9);
			case 4 -> new BattleshipsGame(GamePresets.MEDIUM12);
			case 5 -> new BattleshipsGame(GamePresets.MEDIUM15);
			case 6 -> new BattleshipsGame(GamePresets.LARGE20);
			default -> createCustomGame();
		};
		boolean aiIsPlaying = PlayerInputHandler.askForAi();
		Player  p1;
		Player  p2;
		if (aiIsPlaying) {
			p1 = PlayerInputHandler.askIfPlayerIsAI("Player 1") ? new AiPlayer(game.p1Board) : new HumanPlayer(game.p1Board);
			p2 = PlayerInputHandler.askIfPlayerIsAI("Player 2") ? new AiPlayer(game.p2Board) : new HumanPlayer(game.p2Board);
		} else {
			p1 = new HumanPlayer(game.p1Board);
			p2 = new HumanPlayer(game.p2Board);
		}
		game.addPlayer(p1, true);
		game.addPlayer(p2, false);
		MessageDisplay.printSeparator(game.p1Board.getWidth() * 3 + 4);
		MessageDisplay.println("Time to place ships!");
		MessageDisplay.printSeparator(game.p1Board.getWidth() * 3 + 4);
		MessageDisplay.println("Player 1, place your ships!");
		MessageDisplay.println(Board.boardStringBuilder(Board.emptyBoard(game.p1Board.getWidth(), game.p1Board.getHeight())));
		game.placeShips(p1);
		MessageDisplay.printSeparator(game.p2Board.getWidth() * 3 + 4);
		MessageDisplay.println("Player 2, place your ships!");
		MessageDisplay.println(Board.boardStringBuilder(Board.emptyBoard(game.p2Board.getWidth(), game.p2Board.getHeight())));
		game.placeShips(p2);
		return game;
	}

	private void placeShips(Player p) {
		MessageDisplay.println("Place large ships. (4x1)");
		placeShips(p, ShipSize.LARGE);
		MessageDisplay.println("Place medium ships. (3x1)");
		placeShips(p, ShipSize.MEDIUM);
		MessageDisplay.println("Place small ships. (2x1)");
		placeShips(p, ShipSize.SMALL);
	}

	private void placeShips(Player p, ShipSize shipSize) {
		int ships = 0;
		switch (shipSize) {
			case SMALL -> ships = smallShips;
			case MEDIUM -> ships = mediumShips;
			case LARGE -> ships = largeShips;
		}
		for (int i = 0; i < ships; i++) {
			MessageDisplay.println("Ship " + (i + 1) + ": ");
			boolean isValidPos = false;
			boolean horizontal = p.getHorizontal();
			while (!isValidPos) {
				Pos pos = p.placeShip(shipSize);
				try {
					p.getBoard()
					 .addShip(pos, horizontal, shipSize);
					isValidPos = true;
				} catch (IllegalArgumentException e) {
					MessageDisplay.println(e.getMessage());
					MessageDisplay.println("Please input valid cordinates");
				} catch (UnsupportedOperationException e) {
					MessageDisplay.println(e.getMessage());
					MessageDisplay.println("Try another position");
				}
			}
			MessageDisplay.println("Ship placed!");
			MessageDisplay.println(p.getBoard()
			                        .showBoardString());
			MessageDisplay.printSeparator(p.getBoard()
			                               .getWidth() * 3 + 4);
		}
	}

	private static BattleshipsGame createCustomGame() {
		int boardSize   = PlayerInputHandler.askForBoardSize();
		int smallShips  = PlayerInputHandler.askForSmallShips();
		int mediumShips = PlayerInputHandler.askForMediumShips();
		int largeShips  = PlayerInputHandler.askForLargeShips();

		return new BattleshipsGame(boardSize, smallShips, mediumShips, largeShips);
	}
}
