package 김현욱.week8;

import java.io.*;

public class BJ1213_팰린드롬_만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] arr = br.readLine().toCharArray();
        int[] count = new int['Z' - 'A' + 1];
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            count[c - 'A']++;
        }

        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();

        for (int i = 0; i < count.length; i++) {
            int size = count[i] / 2;

            if (size >= 1) {
                for (int j = 0; j < size; j++) {
                    char ch = (char)('A'+i);
                    left.append(ch);
                    right.append(ch);
                }
                count[i] -= size*2;
            }
        }
        int remain = 0;
        for(int i=0;i<count.length;i++){
            if(count[i] != 0){
                char ch = (char)('A'+i);
                left.append(ch);
                remain++;
            }
        }
        bw.write(remain <= 1 ? left.append(right.reverse().toString()).toString() : "I'm Sorry Hansoo");
        bw.close();
        br.close();
    }
}
