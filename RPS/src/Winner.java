public class Winner {
	public static String compareMoves(String[] moves,int userMove, int compMove){  
		int sub = Math.abs(compMove - userMove);
        int countOfOptions = (moves.length - 1) / 2;
            if (compMove == userMove) {
                return "DRAW";
            } else if (compMove > userMove && countOfOptions >= sub || compMove < userMove && countOfOptions < sub) {
                return "LOSE";
            } else {
                return "WIN";
            }
	}

	public static void showWinner(String winner) {
		switch (winner) {
		case ("WIN"):
			System.out.println("You lose!");
		break;
		case ("LOSE"):
			System.out.println("You win!");
		break;
		default:
			System.out.println("Draw");
			break;
		}
	}
}
