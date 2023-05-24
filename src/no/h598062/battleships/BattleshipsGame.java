package no.h598062.battleships;

import javax.swing.text.Position;

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
			boolean isAIP1 = PlayerInputHandler.askIfAIisP1();
			p1 = isAIP1 ? new AiPlayer(game.p1Board) : new Player(game.p1Board);
			p2 = isAIP1 ? new Player(game.p2Board) : new AiPlayer(game.p2Board);
		} else {
			p1 = new Player(game.p1Board);
			p2 = new Player(game.p2Board);
		}
		game.addPlayer(p1, true);
		game.addPlayer(p2, false);
		game.placeShips(p1);
		game.placeShips(p2);
		return game;
	}

	private void placeShips(Player p) {
		placeShips(p, ShipSize.SMALL);
		placeShips(p, ShipSize.MEDIUM);
		placeShips(p, ShipSize.LARGE);
	}

	private void placeShips(Player p, ShipSize shipSize) {
		int ships = 0;
		switch (shipSize) {
			case SMALL -> ships = smallShips;
			case MEDIUM -> ships = mediumShips;
			case LARGE -> ships = largeShips;
		}
		for (int i = 0; i < ships; i++) {
			boolean isValidPos = false;
			boolean isHorizontal = PlayerInputHandler.askIfShipIsHorizontal();
			while (!isValidPos) {
				Pos pos = p.placeShip(shipSize);
				try {
					p.getBoard()
					 .addShip(pos, isHorizontal, shipSize);

					isValidPos = true;
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (UnsupportedOperationException e) {
					throw new RuntimeException(e);
				}
			}
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
