package no.h598062.battleships;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private final List<Pos> targetedPositions;
	private boolean isHuman;

	private Board board;

	public Player(Board board) {
		this.isHuman           = true;
		this.targetedPositions = new ArrayList<>(20);
		this.board             = board;
	}

	public Pos shootPosition() {
		return PlayerInputHandler.askForTargetPosition();
	}

	public Pos placeShip(ShipSize  size) {
		return PlayerInputHandler.askForShipPosition(size);
	}

	private List<Pos> getTargetedPositions() {
		return targetedPositions;
	}
	public boolean isHuman() {
		return isHuman;
	}

	public void setHuman(boolean human) {
		isHuman = human;
	}

	public Board getBoard() {
		return this.board;
	}
}
