package no.h598062.battleships.player;

import no.h598062.battleships.game.Board;
import no.h598062.battleships.util.Pos;
import no.h598062.battleships.ship.ShipSize;

public class HumanPlayer extends Player {
	public HumanPlayer(Board board) {
		super(board);
	}

	@Override
	public Pos getShootPosition() {
		return inputHandler.askForTargetPosition();
	}

	@Override
	public Pos placeShip(ShipSize size) {
		return inputHandler.askForShipPosition(size);
	}

	@Override
	public boolean getHorizontal() {
		return inputHandler.askIfShipIsHorizontal();
	}
}
