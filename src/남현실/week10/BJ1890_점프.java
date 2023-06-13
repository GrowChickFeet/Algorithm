package 남현실.week10;
/*
https://www.acmicpc.net/problem/1890
 */

/*
왼쪽 위칸에서 오른쪽 아래칸으로

규칙
- 가장 오른쪽아래칸(0)은 종착점
- 항상 현재 칸에 적혀있는 수만큼 오른쪽, 아래만
- 점프중엔 방향 못바꿈


가능한 경로 개수?
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1890_점프 {
    static int N; // 판의 크기
    static int[][] map;
    static long[][] counts; // 가능한 경로 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        String[] temp;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        counts = new long[N][N];
        counts[0][0] = 1;

        int jump;
        int nr, nc;
        long count;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if(r == N-1 && c == N-1) {
                    System.out.println(counts[N-1][N-1]);
                    break;
                }
                jump = map[r][c];
                count = counts[r][c];

                nr = r+jump;
                nc = c+jump;
                if(!outOfMap(nr, c)) { // 아래
                    counts[nr][c] += count;
                }
                if(!outOfMap(r, nc)) { // 오른쪽
                    counts[r][nc] += count;
                }
            }
        }
    }

    static void print2DArr(int[][] arr) {
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
    }


    static boolean outOfMap(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}