package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ2912_백설공주와_난쟁이 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[][] op = new int[m][3];
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            op[i][0] = Integer.parseInt(st.nextToken());
            op[i][1] = Integer.parseInt(st.nextToken());
            op[i][2] = i;
        }

        Arrays.sort(op, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int v = (int)Math.sqrt(n);
                if(o1[0]/v == o2[0]/v){
                    return Integer.compare(o1[1],o2[1]);
                }
                return Integer.compare(o1[0]/v,o2[0]/v);
            }
        });

        int[] answer = new int[m];
        int[] kind = new int[c+1];
        int left = op[0][0];
        int right = op[0][1];
        for(int i=left;i<=right;i++){
            kind[arr[i]]++;
        }
        answer[op[0][2]] = getRet(c,kind,right,left);


        for(int i=1;i<m;i++){
            int start = op[i][0];
            int end = op[i][1];
            int startB = op[i-1][0];
            int endB = op[i-1][1];
            int idx = op[i][2];

            while(left<start){
                kind[arr[left++]]--;
            }
            while(left>start){
                kind[arr[--left]]++;
            }
            while(right < end){
                kind[arr[++right]]++;
            }
            while(right > end){
                kind[arr[right--]]--;
            }
            answer[idx] = getRet(c, kind, end, start);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m;i++){
            sb.append(answer[i] == -1 ? "no" : "yes ".concat(Integer.toString(answer[i]))).append('\n');
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }

    private static int getRet(int c, int[] kind, int end, int start) {
        int ret = -1;
        for(int k = 0; k<= c; k++){
            if(kind[k] > (end - start + 1)/2){
                ret = k;
                break;
            }
        }
        return ret;
    }
}
