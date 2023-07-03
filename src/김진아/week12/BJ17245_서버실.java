import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Main {
	
	static ArrayList<Integer> server;
	static long count;
	static int height;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		server = new ArrayList<>();
		count = 0;
		height = 0;

		int N = Integer.parseInt(reader.readLine());
		for (int i = 0; i < N; i++) {
			String[] input = reader.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				int computers = Integer.parseInt(input[j]);
				server.add(computers);
				count += computers;
				height = Math.max(computers, height);
			}
		}
		
		writer.write(Integer.toString(time()));
		
		writer.close();
		reader.close();
	}
	
	static int time() {
		int time = 0;
		
		int left = 0;
		int right = height;
		while (left <= right) {
			int mid = (left + right) / 2;
			
			long sum = 0;
			for (int computers : server) sum += Math.min(computers, mid);
			
			if (sum * 2 < count) left = mid + 1;
			else {
				time = mid;
				right = mid - 1;
			}
		}
		
		return time;
	}
	
}
