package 김현욱.week12;

import java.io.*;
import java.util.*;

public class BJ16165_걸그룹마스터준석이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, ArrayList<String>> groups = new HashMap<>();
        Map<String,String> person = new HashMap<>();

        for(int i=0;i<n;i++){
            String groupName = br.readLine();
            int num = Integer.parseInt(br.readLine());
            ArrayList<String> members = new ArrayList<>();
            for(int j=0;j<num;j++){
                String name = br.readLine();
                members.add(name);
                person.put(name,groupName);
            }
            Collections.sort(members);

            groups.put(groupName,members);
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<m;i++){
            String name = br.readLine();
            int type = Integer.parseInt(br.readLine());
            if(type == 0){
                groups.get(name).stream().forEach(p->sb.append(p).append('\n'));
            }
            else{
                sb.append(person.get(name)).append('\n');
            }
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
