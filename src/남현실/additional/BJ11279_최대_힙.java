package 남현실.additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ11279_최대_힙 {
    static int[] arr = new int[100_000];
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                result.append(pop()).append("\n");
            } else {
                push(num);
            }
        }
        System.out.println(result);
    }

    static int pop() {
        if (size == 0) {
            return 0;
        }
        int result = arr[1];

        arr[1] = arr[size]; // 맨 뒤에 것 맨 앞으로 옮기기
        arr[size] = 0; // 맨 뒤 초기화
        size -= 1;

        int pIdx = 1;
        while (true) {
            int cIdx = pIdx * 2;

            if (cIdx + 1 <= size && arr[cIdx] < arr[cIdx + 1]) {
                cIdx += 1;
            }
            if (cIdx > size) {
                break;
            }

            if (arr[pIdx] < arr[cIdx]) {
                int temp = arr[pIdx];
                arr[pIdx] = arr[cIdx];
                arr[cIdx] = temp;
                pIdx = cIdx;
            } else {
                break;
            }
        }

        return result;
    }

    static void push(int num) {
        size += 1;
        arr[size] = num;

        int idx = size;
        while (idx != 1) {
            int pIdx = idx / 2;
            if (arr[pIdx] >= num) {
                break;
            }
            arr[idx] = arr[pIdx];
            arr[pIdx] = num;
            idx = pIdx;
        }
    }
}
