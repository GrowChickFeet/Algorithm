package 남현실.additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class BJ1953_팀배분 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] temp;
        ArrayList<Integer>[] list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(temp[0]); j++) {
                list[i].add(Integer.parseInt(temp[j])-1);
            }
        }

        ArrayList<Integer> groupA = new ArrayList<>();
        ArrayList<Integer> groupB = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if(!visited[i]) {
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(i);
                visited[i] = true;
                groupA.add(i);


                int cur;
                int k = 1;
                while (!queue.isEmpty()) {
                    cur = queue.poll();

                    for (int next: list[cur]) {
                        if(visited[next]) {
                            continue;
                        }
                        queue.offer(next);
                        visited[next] = true;
                        if(k == 0) {
                            groupA.add(next);
                            k = 1;
                        } else {
                            groupB.add(next);
                            k = 0;
                        }
                    }
                }
            }
        }

        if(groupB.isEmpty()) {
            groupA.remove(0);
            groupB.add(0);
        }

        Collections.sort(groupA);
        Collections.sort(groupB);

        StringBuilder result = new StringBuilder();
        result.append(groupA.size()).append("\n");
        for (int num: groupA) {
            result.append(num+1).append(" ");
        }
        result.append("\n");
        result.append(groupB.size()).append("\n");
        for (int num: groupB) {
            result.append(num+1).append(" ");
        }
        result.append("\n");

        System.out.println(result);
    }
}
