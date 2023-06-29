import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

class Status {
	
	int x;
	int y;
	int time;
	boolean haveGramr;
	
	public Status(int x, int y, int time, boolean haveGramr) {
		this.x = x;
		this.y = y;
		this.time = time;
		this.haveGramr = haveGramr;
	}
	
}

public class Main {
	
	static final int DIRECTIONS = 4;
	static final int[] dx = { 1, -1, 0, 0 };
	static final int[] dy = { 0, 0, 1, -1 };
	
	static int N;
	static int M;
	static int T;
	static int[][] castle;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = reader.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		T = Integer.parseInt(input[2]);
		
		castle = new int[N][M];
		for (int i = 0; i < N; i++) {
			input = reader.readLine().split(" ");
			for (int j = 0; j < M; j++) castle[i][j] = Integer.parseInt(input[j]);
		}
		
		int time = rescue();
		writer.write(time != -1 ? Integer.toString(time) : "Fail");
		
		writer.close();
		reader.close();
	}
	
	static int rescue() {
		boolean[][][] visited = new boolean[N][M][2];
		
		Queue<Status> queue = new LinkedList<>();
		
		Status status = new Status(0, 0, 0, castle[0][0] == 2);
		visited[0][0][status.haveGramr ? 1 : 0] = true;
		queue.offer(status);
		
		while (!queue.isEmpty()) {
			status = queue.poll();
			
			if (status.time >= T) continue;
			
			for (int i = 0; i < DIRECTIONS; i++) {
				int nx = status.x + dx[i];
				int ny = status.y + dy[i];
				
				if (nx == N - 1 && ny == M - 1) return status.time + 1;
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (!status.haveGramr && castle[nx][ny] == 1) continue;
				
				boolean haveGramr = status.haveGramr || castle[nx][ny] == 2;
				
				if (visited[nx][ny][haveGramr ? 1 : 0]) continue;
				
				Status next = new Status(nx, ny, status.time + 1, haveGramr);
				visited[nx][ny][haveGramr ? 1 : 0] = true;
				queue.offer(next);
			}
		}
		
		return -1;
	}
	
}
