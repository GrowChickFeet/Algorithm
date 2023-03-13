package 남현실.week2;

/*
https://www.acmicpc.net/problem/2910
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2910_빈도_정렬 {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, int[]> numInfo = new HashMap<>(); // num = [cnt, order]

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = temp[0]; // C이하의 숫자들로 이루어진 메세지 길이
        int C = temp[1]; // 숫자

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // 메세지

        int i = 0;
        int[] tempInfo;
        for (int n : arr) {
            if (numInfo.get(n) == null) {
                tempInfo = new int[2];
                tempInfo[0] = 1; // cnt
                tempInfo[1] = i; // order
                numInfo.put(n, tempInfo);
                i++;
                continue;
            }
            tempInfo = numInfo.get(n);
            tempInfo[0] += 1;
            numInfo.put(n, tempInfo);
        }


        List<Map.Entry<Integer, int[]>> entryList = new LinkedList<>(numInfo.entrySet());
        entryList.sort(new Comparator<Map.Entry<Integer, int[]>>() {
            @Override
            public int compare(Map.Entry<Integer, int[]> o1, Map.Entry<Integer, int[]> o2) {
                int[] info1 = o1.getValue(), info2 = o2.getValue();
                if (info1[0] < info2[0]) {
                    return 1;
                } else if (info1[0] == info2[0]) {
                    if (info1[1] > info2[1]) {
                        return 1;
                    } else if (info1[1] == info2[1]) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        });

        StringBuilder result = new StringBuilder();
        int num, cnt;
        for (Map.Entry<Integer, int[]> entry : entryList) {
            num = entry.getKey();
            cnt = entry.getValue()[0];
            for (int j = 0; j < cnt; j++) {
                result.append(num + " ");
            }
        }

        System.out.println(result.toString());

        br.close();
    }
}
