package no.h598062.battleships.util;

import no.h598062.battleships.game.GamePresets;
import no.h598062.battleships.ship.ShipSize;

import java.util.Scanner;

public class PlayerInputHandler {
	public static final Scanner SCANNER = new Scanner(System.in);

	private final boolean isAi;

	public PlayerInputHandler(boolean isAi) {
		this.isAi = isAi;
	}

	private static boolean askForBoolean(String message, String optionsOrExample) {
		MessageDisplay.println(message + " " + optionsOrExample);
		MessageDisplay.print("Input: ");
		String input = SCANNER.nextLine();
		if (input.equals("")) {
			MessageDisplay.println("Please write " + optionsOrExample + "or press CTRL-C to exit");
			return askForBoolean(message, optionsOrExample);
		}
		if (input.equals("y") || input.equals("Y") || input.equals("1")) {
			return true;
		} else if (input.equals("n") || input.equals("N") || input.equals("0")) {
			return false;
		}
		MessageDisplay.println("Please write a valid input or press CTRL-C to exit");
		return askForBoolean(message, optionsOrExample);
	}

	private static Pos askForPosition(String message, String optionsOrExample) {
		MessageDisplay.println(message + " " + optionsOrExample);
		MessageDisplay.print("Input: ");
		String input = SCANNER.nextLine();
		if (input.equals("")) {
			MessageDisplay.println("Please write ex. " + optionsOrExample + "or press CTRL-C to exit");
			return askForPosition(message, optionsOrExample);
		}
		Pos pos;
		try {
			pos = Pos.createPos(input);
		} catch (IllegalArgumentException e) {
			MessageDisplay.println(e.getMessage());
			return askForPosition(message, optionsOrExample);
		}
		return pos;
	}

	public static int askForPreset() {
		MessageDisplay.println("Please select a preset:");
		GamePresets[] values = GamePresets.values();
		MessageDisplay.println("0. Custom");
		for (int i = 0; i < values.length; i++) {
			GamePresets preset = values[i];
			MessageDisplay.println(i + 1 + ". " + preset.name());
		}
		MessageDisplay.print("Select number: ");
		String input = SCANNER.nextLine();
		if (input.equals("")) {
			MessageDisplay.println("Please write a number or press CTRL-C to exit");
			return askForPreset();
		}
		int preset;
		try {
			preset = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			MessageDisplay.println("Please write a valid number or press CTRL-C to exit");
			return askForPreset();
		}
		return preset;
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

	public Pos askForShipPosition(ShipSize size) {
		if (isAi) {
			return null;
		}
		return askForPosition("Please type the position you want to place your ship. " + size.toString(), "(A1, B14)");
	}

	public Pos askForTargetPosition() {
		if (isAi) {
			return null;
		}
		return askForPosition("Please type the position you want to attack.", "(A1, B14)");
	}

	public boolean askIfShipIsHorizontal() {
		if (isAi) {
			return false;
		}
		return askForBoolean("Do you want the ship to be horizontal?", "(y/n)(Y/N)(1/0)");
	}

	public static boolean askForAi() {
		boolean ans = askForBoolean("Should the game include any AI's?", "(y/n)(Y/N)(1/0)");
		if (ans) {
			MessageDisplay.println("AI enabled");
		} else {
			MessageDisplay.println("AI disabled");
		}
		return ans;
	}

	public static boolean askIfPlayerIsAI(String player) {
		boolean ans = askForBoolean("Should " + player + " be an AI?", "(y/n)(Y/N)(1/0)");
		if (ans) {
			MessageDisplay.println(player + " will be an AI");
		} else {
			MessageDisplay.println(player + " will not be an AI");
		}
		return ans;
	}
}
