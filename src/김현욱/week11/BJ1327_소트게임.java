package 김현욱.week11;

import java.io.*;
import java.util.*;

public class BJ1327_소트게임 {
    static class Node{
        String value;
        int count;

        public Node(String value, int count) {
            this.value = value;
            this.count = count;
        }
    }
    public static String getSortString(String input,int i,int k){
        String sub = input.substring(i,i+k);
        String front = input.substring(0,i);
        String back = input.substring(i+k,input.length());
        StringBuilder subBuilder = new StringBuilder(sub);
        String value = front.concat(subBuilder.reverse().toString()).concat(back);

        return value;
    }

    public static boolean isAsc(String input){
        char[] arr = input.toCharArray();
        for(int i=1;i<arr.length;i++){
            if(arr[i-1]>arr[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Map<String, Boolean> visited = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        StringBuilder inputBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            inputBuilder.append(st.nextToken());
        }

        Queue<Node> q = new LinkedList<>();
        String input = inputBuilder.toString();
        int len = input.length();
        q.offer(new Node(input,0));
        visited.put(input,true);

        int ret = Integer.MAX_VALUE;
        while(!q.isEmpty()){
            Node node = q.poll();
            String now = node.value;
            int count = node.count;

            if(isAsc(now)){
                ret = Math.min(ret,count);
                continue;
            }

            for(int i=0;i<=len-k;i++){
                String value = getSortString(now,i,k);
                if(!visited.getOrDefault(value,false)){
                    visited.put(value,true);
                    q.offer(new Node(value,count+1));
                }
            }
        }

        bw.write(Integer.toString(ret == Integer.MAX_VALUE ? -1 : ret));
        br.close();
        bw.close();
    }
}
