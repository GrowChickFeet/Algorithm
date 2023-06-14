package 성민재.week8;

import java.io.*;
import java.util.*;

public class BJ1141_접두사 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        String[] str = new String[N];

        for(int i=0;i<N;i++){
            str[i] = br.readLine();
        }

        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o2.length(),o1.length());
            }
        });

        HashSet<String> set = new HashSet<>();
        set.add(str[0]);

        for (int i = 1; i < N; i++) {
            String s1 = str[i];

            boolean flag = true;
            for(String s2 : set){
                if(s2.startsWith(s1)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                set.add(s1);
            }
        }
        System.out.println(set.size());
    }
}
