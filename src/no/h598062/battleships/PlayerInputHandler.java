package no.h598062.battleships;

import java.util.Scanner;

public class PlayerInputHandler {
	public static final Scanner SCANNER = new Scanner(System.in);


	private PlayerInputHandler() {
	}

	public static Pos askForTargetPosition() {
		return null;
	}

	public static int askForPreset() {
		return 0;
	}

	public static boolean askForAi() {
		return false;
	}

	public static boolean askIfAIisP1() {
		return false;
	}

	public static int askForBoardSize() {
		return 0;
	}

	public static int askForSmallShips() {
		return 0;
	}

	public static int askForMediumShips() {
		return 0;
	}

	public static int askForLargeShips() {
		return 0;
	}

	public static Pos askForShipPosition(ShipSize size) {
		return null;
	}

	public static boolean askIfShipIsHorizontal() {
		return false;
	}
}
