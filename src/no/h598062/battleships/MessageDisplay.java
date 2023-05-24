package no.h598062.battleships;

public class MessageDisplay {

	public static void println(String message) {
		System.out.println(message);
	}

	public static void print(String message) {
		System.out.print(message);
	}

	private MessageDisplay() {
	}

	public static void printSeparator(int length) {
		System.out.println("=".repeat(length));
	}
}
