package 김현욱.week8;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
public class BJ1141_접두사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        String[] str = new String[n];

        for(int i=0;i<n;i++){
            str[i] = br.readLine();
        }

        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o2.length(),o1.length());
            }
        });

        Set<String> set = new HashSet<>();
        for(String s : str){
            if(set.isEmpty()){
                set.add(s);
                continue;
            }

            boolean available = true;
            for(String s2 : set){
                if(s2.indexOf(s) == 0){
                    available = false;
                    break;
                }
            }
            if(available){
                set.add(s);
            }
        }

        bw.write(Integer.toString(set.size()));
        br.close();
        bw.close();
    }
}
