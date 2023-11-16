package 김현욱.additional;

import java.io.*;
import java.util.StringTokenizer;

public class BJ2042_구간_합_구하기_펜윅트리 {
    static StringTokenizer st;

    static class Fenwick {
        int size;
        long[] array;

        public Fenwick(int size) {
            this.size = size;
            array = new long[this.size + 1];
        }

        public void update(int index, long gap) {
            while (index <= size) {
                this.array[index] += gap;
                index += (index & -index);
            }
        }

        public long sum(int start, int end) {
            long result = 0;
            while(end > 0){
                result += this.array[end];
                end -= (end&-end);
            }
            start--;
            while(start > 0){
                result -= this.array[start];
                start -= (start&-start);
            }
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Fenwick fenwick = new Fenwick(n);
        long[] array = new long[n+1];
        for(int i=1;i<=n;i++){
            long value = Long.parseLong(br.readLine());
            fenwick.update(i,value);
            array[i] = value;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m+k;i++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            if(op == 1){
                long b = Long.parseLong(st.nextToken());
                fenwick.update(a,b-array[a]);
                array[a] = b;
            }
            else{
                int b = Integer.parseInt(st.nextToken());
                sb.append(fenwick.sum(a,b)).append('\n');
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
