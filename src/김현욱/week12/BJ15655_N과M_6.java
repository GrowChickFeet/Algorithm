package 김현욱.week12;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ15655_N과M_6 {

    static int n,m;
    static int[] arr;
    static int[] select;
    static StringBuilder sb = new StringBuilder();

    public static void dfs(int index,int count){
        if(count == m){
            for(int i=0;i<select.length;i++){
                sb.append(select[i]).append(' ');
            }
            sb.append('\n');
            return;
        }
        if(index == n) return;
        select[count] = arr[index];
        dfs(index+1,count+1);
        dfs(index+1,count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        select = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        dfs(0,0);

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
