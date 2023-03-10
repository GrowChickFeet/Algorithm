package 김현욱.week3;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1922_네트워크연결 {
    static int[] parent;
    static int find(int a){
        if(parent[a]==a) return a;
        else return parent[a] = find(parent[a]);
    }
    static void union(int a,int b){
        a = find(a);
        b = find(b);

        parent[b]=a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        parent = new int[n+1];
        for(int i=0;i<=n;i++) parent[i]=i;
        int m = Integer.parseInt(br.readLine());

        List<int[]> edges = new ArrayList<>();
        for(int i=0;i<m;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new int[]{w,u,v});
        }

        edges.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int result = 0;
        for(int[] edge : edges){
            int w = edge[0];
            int u = edge[1];
            int v = edge[2];

            if(find(u)!= find(v)){
                union(u,v);
                result+=w;
            }
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
