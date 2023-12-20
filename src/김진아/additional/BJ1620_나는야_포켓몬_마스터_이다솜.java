package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashMap;

public class BJ1620_나는야_포켓몬_마스터_이다솜 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        String[] pokemonDictionary = new String[N];
        HashMap<String, Integer> pokemonNumber = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String pokemon = reader.readLine();
            pokemonDictionary[i] = pokemon;
            pokemonNumber.put(pokemon, i + 1);
        }

        for (int i = 0; i < M; i++) {
            String query = reader.readLine();
            if (pokemonNumber.containsKey(query)) builder.append(pokemonNumber.get(query)).append("\n");
            else builder.append(pokemonDictionary[Integer.parseInt(query) - 1]).append("\n");
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
