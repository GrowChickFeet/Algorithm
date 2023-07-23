import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder builder = new StringBuilder();
		
		String[] input = reader.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		HashMap<String, ArrayList<String>> groups = new HashMap<>();
		HashMap<String, String> idols = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			ArrayList<String> members = new ArrayList<>();
			
			String group = reader.readLine();
			int count = Integer.parseInt(reader.readLine());
			
			for (int j = 0; j < count; j++) {
				String member = reader.readLine();
				members.add(member);
				idols.put(member, group);
			}
			
			Collections.sort(members);
			groups.put(group, members);
		}
		
		for (int i = 0; i < M; i++) {
			String question = reader.readLine();
			int type = Integer.parseInt(reader.readLine());
			
			if (type == 1) builder.append(idols.get(question)).append("\n");
			else {
				for (String member : groups.get(question)) builder.append(member).append("\n");
			}
		}
		
		writer.write(builder.toString());
		writer.close();
		reader.close();
	}

}
