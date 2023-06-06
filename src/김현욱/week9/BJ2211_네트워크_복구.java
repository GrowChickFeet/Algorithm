package 김현욱.week9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2211_네트워크_복구 {
    static class Node implements Comparable<Node>{
        int before,to,cost;

        public Node(int before, int to, int cost) {
            this.before = before;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost,o.cost);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] dist = new int[N+1];
        ArrayList<Node>[] graph = new ArrayList[N+1];
        for(int i=0;i<=N;i++){
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[A].add(new Node(-1,B,C));
            graph[B].add(new Node(-1,A,C));
        }

        int count = 0;

        List<int[]> edge = new ArrayList<>();

        dist[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(-1,1,0));
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int now = node.to;
            int cost = node.cost;
            int before = node.before;

            if(cost != dist[now]) continue;
            if(before!=-1){
                count++;
                edge.add(new int[]{before,now});
            }


            for(Node nextNode : graph[now]){
                int next = nextNode.to;
                int nextCost = nextNode.cost;

                if(dist[next] > dist[now]+nextCost){
                    dist[next] = dist[now]+nextCost;
                    pq.offer(new Node(now,next,dist[next]));
                }
            }
        }

        sb.append(count).append('\n');
        edge.forEach(arr->{
            sb.append(arr[0]).append(' ').append(arr[1]).append('\n');
        });

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
