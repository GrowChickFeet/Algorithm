package 박지영.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17406_배열돌리기4 {

    static int[][] arr, tmpArr;
    static int N, M, K, result =Integer.MAX_VALUE;
    static boolean isSelected[];
    static int[] numbers;
    static int[][] rotates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
//        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        tmpArr = new int[N][M];
        numbers = new int[K];
        isSelected = new boolean[K];
        rotates = new int[K][3];

        for (int i = 0; i < N; i++) {                       // 배열 입력받기
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {                       // 회전 입력 받기
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                rotates[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        collection(0);
        System.out.print(result);
    }

    static void copy() {                    // 복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpArr[i][j] = arr[i][j];
            }
        }
    }

    static void collection(int cnt) {       // 순열
        if (cnt == K) {
            copy();
            for (int i : numbers) {
                rotate(rotates[i][0]-1, rotates[i][1]-1, rotates[i][2]);         // 1부터 시작해서 -1
            }
            findMinSum();
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!isSelected[i]) {
                numbers[cnt] = i;
                isSelected[i] = true;

                collection(cnt+1);
                isSelected[i] = false;
            }
        }
    }

    static void findMinSum() {      // 최소 찾기 계산
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += tmpArr[i][j];
            }
            result = Math.min(result, sum);
        }
    }

    static void rotate(int r, int c, int S) {       // 회전

        for (int s = 1; s <= S; s++) {
            // 임시 저장
            int tmp = tmpArr[r-s][c-s];

            // 왼쪽 이동
            for (int i = r-s; i < r+s; i++) {
                tmpArr[i][c-s] = tmpArr[i+1][c-s];
            }

            // 아래 이동
            for (int i = c-s; i < c+s; i++) {
                tmpArr[r+s][i] = tmpArr[r+s][i+1];
            }

            // 오른쪽 이동
            for (int i = r+s; i > r-s; i--) {
                tmpArr[i][c+s] = tmpArr[i-1][c+s];
            }

            // 왼쪽 이동
            for (int i = c+s; i > c-s+1; i--) {
                tmpArr[r-s][i] = tmpArr[r-s][i-1];
            }

            tmpArr[r-s][c-s+1] = tmp;
        }

    }

}
