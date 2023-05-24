package no.h598062.battleships;

import java.util.LinkedList;
import java.util.List;

public class Board {
	private final int        width;  // 1 - 25
	private final int        height; // 1 - 25
	private final List<Ship> ships;

	public Board(int width, int height) {
		this.width  = width;
		this.height = height;
		ships       = new LinkedList<>();
	}

	public void addShip(Pos startPos, boolean horizontal, ShipSize size) throws IllegalArgumentException, UnsupportedOperationException {
		if (startPos.getX() > 65 + width || startPos.getY() > height) {
			throw new IllegalArgumentException("Position is out of bounds");
		}
		if ((horizontal && startPos.getX() + size.getSize() > 65 + width) || (!horizontal && startPos.getY() + size.getSize() - 1 > height)) {
			throw new IllegalArgumentException("Part(s) of the ship is out of bounds");
		}
		Ship s = new Ship(startPos, horizontal, size);
		for (Pos pos : s.getPositions()) {
			if (checkHit(pos) != null) {
				throw new UnsupportedOperationException("Ship already exists at position");
			}
		}
		ships.add(s);
	}


	public Ship checkHit(Pos pos) {
		for (Ship ship : ships) {
			if (ship.checkPosition(pos)) {
				return ship;
			}
		}
		return null;
	}

	public void sinkShip(Ship ship) {
		if (ship != null && (ship.isSunk())) {
			ships.remove(ship);
		}
	}

	public String showBoardString() {
		char[][] boardCharArr = new char[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				boardCharArr[x][y] = '.';
			}
		}
		for (int i = 0; i < ships.size(); i++) {
			List<Pos> shipPositions = ships.get(i)
			                               .getPositions();
			for (Pos pos : shipPositions) {
				boardCharArr[pos.getXAsInt() - 1][pos.getY() - 1] = (char) ('a' + i);
			}
		}
		return boardStringBuilder(boardCharArr);
	}

	public static char[][] emptyBoard(int width, int height) {
		char[][] board = new char[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				board[x][y] = '.';
			}
		}
		return board;
	}

	public static String boardStringBuilder(char[][] chars) {
		StringBuilder sb = new StringBuilder();
		sb.append("   ");
		for (int i = 0; i < chars.length; i++) {
			sb.append(String.format(" %c ", (char) (65 + i)));
		}
		for (int y = 0; y < chars.length; y++) {
			sb.append("\n");
			sb.append(String.format("%2d ", y + 1));
			for (int x = 0; x < chars[0].length; x++) {
				sb.append(String.format(" %c ", chars[x][y]));
			}
		}
		return sb.toString();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean hasNoShipsLeft() {
		return ships.isEmpty();
	}

	public List<Ship> getShips() {
		return ships;
	}
}
