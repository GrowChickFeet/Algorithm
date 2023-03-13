package 남현실.week1;

/*
https://swexpertacademy.com/main/solvingProblem/solvingProblem.do
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA15941_평행사변형 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.valueOf(br.readLine());
        int len;
        StringBuilder result = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            len = Integer.valueOf(br.readLine());
            result.append("#").append(tc).append(" ").append(len*len).append("\n");
        }
        System.out.println(result.toString());
    }
}
