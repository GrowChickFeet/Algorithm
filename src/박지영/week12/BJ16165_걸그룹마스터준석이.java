package 박지영.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ16165_걸그룹마스터준석이 {
    static int N, M;
    static HashMap<String, String> hash;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        hash = new HashMap<>();
        for (int n = 0; n < N; n++) {
            String group = br.readLine();
            int memberCnt = Integer.parseInt(br.readLine());
            for (int m = 0; m < memberCnt; m++) {
                hash.put(br.readLine(), group);
            }
        }

        // 퀴즈 시작
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            int n = Integer.parseInt(br.readLine());
            if (n == 0) findMember(s);
            else sb.append(hash.get(s)).append("\n");
        }

        System.out.print(sb.toString());
    }

    static void findMember(String group) {
        ArrayList<String> member = new ArrayList<>();
        for(String key: hash.keySet()) {
            if (hash.get(key).equals(group)) member.add(key);
        }

        sortMember(member);
    }

    static void sortMember(ArrayList<String> member) {

        Collections.sort(member);
        for(String m : member) {
            sb.append(m).append("\n");
        }
    }
}
