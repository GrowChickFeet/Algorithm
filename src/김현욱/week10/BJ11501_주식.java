package 김현욱.week10;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BJ11501_주식 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for(int tt = 0;tt<t;tt++) {
            int  n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            int[] visited = new int[1000001];
            long result=0;
            PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o2,o1);
                }
            });
            Stack<Integer> stack = new Stack<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                arr[i] = Integer.parseInt(st.nextToken());
                pq.offer(arr[i]);
                visited[arr[i]]++;
            }

            for(int i=0;i<n;i++){
                visited[arr[i]]--;
                if( arr[i] == pq.peek()){
                    int high = pq.poll();
                     //전부 매각
                    while(!stack.isEmpty()){
                        result += (high-stack.pop());
                    }
                }
                else{
                    stack.push(arr[i]);
                }

                while(!pq.isEmpty() && visited[pq.peek()] == 0){
                    pq.poll();
                }
            }

            sb.append(result).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
