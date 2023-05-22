package no.h598062.battleships;

import java.util.List;

public class Board {
	private int        width;  // 1 - 25
	private int        height; // 1 - 25
	private List<Ship> ships;

	public void addShip(Pos startPos, boolean horizontal, ShipSize size) {
		if (startPos.getX() > 65 + width || startPos.getY() > height) {
			throw new IllegalArgumentException("Position is out of bounds");
		}
		if ((horizontal && startPos.getX() + size.getSize() > 65 + width) || (!horizontal && startPos.getY() + size.getSize() > height)) {
			throw new IllegalArgumentException("Part(s) of the ship is out of bounds");
		}
		Ship s = new Ship(startPos, horizontal, size);
		if (ships.contains(s)) {
			throw new UnsupportedOperationException("Ship already exists");
		}
		ships.add(s);
	}

}
