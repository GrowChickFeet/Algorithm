package 박지영.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2910_빈도정렬 {
    static int N, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();        // 순서가 있기 때문에 LinkedHashMap (숫자: 횟수)

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (!map.containsKey(tmp)) {
                map.put(tmp, 1);
            } else {
                map.put(tmp, map.get(tmp)+1);
            }
        }

        // ------입력 받기 -------

        map = sortedByKeys(map);

        for (Map.Entry entry : map.entrySet()) {        // 결과 출력
            for (int i = 0; i < Integer.parseInt(entry.getValue().toString()); i++) {
                sb.append(entry.getKey()).append(" ");
            }
        }
        System.out.println(sb);
    }

    private static LinkedHashMap<Integer, Integer> sortedByKeys(LinkedHashMap<Integer, Integer> map) {      // value를 기준으로 정렬
        List<Map.Entry<Integer, Integer>> entries = new LinkedList<>(map.entrySet());
        Collections.sort(entries, ((o1, o2) -> {
            if (o1.getValue() < o2.getValue()) {                    // value를 기준으로 내림차순
                return 1;
            } else if (o1.getValue() == o2.getValue()) {
                return 0;
            } else {
                return -1;
            }
//            o1.getKey().compareTo(o2.getKey())
        }));
        LinkedHashMap<Integer, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
