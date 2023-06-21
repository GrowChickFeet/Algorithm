package 남현실.additional;

/*
https://www.acmicpc.net/problem/1953
 */

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

        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(temp[0]); j++) {
                list[i].add(Integer.parseInt(temp[j]) - 1);
            }
        }

        ArrayList<Integer>[] group = new ArrayList[2];
        for (int i = 0; i < 2; i++) {
            group[i] = new ArrayList<>();
        }

        boolean[] visited = new boolean[N];
        int[] groupNum = new int[N];
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(i);
                visited[i] = true;
                group[0].add(i);
                groupNum[i] = 0;


                int cur;
                while (!queue.isEmpty()) {
                    cur = queue.poll();

                    int gnum = (groupNum[cur] + 1) % 2;
                    for (int next : list[cur]) {
                        if (visited[next]) {
                            continue;
                        }
                        queue.offer(next);
                        visited[next] = true;
                        group[gnum].add(next);
                        groupNum[next] = gnum;
                    }
                }
            }
        }

        if (group[1].isEmpty()) {
            group[0].remove(1);
            group[1].add(1);
        }
        Collections.sort(group[0]);
        Collections.sort(group[1]);

        StringBuilder result = new StringBuilder();
        result.append(group[0].size()).append("\n");
        for (int num : group[0]) {
            result.append(num + 1).append(" ");
        }
        result.append("\n");
        result.append(group[1].size()).append("\n");
        for (int num : group[1]) {
            result.append(num + 1).append(" ");
        }
        result.append("\n");

        System.out.println(result);
    }
}
