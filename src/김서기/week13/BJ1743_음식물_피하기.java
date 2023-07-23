package 김서기.week13;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class BJ1743_음식물_피하기 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N,M,K;
    static boolean vst[][],mp[][];

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        mp = new boolean[N+1][M+1];
        vst = new boolean[N+1][M+1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            mp[y][x] = true;
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!vst[i][j] && mp[i][j]){
                    Queue<int[]> q = new ArrayDeque<>();
                    q.add(new int[]{i,j});
                    vst[i][j] = true;
                    int tmp = 1;
                    while(!q.isEmpty()){
                        int []cur = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int[] nx = new int[]{cur[0]+dy[k],cur[1]+dx[k]};
                            int y = nx[0];
                            int x = nx[1];
                            if(y < 0 || y >= N || x < 0 || x >= M) continue;
                            if(!vst[y][x] && mp[y][x]){
                                q.add(nx);
                                vst[y][x] = true;
                                tmp++;
                            }
                        }
                    }
                    answer = Math.max(answer,tmp);
                }
            }
        }
        System.out.println(answer);
    }
}
