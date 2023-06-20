package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ1953_팀배분 {
    static ArrayList<Integer>[] graph;
    static int[] group;
    static ArrayList<Integer>[] groups = new ArrayList[2];

    static void dfs(int parent,int now){
        group[now] = (group[parent] == 0 ? 1 : 0);
        groups[group[now]].add(now);

        for(int next : graph[now]){
            if(group[next] == -1){
                dfs(now,next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=0;i<2;i++) groups[i] = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        group = new int[n+1];
        for(int i=0;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            for(int j=0;j<c;j++){
                int u = i+1;
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
                graph[v].add(u);
            }
        }
        Arrays.fill(group,-1);
        group[0] = 1;
        for(int i=1;i<=n;i++){
            if(group[i]==-1){
                dfs(0,i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<2;i++){
            sb.append(groups[i].size()).append('\n');
            Collections.sort(groups[i]);
            for(int num : groups[i]){
                sb.append(num).append(' ');
            }
            sb.append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
