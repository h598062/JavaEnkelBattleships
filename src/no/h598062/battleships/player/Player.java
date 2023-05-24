package no.h598062.battleships.player;

import no.h598062.battleships.game.Board;
import no.h598062.battleships.util.PlayerInputHandler;
import no.h598062.battleships.util.Pos;
import no.h598062.battleships.ship.ShipSize;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	List<Pos> missedShots;
	List<Pos> hitShots;
	Board     board;

	PlayerInputHandler inputHandler;

	protected Player(Board board) {
		this.missedShots = new ArrayList<>(20);
		this.hitShots    = new ArrayList<>(20);
		this.board       = board;
		this.inputHandler = new PlayerInputHandler(this instanceof AiPlayer);
	}

	public abstract Pos getShootPosition();

	public abstract Pos placeShip(ShipSize size);

	public String showAllShotsBoardString() {
		char[][] boardCharArr = new char[board.getWidth()][board.getHeight()];
		for (int x = 0; x < board.getHeight(); x++) {
			for (int y = 0; y < board.getWidth(); y++) {
				boardCharArr[x][y] = '.';
			}
		}

		for (Pos pos : missedShots) {
			boardCharArr[pos.getXAsInt() - 1][pos.getY() - 1] = 'o';
		}
		for (Pos pos : hitShots) {
			boardCharArr[pos.getXAsInt() - 1][pos.getY() - 1] = 'x';
		}
		return Board.boardStringBuilder(boardCharArr);
	}

	public abstract boolean getHorizontal();

	public void addMissedShot(Pos pos) {
		missedShots.add(pos);
	}

	public void addHitShot(Pos pos) {
		hitShots.add(pos);
	}

	public Board getBoard() {
		return this.board;
	}
}
