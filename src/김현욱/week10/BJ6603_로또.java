package 김현욱.week10;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ6603_로또 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            if(n == 0) break;

            int[] numbers = new int[n];
            boolean[] checked = new boolean[n];
            for(int i=0;i<n;i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(numbers);

            makeCombination(sb,numbers,checked,0,0);
            sb.append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }

    private static void makeCombination(StringBuilder sb, int[] numbers,boolean[] checked, int index,int count) {
        if(count == 6){
            for(int i=0;i<numbers.length;i++){
                if(checked[i]) sb.append(numbers[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for(int i=index;i<numbers.length;i++){
            if(!checked[i]){
                checked[i] = true;
                makeCombination(sb,numbers,checked,i+1,count+1);
                checked[i] = false;
            }
        }
    }
}
