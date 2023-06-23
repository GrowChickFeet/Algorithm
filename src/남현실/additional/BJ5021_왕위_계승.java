package 남현실.additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ5021_왕위_계승 {

    static HashMap<String, String[]> family;
    static HashMap<String, Double> info;
    static String root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        root = br.readLine();
        family = new HashMap<>();
        info = new HashMap<>();

        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            String child = temp[0];
            String parentA = temp[1];
            String parentB = temp[2];

            family.put(child, new String[] {parentA, parentB});
            info.put(child, -1.0);
            info.put(parentA, -1.0);
            info.put(parentB, -1.0);
        }

        // init
        info.put(root, 1.0);

        String cur, prev;
        cur = br.readLine();
        dfs(cur);
        prev = cur;
        for (int i = 1; i < M; i++) {
            cur = br.readLine();
            if(!info.containsKey(cur)) {
                continue;
            }
            dfs(cur);
            if(info.get(prev) < info.get(cur)) {
                prev = cur;
            }
        }
        System.out.println(prev);
    }

    static public void dfs(String name) {
        if(info.get(name) != -1) {
            return;
        }
        if(family.get(name) == null && name != root) {
            info.put(name, 0.0);
            return;
        }
        String parentA = family.get(name)[0];
        String parentB = family.get(name)[1];

        dfs(parentA);
        dfs(parentB);

        info.put(name, (info.get(parentA) + info.get(parentB))/2);
    }
}
