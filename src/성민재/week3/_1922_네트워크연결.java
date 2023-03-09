package 성민재.week3;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1922_네트워크연결 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int[] top;
    static int com,line;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        com = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        line = Integer.parseInt(st.nextToken());

        Node[] edge = new Node[line]; //간선 리스트

        for (int i = 0; i < line; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken()); //시작 정점
            int t = Integer.parseInt(st.nextToken()); //도착 정점
            int w = Integer.parseInt(st.nextToken()); //가중치
            edge[i] = new Node(f, t, w);
        }
        Arrays.sort(edge); //간선리스트를 가중치에 따라서 정렬
        make(); //단위 집합 생성

        int ans = 0;
        for(Node n : edge){
            if(union(n.from,n.to)){ //부모가 같은지 확인후 합집합
                ans += n.weight;// 연결이 되었다면 가중치를 더해준다.
//                if(++cnt == line-1) //스패닝트리는 V-1개만큼 간선이 사용되었다면 완성된 것이다.
//                    break;
            }
        }
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
    }
    public static void make(){
        top = new int[com+1];
        for(int i = 1 ; i <= com; i++){
            top[i] = i;
        }
    }
    public static int find(int a){
        if(top[a] == a) return a;
        return top[a] = find(top[a]);
    }
    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;
        top[bRoot] = aRoot;
        return true;
    }
    static class Node implements Comparable<Node>{
        int from;
        int to;
        int weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
