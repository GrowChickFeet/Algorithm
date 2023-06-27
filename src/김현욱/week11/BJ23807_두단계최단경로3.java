package 김현욱.week11;

import java.io.*;
import java.util.*;

public class BJ23807_두단계최단경로3 {
    static class Node{
        int to;
        long cost;

        public Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    static ArrayList<Node>[] graph;
    static int[] points;
    static int[] select = new int[3];
    static int x,z,n,m;
    static Map<Integer,Map<Integer,Long>> dp = new HashMap<>();
    static long result = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v,cost));
            graph[v].add(new Node(u,cost));
        }

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        z = Integer.parseInt(st.nextToken());

        int p = Integer.parseInt(br.readLine());
        points = new int[p];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<p;i++){
            points[i] = Integer.parseInt(st.nextToken());
        }

        dp.put(x,getDistance(x));
        for(int i=0;i<p;i++){
           dp.put(points[i],getDistance(points[i]));
        }

        make();
        bw.write(Long.toString(result == Long.MAX_VALUE ? -1 : result));
        br.close();
        bw.close();
    }

    private static Map<Integer,Long> getDistance(int from){
        long[] dist = new long[n+1];
        Arrays.fill(dist,Long.MAX_VALUE);

        dist[from] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Long.compare(o1.cost,o2.cost);
            }
        });

        pq.offer(new Node(from,0));

        while(!pq.isEmpty()){
            Node node = pq.poll();

            int num = node.to;
            long cost = node.cost;

            if(dist[num] != cost) continue;

            for(Node nextNode : graph[num]){
                int next = nextNode.to;
                long nextCost = nextNode.cost;

                if(dist[next] > dist[num]+nextCost){
                    dist[next] = dist[num]+nextCost;
                    pq.offer(new Node(next,dist[next]));
                }
            }
        }
        Map<Integer,Long> ret = new HashMap<>();
        for(int i=0;i<points.length;i++){
            if(dist[points[i]] != Long.MAX_VALUE) {
                ret.put(points[i], dist[points[i]]);
            }
        }
        if(dist[z] != Long.MAX_VALUE) ret.put(z,dist[z]);

        return ret;
    }

    private static void make() {
        for(int i=0;i<points.length;i++){
            for(int j=0;j<points.length;j++){
                for(int k=0;k<points.length;k++){
                    if(i==j || j==k || i==k) continue;

                    long da = dp.get(x).getOrDefault(points[i],-1l);
                    long db = dp.get(points[i]).getOrDefault(points[j],-1l);
                    long dc = dp.get(points[j]).getOrDefault(points[k],-1l);
                    long dd = dp.get(points[k]).getOrDefault(z,-1l);
                    if(da==-1 || db==-1 || dc==-1 || dd==-1) continue;

                    //값 갱신
                    result = Math.min(result,da+db+dc+dd);
                }
            }
        }
    }
}
