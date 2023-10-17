package 김현욱.week20;

import java.io.*;
import java.util.*;

public class BJ4386_별자리_만들기 {
    static StringTokenizer st;
    static int[] parent;
    static int find(int x){
        if(x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    static void union(int x,int y){
        x = find(x);
        y = find(y);

        if(x < y){
            parent[y] = x;
        }
        else{
            parent[x] = y;
        }
    }

    static class Point{
        double x,y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getDistance(Point point){
            return Math.sqrt(Math.pow(Math.abs(this.x-point.x),2) +
                    Math.pow(Math.abs(this.y-point.y),2));
        }
    }

    static class Node{
        int u,v;
        double dist;

        public Node(int u, int v, double dist) {
            this.u = u;
            this.v = v;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Point[] stars = new Point[n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars[i] = new Point(x,y);
        }

        List<Node> edges = new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                edges.add(new Node(i,j,stars[i].getDistance(stars[j])));
            }
        }

        Collections.sort(edges, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Double.compare(o1.dist,o2.dist);
            }
        });

        parent = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
        }

        double result = 0;
        for(Node node : edges){
            int u = node.u;
            int v = node.v;
            double dist = node.dist;

            if(find(u) != find(v)){
                union(u,v);
                result += dist;
            }
        }
        bw.write(Double.toString(result));
        br.close();
        bw.close();
    }
}
