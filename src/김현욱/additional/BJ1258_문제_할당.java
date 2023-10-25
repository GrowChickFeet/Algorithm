package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ1258_문제_할당 {
    static StringTokenizer st;
    static int N;
    static final int BIAS = 110;
    static final int INF = Integer.MAX_VALUE;
    static int MAX_V = BIAS*2+10;
    static int source = MAX_V - 2;
    static int sink = MAX_V - 1;
    static ArrayList<Integer>[] graph;
    static int[][] c = new int[MAX_V][MAX_V];//용량
    static int[][] f = new int[MAX_V][MAX_V];//유량
    static int[][] d = new int[MAX_V][MAX_V];//cost

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[MAX_V];
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }


        for(int i=0;i<N;i++){
            graph[source].add(i);
            graph[BIAS+i].add(sink);
            c[source][i] = 1;
            c[BIAS+i][sink] = 1;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = BIAS; j < BIAS + N; j++) {
                graph[i].add(j);
                graph[j].add(i);
                d[i][j] = Integer.parseInt(st.nextToken());
                d[j][i] = -d[i][j];
                c[i][j] = 1;
            }
        }
        int flow = 0;
        while(true){
            int[] prev,dist;
            boolean[] inQ = new boolean[MAX_V];
            prev = new int[MAX_V];
            dist = new int[MAX_V];
            Arrays.fill(prev,-1);
            Arrays.fill(dist,INF);

            Queue<Integer> q = new ArrayDeque<>();
            q.offer(source);
            dist[source] = 0;
            inQ[source] = true;

            while(!q.isEmpty()){
                int cur = q.poll();
                inQ[cur] = false;

                for(int next : graph[cur]){
                    if(c[cur][next] - f[cur][next] > 0
                    && dist[next] > dist[cur] + d[cur][next]){
                        dist[next] = dist[cur] + d[cur][next];
                        prev[next] = cur;
                        if(!inQ[next]) {
                            inQ[next] = true;
                            q.offer(next);
                        }
                    }
                }
            }

            if(prev[sink] == -1) {
                break;
            }

            int minFlow = Integer.MAX_VALUE;
            for(int i=sink;i!=source;i = prev[i]){
                minFlow = Math.min(minFlow,c[prev[i]][i] - f[prev[i]][i]);
            }

            for(int i=sink;i!=source;i = prev[i]){
                flow += d[prev[i]][i];
                f[prev[i]][i]+=minFlow;
                f[i][prev[i]]-=minFlow;
            }
        }

        bw.write(Integer.toString(flow));
        br.close();
        bw.close();
    }
}
