package no.h598062.battleships;

import java.util.LinkedList;
import java.util.List;

public class Board {
	private final int width;  // 1 - 25
	private final int height; // 1 - 25
	private final List<Ship> ships;

	public Board(int width, int height) {
		this.width  = width;
		this.height = height;
		ships = new LinkedList<>();
	}

	public void addShip(Pos startPos, boolean horizontal, ShipSize size) throws IllegalArgumentException, UnsupportedOperationException {
		if (startPos.getX() > 65 + width || startPos.getY() > height) {
			throw new IllegalArgumentException("Position is out of bounds");
		}
		if ((horizontal && startPos.getX() + size.getSize() > 65 + width) || (!horizontal && startPos.getY() + size.getSize() > height)) {
			throw new IllegalArgumentException("Part(s) of the ship is out of bounds");
		}
		Ship s = new Ship(startPos, horizontal, size);
		if (ships.contains(s)) {
			throw new UnsupportedOperationException("Ship already exists at position");
		}
		ships.add(s);
	}

	private Ship isHit(Pos pos) {
		for (Ship ship : ships) {
			if (ship.checkPosition(pos)) {
				return ship;
			}
		}
		return null;
	}

	public boolean checkHitAndSinkShip(Pos pos) {
		Ship hitShip = isHit(pos);
		if (hitShip != null) {
			ships.remove(hitShip);
			return true;
		}
		return false;
	}

}
