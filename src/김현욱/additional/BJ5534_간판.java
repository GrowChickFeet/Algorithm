package 김현욱.additional;

import java.io.*;

public class BJ5534_간판 {
    public static boolean canMake(char[] origin, char[] target, int originIndex, int index, int lastIndex, int gap) {
        if (originIndex == origin.length) {
            return true;
        }
        if (index == target.length) {
            return false;
        }

        boolean result = false;
        if (origin[originIndex] == target[index]) {
            if (originIndex <= 1 || index - lastIndex == gap) {
                gap = index - lastIndex;
                result = result | canMake(origin, target, originIndex + 1, index + 1, index, gap);
            }
        }

        return result | canMake(origin, target, originIndex, index + 1, lastIndex, gap);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        char[] name = br.readLine().toCharArray();
        int result = 0;
        for (int i = 0; i < n; i++) {
            char[] old = br.readLine().toCharArray();

            if (canMake(name, old, 0, 0, 0, 0)) {
                result++;
            }
        }
        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}
