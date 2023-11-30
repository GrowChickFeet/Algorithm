package 김현욱.week25;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1581_락스타_락동호 {
    static StringTokenizer st;
    static final int FF = 0;
    static final int FS = 1;
    static final int SF = 2;
    static final int SS = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] songs = Arrays
                .stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        boolean fStart = false;
        if (songs[FF] != 0 || songs[FS] != 0) {//F로 시작
            fStart = true;
        }
        int result = 0;

        if (fStart) {//f로 시작하는 경우
            result = songs[FF];//앞에 FF로 시작하는건 다 깔아놓고 시작 FF - FF - FF - FF - FF - .... - FS 로 갈 수 있게
            songs[FF] = 0;
            int maxPair = Math.min(songs[FS], songs[SF]);// FS - SF - FS - SF - FS - SF 로 이어질 수 있도록 짝의 개수를 구함
            result += maxPair * 2;// 짝 * 2개만큼 추가로 더해줌

            if (songs[FS] > 0) {// FS가 한개라도 있다면 FS 뒤에 SS 를 모두 이어붙여줌, FF - FF - FF - .... - FS - SS - SS - SS - .... - SF - FS - SF 패턴으로 나열되도록
                result += songs[SS];
            }
            if (songs[FS] - maxPair > 0) {//짝을 모두 찾은 뒤, FS가 한개이상이 남는다면 맨 뒤에 붙여줌
                result++;
            }
        } else {
            result = songs[SS];
            int maxPair = Math.min(songs[SF], songs[FS]);
            result += maxPair * 2;

            if (songs[SF] > 0) {
                result += songs[FF];
            }
            if (songs[SF] - maxPair > 0) {
                result++;
            }
        }

        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
