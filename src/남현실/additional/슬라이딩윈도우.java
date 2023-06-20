package 남현실.additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
4 14
1 2 4 3 4 3 2 1 2 3 4 1 3 4
NNNY
 */
public class 슬라이딩윈도우 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);

        temp = br.readLine().split(" ");
        int[] arr = new int[K + N];
        String[] result = new String[N];
        Arrays.fill(result, "Y");
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(temp[i]) - 1;
        }
        for (int i = K; i < K + N - 1; i++) {
            arr[i] = -1;
        }

        int group = 0;
        int[] count = new int[N];
        int over = 0;
        for (int i = 0; i < K + N - 1; i++) {
            group = i % N;
            if (result[group].equals("N")) {
                continue;
            }
            if (arr[i] >= 0) {
                count[arr[i]] += 1;
                if (count[arr[i]] == 2) { // 정상 범위 넘어감
                    over += 1;
                }
            }

            if (i >= N) {
                count[arr[i - N]] -= 1;
                if (count[arr[i - N]] == 1) { // 정상 범위로 돌아옴
                    over -= 1;
                }
            }

            if (over >= 1) {
                result[group] = "N";
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String r : result) {
            sb.append(r);
        }
        System.out.println(sb.toString());
    }
}
