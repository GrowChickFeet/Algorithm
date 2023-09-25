package 김현욱.week17;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BJ3089_네잎_클로버를_찾아서 {
    static final int MAX_ARRANGE = 99_999;
    static final int MIN_ARRANGE = -99_999;
    static final int BIAS = 99_999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TreeSet<Integer>[] xList = new TreeSet[MAX_ARRANGE - MIN_ARRANGE + 1];
        TreeSet<Integer>[] yList = new TreeSet[MAX_ARRANGE - MIN_ARRANGE + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) + BIAS;
            int y = Integer.parseInt(st.nextToken()) + BIAS;
            if(xList[y] == null) xList[y] = new TreeSet<>();
            if(yList[x] == null) yList[x] = new TreeSet<>();

            xList[y].add(x);
            yList[x].add(y);
        }

        int nowX = BIAS;
        int nowY = BIAS;

        char[] op = br.readLine().toCharArray();
        for(int i=0;i<m;i++){
            char c = op[i];

            switch(c){
                case 'R':
                    nowX = xList[nowY].higher(nowX);
                    break;
                case 'L':
                    nowX = xList[nowY].lower(nowX);
                    break;
                case 'U':
                    nowY = yList[nowX].higher(nowY);
                    break;
                case 'D':
                    nowY = yList[nowX].lower(nowY);
                    break;
                default:
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(nowX - BIAS).append(' ').append(nowY - BIAS);
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}