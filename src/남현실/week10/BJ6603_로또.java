package 남현실.week10;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class BJ6603_로또 {
    static boolean[] isSelected;
    static int[] numbers;
    static int[] comb;
    static final int tgCnt = 6;
    static int len;
    static final StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String temp = br.readLine();
        String[] tempArr;

        while (!temp.equals("0")) {
            tempArr = temp.split(" ");
            isSelected = new boolean[50];

            len = Integer.parseInt(tempArr[0]);
            numbers = new int[len];

            int data;
            for (int i = 0; i < len; i++) {
                data = Integer.parseInt(tempArr[i + 1]);
                numbers[i] = (data);
            }
            Arrays.sort(numbers);

            comb = new int[tgCnt];

            solution(0, 0);
            result.append("\n");

            temp = br.readLine();
        }
        System.out.println(result);
    }


    public static void solution(int cnt, int idx) {
        if (cnt == tgCnt) {

            for (int cb : comb) {
                result.append(cb).append(" ");
            }
            result.append("\n");
            return;
        }

        for (int i = idx; i < len; i++) {
            if (isSelected[i]) {
                continue;
            }
            isSelected[i] = true;
            comb[cnt] = numbers[i];
            solution(cnt + 1, i);
            isSelected[i] = false;
        }
    }

}
