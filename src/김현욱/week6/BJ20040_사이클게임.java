package 김현욱.week6;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ20040_사이클게임 {
    static int[] parent;//각 그룹의 우두머리를 저장할 배열
    static int find(int a){
        if(a==parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }

    static void union(int a,int b){
        a=find(a);
        b=find(b);

        parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for(int i=0;i<n;i++) parent[i]=i;

        int result = Integer.MAX_VALUE;

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine());
            int a= Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            a = find(a);
            b = find(b);

            if(a==b){
                result = Math.min(result,i);
            }
            else{
                union(a,b);
            }
        }

        bw.write(Integer.toString(result == Integer.MAX_VALUE ? 0 : result));
        br.close();
        bw.close();
    }
}
