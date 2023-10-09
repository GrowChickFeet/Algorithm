package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ4811_알약 {
	
	static long[][] count;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		count = new long[31][31];
		for (int i = 1; i <= 30; i++) count[i][0] = 1;
		count[1][1] = 1;
		
		for (int i = 1; i <= 30; i++) {
			for (int j = 1; j <= i; j++) {
				count[i][j] = count[i - 1][j] + count[i][j - 1];
			}
		}
		
		int N;
		while((N = Integer.parseInt(reader.readLine())) != 0) writer.append(Long.toString(count[N][N])).append("\n");
		
		writer.close();
		reader.close();
	}
	
}
