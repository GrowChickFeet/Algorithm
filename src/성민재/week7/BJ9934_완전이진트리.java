package 성민재.week7;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ9934_완전이진트리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = null;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer>[] floor;
    static int K, build[];
    public static void main(String[] args) throws IOException {

        K = Integer.parseInt(br.readLine());
        int cnt = (int) (Math.pow(2,K) - 1);

        build = new int[cnt];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cnt; i++) {
            build[i] = Integer.parseInt(st.nextToken());
        }

        floor = new ArrayList[K];
        for (int i = 0; i < K; i++) {
            floor[i] = new ArrayList<>();
        }
        solution(0, cnt-1, 0);

        for (int i = 0; i < K; i++) {
            for (int j : floor[i]) {
                sb.append(j).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void solution(int left, int right, int depth) {
        if(depth == K){
            return;
        }
        int mid = (left+right)/2;
        floor[depth].add(build[mid]);
        solution(left, mid-1, depth+1);
        solution(mid+1, right, depth+1);
    }
}
/*
왼쪽 -> 오른쪽 -> 가운데 inorder
 */
