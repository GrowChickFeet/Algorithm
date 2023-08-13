import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Main {

    final static int TRUTH = 1;
    final static int LIE = 2;

    static int N;
    static int M;
    static int[] know;
    static ArrayList<ArrayList<Integer>> parties;

    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        know = new int[N];
        input = reader.readLine().split(" ");
        int count = Integer.parseInt(input[0]);
        for (int i = 1; i <= count; i++) know[Integer.parseInt(input[i]) - 1] = TRUTH;

        parties = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            ArrayList<Integer> party = new ArrayList<>();
            input = reader.readLine().split(" ");
            count = Integer.parseInt(input[0]);
            for (int j = 1; j <= count; j++) party.add(Integer.parseInt(input[j]) - 1);
            parties.add(party);
        }

        max = 0;
        letsParty(0, 0, TRUTH, LIE, know);
        letsParty(0, 0, LIE, TRUTH, know);

        writer.write(Integer.toString(max));

        writer.close();
        reader.close();
    }

    static void letsParty(int party, int over, int tell, int not, int[] know) {
        if (party == M) {
            max = Math.max(over, max);
            return;
        }

        int[] now = copy(know);
        for (int person : parties.get(party)) {
            if (now[person] == not) return;
            now[person] = tell;
        }

        if (tell == LIE) over++;
        letsParty(party + 1, over, TRUTH, LIE, now);
        letsParty(party + 1, over, LIE, TRUTH, now);
    }

    static int[] copy(int[] origin) {
        int length = origin.length;
        int[] copy = new int[length];
        for (int i = 0; i < length; i++) copy[i] = origin[i];
        return copy;
    }

}
