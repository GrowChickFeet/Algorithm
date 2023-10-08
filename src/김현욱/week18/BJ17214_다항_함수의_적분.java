package 김현욱.week18;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ17214_다항_함수의_적분 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line,"+|-",true);

        List<String> strs = new ArrayList<>();

        while(st.hasMoreTokens()){
            strs.add(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        if(line.equals("0")){
            sb.append("W");
        }

        else {
            for (int i = 0; i < strs.size(); i++) {
                String str = strs.get(i);
                if (str.equals("+") || str.equals("-")) {
                    continue;
                }

                int ind = str.indexOf('x');

                char sign = (i == 0 ? '+' : strs.get(i - 1).charAt(0));
                int value = Integer.parseInt(str.substring(0, ind == -1 ? str.length() : ind));
                int square = ind == -1 ? 0 : str.length() - ind;

                if (!(sign == '+' && i == 0)) {
                    sb.append(sign);
                }
                if (value / (square + 1) != 1) {
                    sb.append(value / (square + 1));
                }
                sb.append("x".repeat(square + 1));
            }

            sb.append("+W");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
