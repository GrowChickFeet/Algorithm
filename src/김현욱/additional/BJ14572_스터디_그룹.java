package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14572_스터디_그룹 {
    static StringTokenizer st;
    static int[] algorithm;
    static int N,K,D;
    static ArrayList<Integer>[] graph;

    static class Node implements Comparable<Node> {
        int point;
        ArrayList<Integer> algorithm;

        public Node( int point,ArrayList<Integer> algorithm) {
            this.point = point;
            this.algorithm = algorithm;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.point, o.point);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        Node[] people = new Node[N];
        algorithm = new int[K+1];

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int point = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> algo = new ArrayList<>();
            for (int j = 0; j < count; j++) {
                algo.add(Integer.parseInt(st.nextToken()));
            }
            people[i] = new Node(point,algo);
        }

        Arrays.sort(people);

        int left = 0;
        int right = 0;
        int result = 0;
        setAlgorithmCount(people[left].algorithm, true);

        while(true){
            int gap = people[right].point - people[left].point;
            if(gap <= D) {
                result = Math.max(result, getScore((right++) - left + 1));
                if (right >= N) break;

                setAlgorithmCount(people[right].algorithm, true);
                continue;
            }
            setAlgorithmCount(people[left++].algorithm,false);
        }

        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }

    private static void setAlgorithmCount(ArrayList<Integer> list, boolean plus) {
        for (int algoNum : list) {
            algorithm[algoNum] += (plus ? 1 : -1);
        }
    }

    private static int getScore(int pCount) {
        int[] result = new int[2];

        for (int i = 1; i <= K; i++) {
            if (algorithm[i] != 0) {
                result[0]++;
            }

            if (algorithm[i] == pCount) {
                result[1]++;
            }
        }

        return (result[0] - result[1]) * pCount;
    }
}
