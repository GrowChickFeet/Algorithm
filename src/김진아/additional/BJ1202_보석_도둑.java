package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class BJ1202_보석_도둑 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        PriorityQueue<int[]> gems = new PriorityQueue<>((gem1, gem2) -> gem2[1] - gem1[1]);
        for (int i = 0; i < N; i++) {
            input = reader.readLine().split(" ");
            gems.offer(new int[] { Integer.parseInt(input[0]), Integer.parseInt(input[1]) });
        }

        TreeSet<Integer> bags= new TreeSet<>();
        HashMap<Integer, Integer> bagCount = new HashMap<>();
        for (int i = 0; i < K; i++) {
            int bag = Integer.parseInt(reader.readLine());
            bags.add(bag);
            bagCount.put(bag, bagCount.getOrDefault(bag, 0) + 1);
        }

        long sum = 0;
        while (!gems.isEmpty()) {
            int[] gem = gems.poll();

            Integer bag = null;
            if (bags.contains(gem[0])) bag = gem[0];
            else bag = bags.higher(gem[0]);

            if (bag == null) continue;

            sum += gem[1];
            bagCount.put(bag, bagCount.getOrDefault(bag, 0) - 1);
            if (bagCount.get(bag) <= 0) {
                bags.remove(bag);
                if (bags.isEmpty()) break;
            }
        }

        writer.write(Long.toString(sum));

        writer.close();
        reader.close();
    }

}
