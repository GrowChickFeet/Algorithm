import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	
	final static int DIRECTIONS = 4;
	final static int[] dx = { -1, 1, 0, 0 };
	final static int[] dy = { 0, 0, -1, 1 };
	
	static int N;
	static int M;
	static int K;
	
	static int[][][] smell;
	static int[][] sharks;
	static int[][][] priority;
	
	static int time;
	static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = reader.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		
		smell = new int[N][N][2];
		sharks = new int[M][3];
		priority = new int[M][DIRECTIONS][DIRECTIONS];
		
		for (int i = 0; i < N; i++) {
			input = reader.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				int shark = Integer.parseInt(input[j]);
				if (shark != 0) {
					smell[i][j][0] = shark;
					sharks[shark - 1][0] = i;
					sharks[shark - 1][1] = j;
				}
			}
		}
		
		input = reader.readLine().split(" ");
		for (int i = 0; i < M; i++) sharks[i][2] = Integer.parseInt(input[i]) - 1;
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < DIRECTIONS; j++) {
				input = reader.readLine().split(" ");
				for (int k = 0; k < DIRECTIONS; k++) priority[i][j][k] = Integer.parseInt(input[k]) - 1;
			}
		}
		
		time = 0;
		count = M;
		while (count > 1 && time < 1000) move();
		
		writer.write(Integer.toString(count <= 1 ? time : -1));
		
		writer.close();
		reader.close();
	}
	
	static void move() {
		time++;
		for (int i = 0; i < M; i++) {
			if (sharks[i][0] != -1) move(i);
		}
		
		for (int i = 0; i < M; i++) {
			if (sharks[i][0] == -1) continue;
			
			if (smell[sharks[i][0]][sharks[i][1]][1] != time || i + 1 < smell[sharks[i][0]][sharks[i][1]][0]) {
				smell[sharks[i][0]][sharks[i][1]][0] = i + 1;
				smell[sharks[i][0]][sharks[i][1]][1] = time;
				continue;
			}

			count--;
			sharks[i][0] = -1;
			sharks[i][1] = -1;
			sharks[i][2] = -1;
		}
	}
	
	static void move(int shark) {
		for (int i = 0; i < DIRECTIONS; i++) {
			int nx = sharks[shark][0] + dx[priority[shark][sharks[shark][2]][i]];
			int ny = sharks[shark][1] + dy[priority[shark][sharks[shark][2]][i]];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || (smell[nx][ny][0] != 0 && smell[nx][ny][1] >= time - K)) continue;

			sharks[shark][0] = nx;
			sharks[shark][1] = ny;
			sharks[shark][2] = priority[shark][sharks[shark][2]][i];
			
			return;
		}

		for (int i = 0; i < DIRECTIONS; i++) {
			int nx = sharks[shark][0] + dx[priority[shark][sharks[shark][2]][i]];
			int ny = sharks[shark][1] + dy[priority[shark][sharks[shark][2]][i]];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || smell[nx][ny][0] != shark + 1) continue;

			sharks[shark][0] = nx;
			sharks[shark][1] = ny;
			sharks[shark][2] = priority[shark][sharks[shark][2]][i];
			
			return;
		}
	}
	
}
