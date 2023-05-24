package no.h598062.battleships.ship;

public enum ShipSize {
	SMALL(2), MEDIUM(3), LARGE(4);

	private final int size;

	ShipSize(int size) {
		if (size < 2 || size > 4) {
			throw new IllegalArgumentException("Invalid size value for ShipSize enum.");
		}
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return name() + " (" + size + ")";
	}
}
