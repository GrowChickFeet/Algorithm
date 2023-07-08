import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = reader.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		
		int[][] subjects = new int[K][2];
		for (int i = 0; i < K; i++) {
			input = reader.readLine().split(" ");
			subjects[i][0] = Integer.parseInt(input[0]);
			subjects[i][1] = Integer.parseInt(input[1]);
		}
		
		int[][] importance = new int[K + 1][N + 1];
		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				if (subjects[i - 1][1] > j) importance[i][j] = importance[i - 1][j];
				else importance[i][j] = Math.max(importance[i - 1][j - subjects[i - 1][1]] + subjects[i - 1][0], importance[i - 1][j]);
			}
		}
		
		writer.write(Integer.toString(importance[K][N]));
		
		writer.close();
		reader.close();
	}
	
}
