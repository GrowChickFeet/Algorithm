package 남현실.week6;

/*
https://www.acmicpc.net/problem/2571
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
[입력]
- N: 색종이 수 (N <=100)
- x, y: 색종이 붙인 위치
- 도화지 밖으로 나가는 경우 없음

[조건]
- 도화지: 100*100
- 색종이: 10*10
- 자르는 직사각형 변과 평행

[출력]
- 잘라낼 수 있는 검은색 직사각형 최대 넒이

[풀이]
- 겹쳐져있거나 맞닿아있은 것의 공통 넓이
    - 그 중 가장 넓이가 넓은 것을 출력

 */

public class BJ2571_색종이_3 {
    static class box {
        int pos1, pos2, length;

        public box(int pos1, int pos2, int length) {
            this.pos1 = pos1;
            this.pos2 = pos2;
            this.length = length;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int TS = 100;
        int S = 10;

        int[][] map = new int[TS+2][TS+2]; // padding

        String[] temp;
        int x, y;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            x = Integer.parseInt(temp[0]);
            y = Integer.parseInt(temp[1]);
            for (int r = x; r < x + S; r++) {
                for (int c = y; c < y + S; c++) {
                    map[r][c] = 1;
                }
            }
        }

        // array copy
        int[][] sumMap = new int[TS+2][TS+2]; // padding
        for (int i = 0; i < TS+2; i++) {
            for (int j = 0; j < TS+2; j++) {
                sumMap[i][j] = map[i][j];
            }
        }

        // 누적합 구하기
        // x축
        for (int r = 1; r < TS+2; r++) {
            for (int c = 1; c < TS+2; c++) {
                sumMap[r][c] += sumMap[r][c-1];
            }
        }
        // y축
        for (int c = 1; c < TS+2; c++) {
            for (int r = 1; r < TS+2; r++) {
                sumMap[r][c] += sumMap[r-1][c];
            }
        }

        // 검사
        // x축
        for (int r = 1; r < TS+2; r++) {
            for (int c = 1; c < TS+2; c++) {
                if(map[r-1][c] == 0 && map[r][c] == 1) { // 0에서 1로 바뀌는 지점 (사각형이 시작되는 지점에서 찾기)
                    run(r, c);
                }
            }
        }

        // y축
//        for (int i = 0; i < TS+2; i++) {
//            System.out.println(sumMap[25][i]);
//        }
    }

    static void run(int r, int c) {

    }


}
