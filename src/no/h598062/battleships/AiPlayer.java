package no.h598062.battleships;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AiPlayer extends Player {
	Random r;

	List<Pos> unTargetedPositions;
	List<Pos> unTestedPlacePositions;

	public AiPlayer(Board board) {
		super(board);
		this.setHuman(false);
		this.r                   = new Random();
		this.unTargetedPositions = new ArrayList<>(25);
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
	public Pos shootPosition() {
		int randomIndex = r.nextInt(unTargetedPositions.size());
		return unTargetedPositions.remove(randomIndex);
	}

	@Override
	public Pos placeShip(ShipSize size) {
		int randomIndex = r.nextInt(unTestedPlacePositions.size());
		return unTestedPlacePositions.remove(randomIndex);
	}
}
