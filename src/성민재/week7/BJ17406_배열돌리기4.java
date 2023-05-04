package 성민재.week7;
import java.io.*;
import java.util.*;
public class BJ17406_배열돌리기4 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static int N, M, K, ans_min, rotate[][], numbers[], matrix[][], matrix_tmp[][];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N 행
        M = Integer.parseInt(st.nextToken()); // M 열
        K = Integer.parseInt(st.nextToken()); // 회전

        rotate = new int[K][3];
        visited = new boolean[K];
        numbers = new int[K];

        ans_min = Integer.MAX_VALUE;

        matrix = new int[N + 1][M + 1]; // 행렬 구성.
        matrix_tmp = new int[N + 1][M + 1]; // 행렬 구성.
        int[][] matrix_re = new int[N + 1][M + 1]; // 역방향으로 변환시킬 행렬

        for(int i = 1 ; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                matrix_re[i][j] = matrix[i][j];
            }
        }

        for(int i = 0 ; i < K ; i++){
            st = new StringTokenizer(br.readLine());
            rotate[i][0] = Integer.parseInt(st.nextToken()); // r
            rotate[i][1] = Integer.parseInt(st.nextToken()); // c
            rotate[i][2] = Integer.parseInt(st.nextToken()); // s
        }

        dfs(0);
        System.out.println(ans_min);
    }

    public static void dfs(int cnt){
        if(cnt == K){
            for(int i = 1 ; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    matrix_tmp[i][j] = matrix[i][j];
                }
            }
            for(int num : numbers){
                rotation( rotate[num][0] - rotate[num][2], rotate[num][1] - rotate[num][2],
                        rotate[num][0] + rotate[num][2], rotate[num][1] + rotate[num][2], matrix_tmp);
            }
            check(matrix_tmp);
            return;
        }
        for(int i = 0 ; i < K; i++){
            if(visited[i]) continue;
            visited[i] = true;
            numbers[cnt] = i;
            dfs(cnt + 1);
            visited[i] = false;
        }

    }
    public static void rotation(int s_row, int s_col, int d_row, int d_col, int[][] tmp_matrix){

        int rmax = d_row, cmax = d_col, rmin = s_row, cmin = s_col;
        int cur = 0;
        while(rmax > rmin && cmax > cmin){

            int prev = tmp_matrix[rmin+1][cmin];
            for(int i = cmin; i <= cmax ; i++){  //위
                cur = tmp_matrix[rmin][i];
                tmp_matrix[rmin][i] = prev;
                prev = cur;
            }

            for(int i = rmin + 1; i <= rmax ; i++){ //오른쪽
                cur = tmp_matrix[i][cmax];
                tmp_matrix[i][cmax] = prev;
                prev = cur;
            }

            for(int i = cmax - 1; i >= cmin ; i--){ //아래
                cur = tmp_matrix[rmax][i];
                tmp_matrix[rmax][i] = prev;
                prev = cur;
            }

            for(int i = rmax - 1; i > rmin ; i--){ //왼쪽
                cur = tmp_matrix[i][cmin];
                tmp_matrix[i][cmin] = prev;
                prev = cur;
            }
            rmax--;
            cmax--;
            rmin++;
            cmin++;
        }
    }
    public static void check(int[][] tmp_matrix){
        for(int i = 1 ; i <= N; i++){
            int sum = 0;
            for(int j = 1 ; j <= M; j++){
                sum += tmp_matrix[i][j];
            }
            ans_min = Math.min(ans_min, sum);
        }
    }
}
