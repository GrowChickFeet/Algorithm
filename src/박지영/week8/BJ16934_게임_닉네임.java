package 박지영.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  모든 prefix들을 HashSet에 저장
 *  모든 이름들을 Map에 저장해서 이름의 중복과 접두사를 확인
 */
public class BJ16934_게임_닉네임 {
    static int N;
    static HashSet<String> prefix;
    static HashMap<String, Integer> map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N =Integer.parseInt(br.readLine());
        prefix = new HashSet<>();
        map = new HashMap<String, Integer>();

        for (int i = 0; i < N; i++) {
            String nickname = br.readLine();
            String alias = getAlias(nickname);

            map.put(nickname, map.getOrDefault(nickname, 0) + 1);

            if (!alias.equals("")) {        // 별칭이 정해졌을 때
                sb.append(alias).append("\n");
            } else if (map.get(nickname) == 1){     // 별칭을 정의할 수 없어서 x를 붙여야 할 때 & 해당 이름이 처음임
                sb.append(nickname).append("\n");
            } else {        // x를 붙여야 하는데 기존의 사용자가 있었을 때
                sb.append(nickname).append(map.get(nickname)).append("\n");
            }

        }

        System.out.println(sb.toString());

    }

    static String getAlias(String s) {      // 별칭을 가져오는 함수
        String result = "";
        boolean flag = false;       // 별칭이 정해졌을 때 더이상 Reult를 update하지 않기 위해 선언
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (!prefix.contains(sb.toString()) && !flag) {
                result = sb.toString();
                flag = true;
            }
            prefix.add(sb.toString());      // 모든 prefix들을 저장
        }

        return result;
    }
}
