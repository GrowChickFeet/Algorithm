package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ11406_책_구매하기_2 {
    static int MAX_V = 300;
    static int BIAS = 110;
    static int source = MAX_V - 1;
    static int sink = MAX_V - 2;
    static int[][] c = new int[MAX_V][MAX_V];
    static int[][] f = new int[MAX_V][MAX_V];
    static int[][] d = new int[MAX_V][MAX_V];

    static ArrayList<Integer>[] graph = new ArrayList[MAX_V];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        //사람이니까 sink랑 연결
        for (int i = BIAS; i < BIAS + n; i++) {
            int value = Integer.parseInt(st.nextToken());
            graph[i].add(sink);
            graph[sink].add(i);

            c[i][sink] = value;
        }
        st = new StringTokenizer(br.readLine());
        //서점이니 source랑 연결
        for (int i = 0; i < m; i++) {
            int value = Integer.parseInt(st.nextToken());
            graph[i].add(source);
            graph[source].add(i);

            c[source][i] = value;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = BIAS; j < BIAS + n; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value != 0) {
                    c[i][j] = value;
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        int result = 0;
        while (true) {
            int[] prev = new int[MAX_V];

            Arrays.fill(prev, -1);

            Queue<Integer> q = new ArrayDeque<>();
            q.offer(source);
            boolean canUpdate = false;
            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int next : graph[cur]) {
                    if (c[cur][next] - f[cur][next] > 0
                            && prev[next] == -1) {
                        prev[next] = cur;
                        q.offer(next);
                        if(next == sink){
                            canUpdate = true;
                        }
                    }
                }
            }

            if(!canUpdate) break;

            int minFlow = Integer.MAX_VALUE;
            for(int i=sink;i!=source;i=prev[i]){
                minFlow = Math.min(minFlow,c[prev[i]][i] - f[prev[i]][i]);
            }

            for(int i=sink ; i != source ; i = prev[i]){
                f[prev[i]][i]+=minFlow;
                f[i][prev[i]]-=minFlow;
            }
            result+=minFlow;
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
