package 성민재.week6;

import java.io.*;
import java.util.*;

public class BJ20040_사이클게임 {
    static int[] Root;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        makeSet();

        int res = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(!union(start, end)){
                res = i+1;
                break;
            }
        }
        System.out.println(res);
    }

    private static boolean union(int start, int end) {
        int aRoot = findSet(start);
        int bRoot = findSet(end);
        if(aRoot == bRoot) return false;

        Root[bRoot] = aRoot;
        return true;
    }

    private static int findSet(int a) {
        if(a == Root[a]) return a;
        return Root[a] = findSet(Root[a]);
    }

    private static void makeSet() {
        Root = new int[N];
        for(int i = 0 ; i < N; i++){
            Root[i] = i;
        }
    }
}