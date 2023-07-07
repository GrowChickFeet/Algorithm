package 김서기.week12;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
public class B16165_걸그룹_마스터_준석이 {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String,String> members = new HashMap<>();
        Map<String, ArrayList<String>> teams = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String team = br.readLine();
            int K = Integer.parseInt(br.readLine());
            teams.putIfAbsent(team,new ArrayList<>());
            for (int j = 0; j < K; j++) {
                String member = br.readLine();
                teams.get(team).add(member);
                members.put(member,team);
            }
        }
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            int op = Integer.parseInt(br.readLine());
            if(op == 1){
                System.out.println(members.get(name));
            }
            else{
                teams.get(name).sort((s1,s2)->s1.compareTo(s2));
                for (int j = 0; j < teams.get(name).size(); j++) {
                    System.out.println(teams.get(name).get(j));
                }
            }
        }
    }
}
