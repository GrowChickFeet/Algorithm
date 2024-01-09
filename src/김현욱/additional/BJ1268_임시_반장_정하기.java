package 김현욱.additional;

import java.io.*;
import java.util.StringTokenizer;

public class BJ1268_임시_반장_정하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] students = new int[n][5];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                students[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxCount = -1;
        int cm = -1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                boolean flag = false;
                for (int k = 0; k < 5; k++) {
                    if (students[i][k] == students[j][k]) {
                        flag = true;
                        break;
                    }
                }

                if (flag) count++;
            }

            if (maxCount < count) {
                maxCount = count;
                cm = i;
            }
        }
        bw.write(Integer.toString(cm + 1));
        br.close();
        bw.close();
    }
}
