package 남현실.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BJ16165_걸그룹_마스터_준석이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        Map<String, ArrayList<String>> info = new HashMap<>();
        String group;
        int num;
        for (int i = 0; i < N; i++) {
            ArrayList<String> members = new ArrayList<>();
            group = br.readLine();
            num = Integer.parseInt(br.readLine());
            for (int j = 0; j < num; j++) {
                members.add(br.readLine());
            }
            Collections.sort(members);
            info.put(group, members);
        }

        String name;
        int type;
        for (int i = 0; i < M; i++) {
            name = br.readLine();
            type = Integer.parseInt(br.readLine());

            if(type == 0) { // 멤버 이름 출력
                for (String member: info.get(name)) {
                    System.out.println(member);
                }
            } else if(type == 1) { // 팀의 이름 출력
                for (Map.Entry<String, ArrayList<String>> entrySet:  info.entrySet()) {
                    if(entrySet.getValue().contains(name)) {
                        System.out.println(entrySet.getKey());
                    }
                }
            }
        }
    }
}
