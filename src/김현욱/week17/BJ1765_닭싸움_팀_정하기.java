package 김현욱.week17;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ1765_닭싸움_팀_정하기 {
    static int N,M;
    static int[] groups;
    static int index=1;
    static ArrayList<Integer>[] enemies,friends;

    public static void makeEmemyToFriends(int root,int num,int depth,boolean[] visited){
        if(depth == 2){
            friends[root].add(num);
            return;
        }

        for(int next : enemies[num]){
            if(!visited[next]){
                visited[next] = true;
                makeEmemyToFriends(root,next,depth+1,visited);
            }
        }
    }

    public static void dfs(int num,int group){
        groups[num] = group;

        for(int next : friends[num]){
            if(groups[next] == 0){
                dfs(next,group);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        enemies = new ArrayList[N+1];
        friends = new ArrayList[N+1];
        groups = new int[N+1];
        for(int i=0;i<=N;i++){
            enemies[i] = new ArrayList<>();
            friends[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char r = st.nextToken().charAt(0);
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            if(r == 'E'){
                enemies[n1].add(n2);
                enemies[n2].add(n1);
            }
            else{
                friends[n1].add(n2);
                friends[n2].add(n1);

            }
        }

        for(int i=1;i<=N;i++){
            makeEmemyToFriends(i,i,0,new boolean[N+1]);
        }

        for(int i=1;i<=N;i++){
            if(groups[i] == 0){
                dfs(i,index++);
            }
        }

        bw.write(Integer.toString(index-1));
        br.close();
        bw.close();
    }
}
