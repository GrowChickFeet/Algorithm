package 성민재.week8;

import java.io.*;
import java.util.*;

public class BJ1141_접두사 {
    static int N;
    static String[] input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        input = new String[N];

        ArrayList<String> list = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            list.add(br.readLine());
        }

        Collections.sort(list, (a,b) -> Integer.compare(b.length(),a.length()));

        HashSet<String> set = new HashSet<>();
        set.add(list.get(0));

        for (int i = 1; i < N; i++) {
            String s1 = list.get(i);

            boolean flag = true;
            for(String s2 : set){
                if(s2.startsWith(s1)){
                    flag = false;
                }
            }
            if(flag){
                set.add(s1);
            }
        }
        System.out.println(set.size());
    }
}
