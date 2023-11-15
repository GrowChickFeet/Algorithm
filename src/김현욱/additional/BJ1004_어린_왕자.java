package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1004_어린_왕자 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int round = 1;
        List<Integer>[] list = new ArrayList[2];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            list[1].add(i);
        }
        boolean match = false;
        while (!list[round%2].isEmpty() && !match) {
            for (int i = 0; i < list[round%2].size(); i += 2) {
                if (i + 1 >= list[round%2].size()) {//부전승
                    list[(round+1) % 2].add(list[round%2].get(i));
                } else {
                    int u = list[round%2].get(i);
                    int v = list[round%2].get(i+1);
                    int winner = u;
                    if(u == k && v == l || u == l && v == k){//만났다면
                        match = true;
                        break;
                    }
                    else if(u == k || u == l){
                        winner = u;
                    }
                    else if(v == k || v == l){
                        winner = v;
                    }
                    list[(round+1)%2].add(winner);
                }
            }
            list[(round++)%2].clear();
        }

        bw.write(Integer.toString(match? (round-1) : -1));
        br.close();
        bw.close();
    }
}
