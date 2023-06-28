import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	
	static int N;
	static int X;
	static int[][] roads;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = reader.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		X = Integer.parseInt(input[2]) - 1;
		
		roads = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) roads[i][j] = Integer.MAX_VALUE;
			roads[i][i] = 0;
		}
		
		for (int i = 0; i < M; i++) {
			input = reader.readLine().split(" ");
			int start = Integer.parseInt(input[0]) - 1;
			int end = Integer.parseInt(input[1]) - 1;
			int time = Integer.parseInt(input[2]);
			
			roads[start][end] = time;
		}
		
		writer.write(Integer.toString(max()));
		
		writer.close();
		reader.close();
	}
	
	static int max() {
		int[] party = new int[N];
		for (int i = 0; i < N; i++) party[i] = roads[i][X];
		
		boolean[] visited = new boolean[N];
		visited[X] = true;
		for (int i = 0; i < N - 1; i++) {
			int next = -1;
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < N; j++) {
				if (visited[j]) continue;
				if (party[j] < min) {
					next = j;
					min = party[j];
				}
			}
			
			visited[next] = true;
			for (int j = 0; j < N; j++) {
				if (visited[j] || roads[j][next] == Integer.MAX_VALUE) continue;
				party[j] = Math.min(party[next] + roads[j][next], party[j]);
			}
		}
		
		int[] home = new int[N];
		for (int i = 0; i < N; i++) home[i] = roads[X][i];
		
		visited = new boolean[N];
		visited[X] = true;
		for (int i = 0; i < N - 1; i++) {
			int next = -1;
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < N; j++) {
				if (visited[j]) continue;
				if (home[j] < min) {
					next = j;
					min = home[j];
				}
			}
			
			visited[next] = true;
			for (int j = 0; j < N; j++) {
				if (visited[j] || roads[next][j] == Integer.MAX_VALUE) continue;
				home[j] = Math.min(home[next] + roads[next][j], home[j]);
			}
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) max = Math.max(party[i] + home[i], max);
		return max;
	}

}
