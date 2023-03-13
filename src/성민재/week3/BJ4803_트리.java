package 성민재.week3;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ4803_트리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static boolean[] visited;
    static ArrayList<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        int cnt = 0;
        while (true) {
            cnt++;
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); //정점의 개수
            int E = Integer.parseInt(st.nextToken()); //간선의 개수

            if(V == 0 && E == 0) // 0,0 이 입력될 경우 끝
                break;

            visited = new boolean[V + 1]; //방문 확인 배열
            adjList = new ArrayList[V+1]; //인접리스트 생성
            for (int i = 0; i <= V; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()); //시작 정점
                int to = Integer.parseInt(st.nextToken()); //도착 정점
                adjList[from].add(to);
                adjList[to].add(from);
            }
            int ans = 0;
            for(int vertex = 1; vertex <= V; vertex++) { //정점 순서대로 진입
                if(!visited[vertex]) { //이미 방문한 정점이라면 bfs를 하지 않는다.
                    if(bfs(vertex)) //bfs에서 true가 나올경우 트리가 하나 찾아진거다.
                        ans++;
                }
            }
            if(ans == 0)
                sb.append("Case ").append(cnt).append(": ").append("No trees.").append("\n");
            else if(ans == 1)
                sb.append("Case ").append(cnt).append(": ").append("There is one tree.").append("\n");
            else
                sb.append("Case ").append(cnt).append(": ").append("A forest of ").append(ans).
                        append(" trees.").append("\n");
        }
        bw.write(sb.toString());
        bw.close();
    }
    public static boolean bfs(int ver){
        Queue<Node> q = new ArrayDeque<>();
        visited[ver] = true;
        q.add(new Node(ver,0));

        while (!q.isEmpty()){
            Node cur = q.poll();

            for(int num : adjList[cur.cur]){
                if(num == cur.prv) //현재 정점을 큐에 넣은 정점으로 다시 가지 않기 하기 위함.
                    continue;
                if(visited[num] ) //이미 방문했던 곳을 다시 방문한다면 싸이클이 생긴거다.
                    return false;
                q.add(new Node(num, cur.cur));
                visited[num] = true; //방문처리
            }
        }
        return true; //중간에 return된적이 없기 때문에 싸이클이 생기지 않은거다.
    }
    static class Node{
        int cur; //현재 정점
        int prv; //현재 정점을 큐에 넣게 한 정점

        public Node(int cur, int prv) {
            this.cur = cur;
            this.prv = prv;
        }
    }
}
