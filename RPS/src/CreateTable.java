import com.jakewharton.fliptables.FlipTable;

public class CreateTable {
	public static void help(String[] moves) {
		int size = moves.length;
		String[] headers = new String[size + 1];
		String[][] data = new String[size][size + 1];
		headers[0] = "PC \\ USER";
		for (int i = 1; i <= size; i++) {
			headers[i] = moves[i - 1];
		}

		for (int j = 0; j <= size; j++)
			for (int i = 0; i < size; i++) {
				if (j == 0) {
					data[i][j] = moves[i];
				} else {
					data[i][j] = Winner.compareMoves(moves, j, i + 1);
				}
			}
		System.out.println(FlipTable.of(headers, data));
	}
}
