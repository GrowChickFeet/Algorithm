package 성민재.week4;

import java.io.*;
import java.util.StringTokenizer;

public class BJ2805_나무자르기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n,m, tree[],ans, max;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 나무의 수
        m = Integer.parseInt(st.nextToken()); // 가져가야하는 나무의 길이

        tree = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            max = Math.max(tree[i], max); // 가장 높이가 높은 나무
        }
        int bottom = 0; // 가장 낮은 높이
        int high = max; // 가장 높은 높이
        while(high > bottom){ //이진 탐색
            int cut = (high + bottom) / 2; //나무들의 중간값

            long sum = 0; // 범위가 초과할지도 모르기 때문에 long
            for(int tmp : tree) {
                if(tmp - cut > 0) // 기준 높이보다 높은 나무만 cut한다.
                    sum += tmp - cut; // 자른 나무의 높이들을 더해준다.
            }
            if(sum >= m){ //자른 나무의 높이가 가져가려는 나무보다 높다면 높이를 더 높게 조정해준다.
                bottom = cut + 1;
            }
            else{ // 자른 나무의 높이가 가져가려는 나무보다 낮다면 높이를 낮게 조정.
                high = cut;
            }
        }
        sb.append(bottom - 1);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}

