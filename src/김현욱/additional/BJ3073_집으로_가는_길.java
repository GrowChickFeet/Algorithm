package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ3073_집으로_가는_길 {
    static StringTokenizer st;
    static int BIAS = 110;
    static int source = 0;
    static int sink = BIAS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while(true) {
            int result = 0;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0){
                break;
            }

            char[][] maze = new char[n][];
            List<Integer> home = new ArrayList<>();
            List<Integer> children = new ArrayList<>();
            List<Integer>[] graph = new ArrayList[BIAS*2+10];

            for(int i=0;i<graph.length;i++){
                graph[i] = new ArrayList<>();
            }
            int[][] cap = new int[BIAS*2][BIAS*2];
            int[][] flow = new int[BIAS*2][BIAS*2];
            int[][] d = new int[BIAS*2][BIAS*2];

            for(int i=0;i<n;i++){
                maze[i] = br.readLine().toCharArray();
                for(int j=0;j<m;j++){
                    if(maze[i][j] == 'H'){
                        home.add(i*m+j);
                        cap[source][home.size()] = 1;
                        graph[source].add(home.size());
                        graph[home.size()].add(source);

                    }
                    else if(maze[i][j] == 'm'){
                        children.add(i*m+j);
                        cap[BIAS+children.size()][sink] = 1;
                        graph[BIAS+children.size()].add(sink);
                        graph[sink].add(BIAS+children.size());
                    }
                }
            }


            for(int i=0;i<home.size();i++){
                for(int j=0;j<children.size();j++){
                    int h = home.get(i);
                    int c = children.get(j);

                    int hx = h/m;
                    int hy = h%m;

                    int cx = c/m;
                    int cy = c%m;

                    int dist = Math.abs(hx-cx) + Math.abs(hy-cy);
                    graph[i+1].add(BIAS+j+1);
                    graph[BIAS+j+1].add(i+1);
                    cap[i+1][BIAS+j+1] = 1;

                    d[i+1][BIAS+j+1] = dist;
                    d[BIAS+j+1][i+1] = -dist;
                }
            }
            while(true){
                int[] prev,dist;
                prev = new int[BIAS*2];
                dist = new int[BIAS*2];
                Arrays.fill(prev,-1);
                Arrays.fill(dist,Integer.MAX_VALUE);

                Queue<Integer> q = new ArrayDeque<>();
                boolean[] inQ = new boolean[BIAS*2];
                dist[source] = 0;
                inQ[source] = true;
                q.offer(source);

                while(!q.isEmpty()){
                    int cur = q.poll();

                    inQ[cur] = false;

                    for(int next : graph[cur]){
                        if(cap[cur][next] - flow[cur][next] > 0
                        && dist[next] > dist[cur]+d[cur][next]){
                            dist[next] = dist[cur] + d[cur][next];
                            inQ[next] = true;
                            prev[next] = cur;
                            q.offer(next);
                        }
                    }
                }

                if(prev[sink] == -1) break;

                int minFlow = Integer.MAX_VALUE;
                for(int i=sink;i!=source;i = prev[i]){
                    minFlow = Math.min(minFlow,cap[prev[i]][i] - flow[prev[i]][i]);
                }
                for(int i=sink;i!=source;i = prev[i]){
                    result += d[prev[i]][i];
                    flow[prev[i]][i] += minFlow;
                    flow[i][prev[i]] -= minFlow;
                }
            }
            sb.append(result).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
