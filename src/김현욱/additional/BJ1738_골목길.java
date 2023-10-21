package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ1738_골목길 {
    static int n,m;
    static ArrayList<Edge> edges = new ArrayList<>();
    static final int INF = 987654321;
    static int[] trace;
    static class Edge{
        int u,v,cost;

        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        trace = new int[n+1];

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u,v,cost));
        }

        int[] dist = new int[n+1];
        Arrays.fill(dist,-INF);
        Arrays.fill(trace,-1);

        dist[1] = 0;

        for(int i=0;i<n-1;i++){
            for(Edge edge:edges){
                int u = edge.u;
                int v = edge.v;
                int cost = edge.cost;

                if(dist[u] != -INF && dist[u] + cost > dist[v]){
                    dist[v] = dist[u]+cost;
                    trace[v] = u;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if(dist[n] == -INF){
            sb.append(-1);
        }
        else{
            boolean flag = true;
            for(Edge edge : edges){
                int u = edge.u;
                int v = edge.v;
                int cost = edge.cost;
                if(dist[u] == -INF) continue;

                if(isCycle(dist,u,v,cost) && isPathCycle(v)){
                    sb.append(-1);
                    flag=false;
                    break;
                }
            }

            if(flag){
                int ind = n;
                sb.append(ind);
                while(trace[ind] != -1){
                    sb.insert(0,trace[ind]+" ");
                    ind = trace[ind];
                }
            }
        }
        bw.write(sb.toString());

        br.close();
        bw.close();
    }

    public static boolean isCycle(int[] dist,int u,int v,int cost){
        return dist[v] < dist[u]+cost;
    }

    public static boolean isPathCycle(int target){
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(target);
        visited[target] = true;
        while(!q.isEmpty()){
            int now = q.poll();

            for(Edge edge : edges){
                int u = edge.u;
                int v = edge.v;

                if(u == now && !visited[v]){
                    q.offer(v);
                    visited[v] = true;
                }
            }
        }

        return visited[n];
    }
}
