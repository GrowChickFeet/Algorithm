package 김서기.week13;
import java.util.*;
public class B17845_수강_과목 {
    static final int INF = (2 << 32) - 1;
    static final int MAX = 100010;

    static int[] dp = new int[MAX];

    static boolean cmp(Pair p1, Pair p2) {
        if (p1.first == p2.first) {
            return p1.second < p2.second;
        }
        return p1.first < p2.first;
    }

    static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        List<Pair> sub = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int I = sc.nextInt();
            int T = sc.nextInt();
            sub.add(new Pair(I, T));
        }

        for (int i = 0; i < K; i++) {
            for (int j = N; j >= 1; j--) {
                if (sub.get(i).second <= j) {
                    dp[j] = Math.max(dp[j], dp[j - sub.get(i).second] + sub.get(i).first);
                }
            }
        }

        System.out.println(dp[N]);
    }
}
