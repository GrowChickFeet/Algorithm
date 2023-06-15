package 성민재.week6;

import java.io.*;
import java.util.*;

public class BJ2571_색종이3 {
    static int N, map[][];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[101][101];

        for(int i = 1; i < 101; i++){
            Arrays.fill(map[i], -10001);
        }

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for(int dx = x ; dx < x+10; dx++) {
                for (int dy = y; dy < y+10; dy++) {
                    map[dx][dy] = 1;
                }
            }
        }

        int[][] sectorSum = new int[101][101];
        for (int i = 1; i < 101; i++) {
            for (int j = 1; j < 101; j++) {
                sectorSum[i][j] = sectorSum[i-1][j] + sectorSum[i][j-1] - sectorSum[i-1][j-1] + map[i][j];
            }
        }
        int res = 0;
        for (int i = 1; i < 101; i++) {
            for (int j = 1; j < 101; j++) {
                for (int k = i+1; k < 101; k++) {
                    for (int l = j+1; l < 101; l++) {
                        int area = sectorSum[k][l] - sectorSum[i-1][l] - sectorSum[k][j-1] + sectorSum[i-1][j-1];
                        if(area < 0)
                            break;
                        res = Math.max(area, res);
                    }
                }
            }
        }
        System.out.println(res);
    }
}
