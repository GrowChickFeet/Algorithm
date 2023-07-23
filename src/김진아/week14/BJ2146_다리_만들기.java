import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	final static int ISLAND = 10;
	final static int DIRECTIONS = 4;
	final static int[] dx = { 1, -1, 0, 0 };
	final static int[] dy = { 0, 0, 1, -1 };
	
	static int N;
	static int[][] map;
	
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(reader.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] input = reader.readLine().split(" ");
			for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(input[j]);
		}
		
		setIsland();
		
		min = Integer.MAX_VALUE;
		findBridge();
		
		writer.write(Integer.toString(min));
		
		writer.close();
		reader.close();
	}
	
	static void findBridge() {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (map[x][y] != 0) min = Math.min(findBridge(map[x][y], x, y), min);
			}
		}
	}
	
	static int findBridge(int island, int x, int y) {
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> queue = new LinkedList<>();
		
		visited[x][y] = true;
		queue.offer(new int[] { x, y, 0 });
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for (int i = 0; i < DIRECTIONS; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] == island) continue;
				
				if (map[nx][ny] != 0) return now[2];
				visited[nx][ny] = true;
				queue.offer(new int[] { nx, ny, now[2] + 1 });
			}
		}
		
		return Integer.MAX_VALUE;
	}
	
	static void setIsland() {
		int island = ISLAND;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (map[x][y] == 1) setIsland(island++, x, y);
			}
		}
	}
	
	static void setIsland(int island, int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		
		map[x][y] = island;
		queue.offer(new int[] { x, y });
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for (int i = 0; i < DIRECTIONS; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != 1) continue;
				
				map[nx][ny] = island;
				queue.offer(new int[] { nx, ny });
			}
		}
	}
	
}
