package no.h598062.battleships;

public enum GamePresets {
	SMALL5(5, 2, 1, 1), SMALL7(7, 2, 2, 1), SMALL9(9, 3, 2, 1), MEDIUM12(12, 3, 3, 2), MEDIUM15(15, 5, 3, 2), LARGE20(20, 7, 5, 4);

	private final int smallShips;
	private final int mediumShips;
	private final int largeShips;
	private final int totalShips;
	private final int boardsize;

	GamePresets(int boardsize, int smallShips, int mediumShips, int largeShips) {
		this.boardsize   = boardsize;
		this.smallShips  = smallShips;
		this.mediumShips = mediumShips;
		this.largeShips  = largeShips;
		this.totalShips  = smallShips + mediumShips + largeShips;
	}

	public int getBoardsize() {
		return boardsize;
	}

	public int getSmallShips() {
		return smallShips;
	}

	public int getMediumShips() {
		return mediumShips;
	}

	public int getLargeShips() {
		return largeShips;
	}

	public int getTotalShips() {
		return totalShips;
	}

	@Override
	public String toString() {
		return name() + ":\n" + "\tBoardsize: " + boardsize + "\tTotal Ships: " + totalShips + "\n\tSmall Ships: " + smallShips + "\tMedium Ships: " +
		       mediumShips + "\tLarge Ships: " + largeShips;
	}
}
