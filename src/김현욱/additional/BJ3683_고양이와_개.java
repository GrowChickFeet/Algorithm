package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ3683_고양이와_개 {
    static class Vote {
        int like;
        int dislike;

        public Vote(String like, String dislike) {
            this.like = Integer.parseInt(like.substring(1));
            this.dislike = Integer.parseInt(dislike.substring(1));
        }

        public boolean mismatch(Vote other) {
            return this.like == other.dislike || this.dislike == other.like;
        }
    }

    public static boolean dfs(List<Integer>[] graph, int[] match, boolean[] visited, int node) {
        visited[node] = true;
        for (int next : graph[node]) {
            if (match[next] == -1 || (!visited[match[next]] && dfs(graph, match, visited, match[next]))) {
                match[next] = node;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            List<Vote> catLover = new ArrayList<>();
            List<Vote> dogLover = new ArrayList<>();

            for (int i = 0; i < v; i++) {
                st = new StringTokenizer(br.readLine());
                String like = st.nextToken();
                String dislike = st.nextToken();

                if (like.charAt(0) == 'C') {
                    catLover.add(new Vote(like, dislike));
                } else {
                    dogLover.add(new Vote(like, dislike));
                }
            }

            List<Integer>[] graph = new List[catLover.size()];
            for (int i = 0; i < catLover.size(); i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < catLover.size(); i++) {
                for (int j = 0; j < dogLover.size(); j++) {
                    if (catLover.get(i).mismatch(dogLover.get(j))) {
                        graph[i].add(j);
                    }
                }
            }
            int result = 0;
            int[] match = new int[dogLover.size()];
            Arrays.fill(match, -1);
            for (int i = 0; i < catLover.size(); i++) {
                boolean[] visited = new boolean[catLover.size()];
                if (dfs(graph, match, visited, i)) {
                    result++;
                }
            }
            sb.append(v - result).append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
