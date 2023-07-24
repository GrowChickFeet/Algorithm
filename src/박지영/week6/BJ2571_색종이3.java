package 박지영.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 누적합을 이용한 넓이 구하기
 * 1. 세로로 누적합을 통해서 길이를 구한다 : getLength()
 * 2. 모든 칸에 대하여 옆으로 이동하면서 길이(누적합으로 구한 값)의 Min값을 구한다. -> 넓이 구하기 : getSum()
 */
public class BJ2571_색종이3 {
    static int N, maxValue;
    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[100][100];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            // 종이의 위치 표시
            for (int j = y; j < y+10; j++) {
                for (int k = x; k < x+10; k++) {
                    map[j][k] = 1;
                }
            }
        }

        getLength();
        getSum();
        System.out.println(maxValue);

    }

    // 최대의 넓이를 구하는 함수
    static void getSum() {
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                int h = 100;

                for (int i = x+1; i < 100; i++) {
                    h = Math.min(h, map[y][i]);
                    if (h == 0) break;

                    maxValue = Math.max(maxValue, h * (i-x));
                }
            }
        }
    }

    static void getLength() {           // 누적합을 구하는 함수 : 위에서 아래로 더하기
        for (int x = 0; x < 100; x++) {
            for (int y = 1; y < 100; y++) {
                if (map[y][x] != 0) {
                    map[y][x] += map[y-1][x];
                }
            }
        }
    }
}