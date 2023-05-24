package no.h598062.battleships.ship;

import no.h598062.battleships.util.Pos;

import java.util.LinkedList;
import java.util.List;

public class Ship {
	private ShipSize  size;
	private boolean   horizontal;
	private Pos       start;
	private List<Pos> positions;

	public Ship(Pos start, boolean horizontal, ShipSize size) {
		if (start == null || size == null) {
			throw new IllegalArgumentException("Start and size must be specified");
		}
		this.start = start;
		this.horizontal = horizontal;
		this.positions = new LinkedList<>();
		if (horizontal) {
			createHorizontalPositions(start, size);
		} else {
			createVerticalPositions(start, size);
		}
	}

	private void createVerticalPositions(Pos start, ShipSize size) {
		char xpos = start.getX();
		int  ypos = start.getY();

		for (int i = 0; i < size.getSize(); i++) {
			positions.add(new Pos(xpos, ypos + i));
		}
	}

	private void createHorizontalPositions(Pos start, ShipSize size) {
		char xpos = start.getX();
		int  ypos = start.getY();

		for (int i = 0; i < size.getSize(); i++) {
			char newXpos = (char) (xpos + i); // char 'A' = 65, char A + 1 = B (66)
			positions.add(new Pos(newXpos, ypos));
		}
	}

	public boolean checkPosition(Pos pos) {
		return positions.contains(pos);
	}

	public boolean hit(Pos pos) {
		return positions.remove(pos);
	}

	public boolean isSunk() {
		return positions.isEmpty();
	}

	public List<Pos> getPositions() {
		return positions;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Ship ship = (Ship) o;

		if (horizontal != ship.horizontal) {
			return false;
		}
		if (size != ship.size) {
			return false;
		}
		return start.equals(ship.start);
	}

	@Override
	public int hashCode() {
		int result = size.hashCode();
		result = 31 * result + (horizontal ? 1 : 0);
		result = 31 * result + start.hashCode();
		return result;
	}
}
