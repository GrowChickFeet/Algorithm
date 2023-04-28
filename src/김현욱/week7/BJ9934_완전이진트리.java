package 김현욱.week7;

import java.io.*;
import java.util.StringTokenizer;

public class BJ9934_완전이진트리 {
    public static StringBuilder[] sb;
    public static int k,n,h;

    public static void inorderTraversal(int[] arr,int left,int right,int level){
        if(left>right) return;
        int middle = (left+right)/2;
        sb[level].append(arr[middle]).append(' ');

        inorderTraversal(arr,left,middle-1,level+1);
        inorderTraversal(arr,middle+1,right,level+1);
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());
        n = (int)Math.pow(2,k) - 1;
        h = (int)Math.ceil(Math.log(n)/Math.log(2));
        sb = new StringBuilder[h];
        for(int i=0;i<h;i++){
            sb[i] = new StringBuilder();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n+1];
        for(int i=1;i<=n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //중위순회 시작
        inorderTraversal(arr,1,n,0);

        for(int i=0;i<h;i++){
            bw.write(sb[i].toString().concat("\n"));
        }

        br.close();
        bw.close();
    }
}
