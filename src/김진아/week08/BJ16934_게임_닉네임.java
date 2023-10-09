package 김진아.week08;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.HashSet;

public class BJ16934_게임_닉네임 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());
        String[] nicknames = new String[N];
        for (int i = 0; i < N; i++) nicknames[i] = reader.readLine();

        HashMap<String, Integer> count = new HashMap<>();
        HashSet<String> prefix = new HashSet<>();

        for (String nickname : nicknames) {
            if (!count.containsKey(nickname)) count.put(nickname, 1);
            else count.replace(nickname, count.get(nickname) + 1);

            boolean existAlias = false;
            int length = nickname.length();

            for (int i = 1; i <= length; i++) {
                String alias = nickname.substring(0, i);
                if (!existAlias && !prefix.contains(alias)) {
                    builder.append(alias).append("\n");
                    existAlias = true;
                }
                prefix.add(alias);
            }

            if (existAlias) continue;

            StringBuilder aliasBuilder = new StringBuilder();
            aliasBuilder.append(nickname);

            int x = count.get(nickname);
            if (x > 1) aliasBuilder.append(x);

            String alias = aliasBuilder.toString();
            builder.append(alias).append("\n");
            prefix.add(alias);
        }

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

}
