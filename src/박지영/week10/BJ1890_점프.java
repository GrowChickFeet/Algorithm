package 박지영.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 다이나믹프로그래밍을 이용해서 이동프
 * 특정 지점도착 -> 해당 지점의 숫자만큼 이동한 y, x값을 visited에 더하면서 이동
 * 반복이 끝나면 visited[N][X]가 이동 가능한 경로의 수
 */
public class BJ1890_점프 {
    static int N;
    static int[][] map;
    static long[][] visited;   // 경로의 개수 최대 : 2^63-1
    static int[][] dir = {{1, 0}, {0, 1}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new long[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) continue;       // 도착

                if (visited[i][j] != 0) {
                    for (int k = 0; k < 2; k++) {
                        int ny = i + dir[k][0] * map[i][j];
                        int nx = j + dir[k][1] * map[i][j];

                        if (ny >= N || ny < 0 || nx >= N || nx < 0) continue;

                        visited[ny][nx] += visited[i][j];
                    }

                }
            }
        }

        System.out.print(visited[N-1][N-1]);
    }
}