package 김현욱.additional;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ1236_성_지키기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] maze = new char[n][m];

        int result = n;
        for (int i = 0; i < n; i++) {
            maze[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 'X') {
                    result--;
                    break;
                }
            }
        }

        int result2 = m;
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (maze[i][j] == 'X') {
                    result2--;
                    break;
                }
            }
        }

        bw.write(Integer.toString(Math.max(result, result2)));
        br.close();
        bw.close();
    }
}
