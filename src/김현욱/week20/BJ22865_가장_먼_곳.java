package 김현욱.week20;

import java.io.*;
import java.util.*;

public class BJ22865_가장_먼_곳 {
    static ArrayList<Node>[] graph;
    static StringTokenizer st;
    static long[][] dist;


    static class Node{
        int to;
        long cost;

        public Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void dijkstra(int index,int start){
        long[] d = dist[index];
        Arrays.fill(d,Long.MAX_VALUE);
        PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Long.compare(o1.cost,o2.cost);
            }
        });
        d[start] = 0;
        q.offer(new Node(start,0l));

        while(!q.isEmpty()){
            Node node = q.poll();
            int num = node.to;
            long cost = node.cost;

            if(d[num] != cost){
                continue;
            }

            for(Node nextNode : graph[num]){
                int next = nextNode.to;
                long nextCost = nextNode.cost;

                if(d[next] > d[num]+nextCost){
                    d[next] = d[num]+nextCost;
                    q.offer(new Node(next,d[next]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        dist = new long[3][n+1];
        graph = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        int[] friends = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                                .toArray();

        int m = Integer.parseInt(br.readLine());
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v,cost));
            graph[v].add(new Node(u,cost));
        }

        for(int i=0;i<friends.length;i++){
            dijkstra(i,friends[i]);
        }

        long minLen = -1;
        int minIndex = 0;
        for(int i=1;i<=n;i++){
            long mDist = Long.MAX_VALUE;
            for(int j=0;j<3;j++){
                mDist = Math.min(mDist,dist[j][i]);
            }

            if(minLen < mDist){
                minIndex = i;
                minLen = mDist;
            }
        }

        bw.write(Integer.toString(minIndex));
        br.close();
        bw.close();
    }
}
