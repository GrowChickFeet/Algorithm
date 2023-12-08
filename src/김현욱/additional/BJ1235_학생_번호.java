package 김현욱.additional;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class BJ1235_학생_번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] students = new String[n];
        for (int i = 0; i < n; i++) {
            students[i] = br.readLine();
        }

        int len = students[0].length();
        int k = 1;

        for (int i = len - 1; i >= 0; i--) {
            Set<String> sets = new HashSet<>();
            for (int j = 0; j < n; j++) {
                sets.add(students[j].substring(i, len));
            }

            if (sets.size() == n) {
                break;
            } else {
                k++;
            }
        }
        bw.write(Integer.toString(k));
        br.close();
        bw.close();
    }

}
