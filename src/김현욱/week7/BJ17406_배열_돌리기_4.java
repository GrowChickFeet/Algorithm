package 김현욱.week7;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ17406_배열_돌리기_4 {
    static int N, M, K,result = Integer.MAX_VALUE;
    static int[][] maze;

    static int[][] operations;
    static int[] order;
    static boolean[] visited;

    private static void spin(int[][] copy, int od) {
        int x = operations[od][0]-1;
        int y = operations[od][1]-1;
        int len = operations[od][2];

        int x1 = x-len;
        int y1 = y-len;

        int x2 = x+len;
        int y2 = y+len;

        while(x1!=x2){
            int first = copy[x1][y1];
            for(int i=x1;i<x2;i++){
                copy[i][y1] = copy[i+1][y1];
            }
            for(int i=y1;i<y2;i++){
                copy[x2][i] = copy[x2][i+1];
            }
            for(int i=x2;i>x1;i--){
                copy[i][y2] = copy[i-1][y2];
            }
            for(int i=y2;i>y1;i--){
                copy[x1][i] = copy[x1][i-1];
            }
            copy[x1][y1+1] = first;

            x1++;
            y1++;
            x2--;
            y2--;
        }
    }

    public static void solution(){
        int[][] copy = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                copy[i][j] = maze[i][j];
            }
        }
        //순서에 따라 배열 회전
        for(int i=0;i<K;i++){
            spin(copy,order[i]);
        }
        //값 갱신
        int min = Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            int sum = 0;
            for(int j=0;j<M;j++){
                sum+=copy[i][j];
            }

            min = Math.min(min,sum);
        }
        result = Math.min(min,result);
    }


    public static void dfs(int index) {
        if (index == K) {
            solution();
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[index] = i;
                dfs(index + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        operations = new int[K][3];
        order = new int[K];
        visited = new boolean[K];

        for (int i = 0; i < N; i++) {
            maze[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i=0;i<K;i++){
            operations[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dfs(0);

        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
