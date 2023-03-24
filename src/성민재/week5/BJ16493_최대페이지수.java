package 성민재.week5;

import java.io.*;
import java.util.StringTokenizer;

public class BJ16493_최대페이지수 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] books;
    static int N,M,ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //남은 기간
        M = Integer.parseInt(st.nextToken()); //챕터의 수

        books = new int[M][2];
        visited = new boolean[M];
        ans = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                books[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);
        System.out.println(ans);
    }
    public static void dfs(int cnt){
        if(cnt == M){
            int days = 0, pages = 0;
            for (int i = 0; i < M; i++) {
                if(visited[i]){
                    days += books[i][0];
                    pages += books[i][1];
                }
            }
            if(days <= N){
                ans = Math.max(ans,pages);
            }
        }
        else{
            visited[cnt] = true;
            dfs(cnt + 1);
            visited[cnt] = false;
            dfs(cnt + 1);
        }
    }
}
