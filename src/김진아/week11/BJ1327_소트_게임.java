import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

class Status {
	
	int[] arr;
	int count;
	
	public Status(int[] arr, int count) {
		this.arr = arr;
		this.count = count;
	}
	
}

public class Main {
	
	static int N;
	static int K;
	static int sorted;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = reader.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		sorted = 1;
		for (int i = 2; i <= N; i++) sorted = sorted * 10 + i;
		
		input = reader.readLine().split(" ");
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(input[i]);
		
		writer.write(Integer.toString(sort(arr)));
		
		writer.close();
		reader.close();
	}
	
	static int sort(int[] arr) {
		HashSet<Integer> visited = new HashSet<>();
		Queue<Status> queue = new LinkedList<>();
		
		int value = arr[0];
		for (int i = 1; i < N; i++) value = value * 10 + arr[i];
		
		if (value == sorted) return 0;
		visited.add(value);
		queue.offer(new Status(arr, 0));
		
		while (!queue.isEmpty()) {
			Status now = queue.poll();
			
			for (int i = 0; i <= N - K; i++) {
				int[] reverse = copy(now.arr);
				for (int j = 0; j < K / 2; j++) {
					reverse[i + j] += reverse[i + K - 1 - j];
					reverse[i + K - 1 - j] = reverse[i + j] - reverse[i + K - 1 - j];
					reverse[i + j] -= reverse[i + K - 1 - j];
				}
				
				value = reverse[0];
				for (int j = 1; j < N; j++) value = value * 10 + reverse[j];
				
				if (visited.contains(value)) continue;
				if (value == sorted) return now.count + 1;
				visited.add(value);
				queue.offer(new Status(reverse, now.count + 1));
			}
		}
		
		return -1;
	}
	
	static int[] copy(int[] origin) {
		int[] copy = new int[N];
		for (int i = 0; i < N; i++) copy[i] = origin[i];
		return copy;
	}
	
}
