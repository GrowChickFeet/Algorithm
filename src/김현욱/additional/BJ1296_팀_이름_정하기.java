package 김현욱.additional;

import java.io.*;
import java.util.Arrays;

public class BJ1296_팀_이름_정하기 {
    public static int getIndex(char c) {
        int index = -1;
        switch (c) {
            case 'L':
                index = 0;
                break;
            case 'O':
                index = 1;
                break;
            case 'V':
                index = 2;
                break;
            case 'E':
                index = 3;
                break;
            default:
                index = -1;
                break;
        }
        return index;
    }

    public static int getPercentage(int[] counts) {
        int result = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                result *= (counts[i] + counts[j]);
            }
        }
        return result % 100;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int maxPercent = -1;
        String result = null;

        char[] yeondu = br.readLine().toCharArray();
        int[] count = new int[4];
        for (int i = 0; i < yeondu.length; i++) {
            int index = getIndex(yeondu[i]);
            if (index != -1) {
                count[index]++;
            }
        }
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int[] newCount = Arrays.copyOf(count, count.length);
            String name = br.readLine();
            char[] line = name.toCharArray();
            for (int j = 0; j < line.length; j++) {
                int index = getIndex(line[j]);
                if (index != -1) {
                    newCount[index]++;
                }
            }

            int percent = getPercentage(newCount);
            if (maxPercent < percent) {
                maxPercent = percent;
                result = name;
            } else if (maxPercent == percent && name.compareTo(result) < 0) {
                result = name;
            }
        }
        bw.write(result);
        br.close();
        bw.close();
    }
}
