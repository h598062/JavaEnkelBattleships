package no.h598062.battleships;

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
