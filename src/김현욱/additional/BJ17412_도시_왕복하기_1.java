package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ17412_도시_왕복하기_1 {
    static int N,M;
    static int[][] c;
    static int[][] f ;
    static ArrayList<Integer>[] graph;
    static int source = 1;
    static int sink = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        c = new int[N+1][N+1];
        f = new int[N+1][N+1];
        graph = new ArrayList[N+1];
        for(int i=0;i<=N;i++){
            graph[i] = new ArrayList<>();
        }


        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
            c[u][v] = 1;
        }

        long result = 0;
        while(true){
            int[] prev = new int[N+1];
            Arrays.fill(prev,-1);
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(source);
            prev[source] = source;

            while(!q.isEmpty() && prev[sink] == -1){
                int cur = q.poll();

                for(int next : graph[cur]){
                    if(c[cur][next] - f[cur][next] > 0 && prev[next] == -1){
                        q.offer(next);
                        prev[next] = cur;
                    }
                }
            }
            //도달하지 못했으면 -1
            if(prev[sink] == -1){
                break;
            }

            int min = Integer.MAX_VALUE;
            for(int i=sink ; i!= source;i=prev[i]){
                min = Math.min(min,c[prev[i]][i] - f[prev[i]][i]);
            }

            for(int i=sink;i!=source;i=prev[i]){
                f[prev[i]][i] += min;
                f[i][prev[i]] -= min;
            }

            result+=min;
        }
        bw.write(Long.toString(result));
        br.close();
        bw.close();
    }
}
