package 김서기.week12;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;
public class B15655_N과_M {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int answer[];
    static ArrayList<Integer> arr;
    static Stack<int[]> sk = new Stack<>();
    static int N,M;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = new int[M];
        arr = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(arr);
        dfs(0,0);
        while(!sk.isEmpty()){
            int[] cur = sk.pop();
            for (int i = 0; i < M; i++) {
                System.out.print(cur[i]+" ");
            }
            System.out.println();
        }
    }
    static void dfs(int cur,int num){
        if(num == M){
            sk.add(answer.clone());
            return;
        }
        if(cur >= arr.size()){
            return;
        }
        dfs(cur+1,num);
        answer[num] = arr.get(cur);
        dfs(cur+1,num+1);
    }
}
