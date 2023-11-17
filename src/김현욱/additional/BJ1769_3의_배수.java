package 김현욱.additional;

import java.io.*;

public class BJ1769_3의_배수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        long result = 0;
        long count = 0;
        while (true) {
            result = 0;
            if (str.length() == 1) {
                break;
            }
            char[] array = str.toCharArray();
            for (int i = 0; i < array.length; i++) {
                result += (int) (array[i] - '0');
            }
            str = String.valueOf(result);
            count++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(count).append('\n').append(Integer.parseInt(String.valueOf(str)) % 3 == 0 ? "YES" : "NO");
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
