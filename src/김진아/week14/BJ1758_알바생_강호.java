package 김진아.week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Arrays;

public class BJ1758_알바생_강호 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(reader.readLine());
		int[] tips = new int[N];
		for (int i = 0; i < N; i++) tips[i] = Integer.parseInt(reader.readLine());
		
		Arrays.sort(tips);
		
		long sum = 0;
		for (int i = N - 1; i >= 0; i--) {
			int tip = tips[i] - N + i + 1;
			if (tip <= 0) break;
			sum += tip;
		}
		
		writer.write(Long.toString(sum));
		
		writer.close();
		reader.close();
	}

}
