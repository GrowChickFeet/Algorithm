package 김진아.week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

public class BJ14719_빗물 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = reader.readLine().split(" ");
		int H = Integer.parseInt(input[0]);
		int W = Integer.parseInt(input[1]);
		
		ArrayList<ArrayList<Integer>> blocks = new ArrayList<>();
		for (int i = 0; i < H; i++) blocks.add(new ArrayList<>());
		
		input = reader.readLine().split(" ");
		for (int i = 0; i < W; i++) {
			int block = Integer.parseInt(input[i]);
			for (int j = 0; j < block; j++) blocks.get(j).add(i);
		}
		
		int water = 0;
		for (int i = 0; i < H; i++) {
			int count = blocks.get(i).size();
			for (int j = 0; j < count - 1; j++) {
				int now = blocks.get(i).get(j);
				int next = blocks.get(i).get(j + 1);
				if (next - now > 1) water += next - now - 1;
			}
		}
		
		writer.write(Integer.toString(water));
		
		writer.close();
		reader.close();
	}
	
}
