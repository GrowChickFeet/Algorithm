package 김현욱.week21;

import java.io.*;
import java.util.*;

public class BJ20303_할로윈_양아치 {
    static StringTokenizer st;
    static int N, K, M;
    static int[] parent;
    static int[] sum;
    static int[] count;
    static int find(int x) {
        if (x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        sum = new int[N+1];
        count = new int[N+1];
        for(int i=0;i<=N;i++){
            parent[i] = i;
            count[i] = 1;
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            sum[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u,v);
        }

        List<Node> arr = new ArrayList<>();
        for(int i=1;i<=N;i++){
            if(parent[i] != i){
                int p = find(i);
                sum[p] += sum[i];
                count[p] += count[i];
            }
        }
        for(int i=1;i<=N;i++){
            if(parent[i] == i){
                arr.add(new Node(count[i],sum[i]));
            }
        }

        Collections.sort(arr, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.count,o2.count);
            }
        });

        int[] dp = new int[K];
        for(int i=0;i<arr.size();i++){
            int c = arr.get(i).count;
            int v = arr.get(i).sum;

            for(int j=K-1;j>=c;j--){
                dp[j] = Math.max(dp[j],dp[j-c]+v);
            }
        }
        bw.write(Integer.toString(dp[K-1]));
        br.close();
        bw.close();
    }

    private static class Node{
        int count,sum;
        public Node(int count,int sum){
            this.count=count;
            this.sum=sum;
        }
    }
}
