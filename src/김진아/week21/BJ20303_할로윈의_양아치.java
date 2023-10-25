package 김진아.week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ20303_할로윈의_양아치 {

    static int[] friends;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);

        int[] candy = new int[N];
        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) candy[i] = Integer.parseInt(input[i]);

        friends = new int[N];
        for (int i = 0; i < N; i++) friends[i] = i;
        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            makeFriend(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]) - 1);
        }

        int[] index = new int[N];
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (index[findFriend(friends[i])] == 0) {
                index[findFriend(friends[i])] = ++count;
            }
        }

        int[][] total = new int[count + 1][2];
        for (int i = 0; i < N; i++) {
            total[index[findFriend(friends[i])]][0]++;
            total[index[findFriend(friends[i])]][1] += candy[i];
        }

        int[][] max = new int[count + 1][K];
        for (int i = 1; i <= count; i++) {
            for (int j = 1; j < K; j++) {
                if (j < total[i][0]) max[i][j] = max[i - 1][j];
                else max[i][j] = Math.max(max[i - 1][j - total[i][0]] + total[i][1], max[i - 1][j]);
            }
        }

        writer.write(Integer.toString(max[count][K - 1]));

        writer.close();
        reader.close();
    }

    static void makeFriend(int kid1, int kid2) {
        int friend1 = findFriend(kid1);
        int friend2 = findFriend(kid2);
        if (friend1 < friend2) friends[friend2] = friend1;
        else if (friend1 > friend2) friends[friend1] = friend2;
    }

    static int findFriend(int kid) {
        if (friends[kid] == kid) return kid;
        return friends[kid] = findFriend(friends[kid]);
    }

}
