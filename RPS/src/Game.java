import java.util.Scanner;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Game {

	public static String getMove(String[] moves) {
		System.out.print("Enter your move: ");
		Scanner inputScanner = new Scanner(System.in);
		String userInput = inputScanner.nextLine();
		int length = moves.length;
		boolean isInteger = userInput.matches("[-+]?\\d+");
		if(userInput.length() > 0) {
			if(userInput.equals("?") || userInput.equals("0") || isInteger && Integer.parseInt(userInput) <= length) return userInput;
		}
		Menu(moves);
		return getMove(moves);
	}

	public static void Menu(String[] moves) {
		System.out.println("Available moves:");
		for (int i = 0; i < moves.length; i++) {
			System.out.println(i+1 + " - " + moves[i]);
		}
		System.out.println("0 - exit\n? - help");
	}

	public static void startGame(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException {
		String[] moves = args;
		if(moves.length < 3) {
			System.out.println(
					"Error: Invalid number of arguments, the number of arguments must be more than 3,for example: \n"
							+ "Rock Paper Scissors or 1 2 3 4 5 6 7. Try again"
					);
		}else if(moves.length % 2 == 0) {
			System.out.println("Error: The number of arguments passed must be an odd number,for example: \n "
					+ "Rock Paper Scissors or 1 2 3 4 5 6 7. Try again"
					);
		}else {
			KeyGenerator generate = new KeyGenerator(moves);
			generate.showHMAC();
			Menu(moves);
			String userMove = getMove(moves);
			int computerMove = generate.getMove();

			if(userMove.equals("?")) {
				System.out.println("Your move: ?");
				CreateTable.help(moves);
			}else if(userMove.equals("0")) {
				System.out.println("Your move: 0 \nGame end");
			}else {
				int selected = Integer.parseInt(userMove) - 1;
				System.out.println("Your move: " + moves[selected]);
				System.out.println("Computer move: " + moves[computerMove]);
				Winner.showWinner(Winner.compareMoves(moves, selected, computerMove));
				generate.showKey();
			}
		}
	}

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException {
		startGame(args);
	}
}
