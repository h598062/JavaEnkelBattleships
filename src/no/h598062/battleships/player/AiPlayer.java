package no.h598062.battleships.player;

import no.h598062.battleships.game.Board;
import no.h598062.battleships.util.Pos;
import no.h598062.battleships.ship.ShipSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AiPlayer extends Player {
	Random r;

	List<Pos> unTargetedPositions;
	List<Pos> unTestedPlacePositions;

	public AiPlayer(Board board) {
		super(board);
		this.r                      = new Random();
		this.unTargetedPositions    = new ArrayList<>(25);
		this.unTestedPlacePositions = new ArrayList<>(25);
		fillPositionLists(this.getBoard()
		                      .getWidth(), this.getBoard()
		                                       .getHeight());
	}

	public void fillPositionLists(int width, int height) {
		for (int x = 0; x < width; x++) {
			for (int y = 1; y <= height; y++) {
				Pos pos = new Pos((char) (x + 65), y);
				unTargetedPositions.add(pos);
				unTestedPlacePositions.add(pos);
			}
		}
	}

	@Override
	public Pos getShootPosition() {
		int randomIndex = r.nextInt(unTargetedPositions.size());
		return unTargetedPositions.remove(randomIndex);
	}

	@Override
	public Pos placeShip(ShipSize size) {
		int randomIndex = r.nextInt(unTestedPlacePositions.size());
		return unTestedPlacePositions.remove(randomIndex);
	}

	@Override
	public boolean getHorizontal() {
		return r.nextBoolean();
	}
}
