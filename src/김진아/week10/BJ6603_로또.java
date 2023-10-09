package 김진아.week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ6603_로또 {
	
	final static int COUNT = 6;
	
	static StringBuilder builder;
	static int k;
	static int[] nums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		builder = new StringBuilder();
		
		String line;
		while (!(line = reader.readLine()).equals("0")) {
			String[] input = line.split(" ");
			
			k = Integer.parseInt(input[0]);
			nums = new int[k];
			for (int i = 0; i < k; i++) nums[i] = Integer.parseInt(input[i + 1]);
			
			select(0, 0, new int[COUNT]);
			builder.append("\n");
		}
		
		writer.write(builder.toString());
		
		writer.close();
		reader.close();
	}
	
	static void select(int n, int count, int[] selected) {
		if (count == COUNT) {
			for (int i = 0; i < count; i++) builder.append(nums[selected[i]]).append(" ");
			builder.append("\n");
			return;
		}
		
		for (int i = n; i <= k - COUNT + count; i++) {
			selected[count] = i;
			select(i + 1, count + 1, selected);
		}
	}
	
}
