package 김현욱.week15;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BJ1174_줄어드는_수 {
    public static List<Long> list = new ArrayList<>();
    public static void dfs(long num , int index){
        if(!list.contains(num)){
            list.add(num);
        }

        if(index >= 10){
            return;
        }

        dfs(num*10+(9-index),index+1);
        dfs(num,index+1);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        dfs(0,0);

        Collections.sort(list);

        bw.write(Long.toString(list.size() < n ? -1l : list.get(n-1)));
        br.close();
        bw.close();
    }
}
