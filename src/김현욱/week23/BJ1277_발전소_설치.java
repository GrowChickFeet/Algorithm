package 김현욱.week23;

import java.io.*;
import java.util.*;

public class BJ1277_발전소_설치 {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getDist(Point target) {
            return getDist(this, target);
        }

        public static double getDist(Point u, Point v) {
            return Math.sqrt(Math.pow(u.x - v.x, 2) + Math.pow(u.y - v.y, 2));
        }
    }

    static class Node {
        int to;
        double dist;

        public Node(Point[] points, int u, int v) {
            this.to = v;
            this.dist = points[u].getDist(points[v]);
        }

        public Node(int to, double dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        double m = Double.parseDouble(br.readLine());

        Point[] points = new Point[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        boolean[][] already = new boolean[n + 1][n + 1];

        ArrayList<Node>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= w; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            already[u][v] = already[v][u] = true;
            graph[u].add(new Node(v, 0));
            graph[v].add(new Node(u, 0));
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i==j || already[i][j] || already[j][i]) continue;

                int u = i;
                int v = j;

                double d = Point.getDist(points[u], points[v]);
                if(d<=m){
                    graph[u].add(new Node(v,d));
                    graph[v].add(new Node(u,d));
                }
            }
        }

        double[] dist = new double[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Double.compare(o1.dist,o2.dist);
            }
        });
        Arrays.fill(dist, Double.MAX_VALUE);

        dist[1] = 0;
        pq.offer(new Node(1,0));

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int cur = node.to;
            double d = node.dist;
            if(dist[cur] != d) continue;

            if(cur == n+1){
                break;
            }

            for(Node nextNode : graph[cur]){
                int next = nextNode.to;
                double cost = nextNode.dist;

                if(dist[next] > dist[cur]+cost){
                    dist[next] = dist[cur]+cost;
                    pq.offer(new Node(next,dist[next]));
                }
            }
        }
        bw.write(Long.toString((long)(dist[n]*1000)));
        br.close();
        bw.close();
    }
}
