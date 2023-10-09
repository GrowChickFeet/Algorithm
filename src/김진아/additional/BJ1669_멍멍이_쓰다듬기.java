package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class BJ1669_멍멍이_쓰다듬기 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = reader.readLine().split(" ");
		int X = Integer.parseInt(input[0]);
		int Y = Integer.parseInt(input[1]);
		
		writer.write(Integer.toString(day(Y - X)));
		
		writer.close();
		reader.close();
	}
	
	static int day(int amount) {
		if (amount <= 3) return amount;
		
		int day = (int) Math.sqrt(amount);
		if (amount == day * day) return day * 2 - 1;
		if (amount <= day * day + day) return day * 2;
		return day * 2 + 1;
	}
	
}
