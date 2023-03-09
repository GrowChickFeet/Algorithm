package 김현욱.week3;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ4803_트리 {
    public static boolean[] visited;
    public static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        for(int t =1 ;;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n==0 && m==0) break;

            graph = new ArrayList[n + 1];
            visited = new boolean[n + 1];
            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
                graph[v].add(u);
            }

            int trees = 0;
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    Queue<int[]> q = new LinkedList<>();
                    boolean isNotTree = false;
                    q.offer(new int[]{i,-1});

                    while(!q.isEmpty()){
                        int[] node = q.poll();
                        int now = node[0];
                        int parent = node[1];

                        for(int next : graph[now]){
                            if(next!=parent){
                                if(!visited[next]){
                                    q.offer(new int[]{next,now});
                                }
                                else{
                                    isNotTree=true;
                                    break;
                                }
                            }
                        }
                        if(isNotTree) break;
                    }

                    if(!isNotTree) trees++;
                }
            }
            sb.append("Case ").append(t).append(": ");
            if(trees == 0){
                sb.append("No trees.\n");
            }
            else if(trees == 1){
                sb.append("There is one tree.\n");
            }
            else{
                sb.append("A forest of ").append(trees).append(" trees.\n");
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
