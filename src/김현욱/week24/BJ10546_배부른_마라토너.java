package 김현욱.week24;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BJ10546_배부른_마라토너 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Map<String,Integer> map = new HashMap<>();
        Set<String> remain = new HashSet<>();
        for(int i=0;i<n;i++){
            String name = br.readLine();
            map.put(name,map.getOrDefault(name,0)+1);
            remain.add(name);
        }

        for(int i=0;i<n-1;i++){
            String name = br.readLine();
            Integer count = map.get(name);
            map.put(name,count-1);
            if(count == 1){
                remain.remove(name);
            }
        }

        bw.write(remain.stream().findFirst().get());
        br.close();
        bw.close();
    }
}
