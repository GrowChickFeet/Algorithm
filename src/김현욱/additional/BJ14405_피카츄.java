package 김현욱.additional;

import java.io.*;

public class BJ14405_피카츄 {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] line = br.readLine().toCharArray();
        char[][] pikachu = new char[][]{{'p', 'i' }, {'k', 'a' }, {'c', 'h', 'u' }};
        int[] indexes = new int[3];
        boolean flag = true;
        for (int i = 0; i < line.length; i++) {
            int count = 0;
            for (int j = 0; j < 3; j++) {
                if (line[i] == pikachu[j][indexes[j]]) {
                    indexes[j] = (indexes[j] + 1) % pikachu[j].length;
                    count++;
                }
            }
            if (count == 0) {
                flag = false;
                break;
            }
            count = 0;
            for (int j = 0; j < 3; j++) {
                if (indexes[j] != 0) count++;
            }
            if (count > 1) {
                flag = false;
                break;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (indexes[i] != 0) {
                flag = false;
                break;
            }
        }

        bw.write(flag ? "YES" : "NO");
        br.close();
        bw.close();
    }
}
