package no.h598062.battleships.util;

public class Pos {
	private char x; // 'A' - 'Z' (65 - 90)
	private int y; // 1 - 25

	public Pos(char x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Pos createPos(String position) throws IllegalArgumentException {
		if (position.length() < 2 || position.length() > 3) {
			throw new IllegalArgumentException("Invalid position String length");
		}
		char x = position.charAt(0);
		int y;
		try {
			y = Integer.parseInt(position.substring(1));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid Y position format,  must be a number");
		}
		if (x < 'A' || x > 'Z' || y < 1 || y > 25) { // 'A' = 65, 'Z' = 90, 90 - 65 = 25
			throw new IllegalArgumentException("X or Y position is out of range");
		}
		return new Pos(x, y);
	}

	public char getX() {
		return x;
	}

	public int getXAsInt() {
		return x - 64;
	}

	public void setX(char x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return String.valueOf(x) + y;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Pos pos = (Pos) o;

		if (x != pos.x) {
			return false;
		}
		return y == pos.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}
}
