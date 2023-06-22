import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	
	static int[][] sum;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		sum = new int[10001][4];
		for (int i = 1; i <= 3; i++) {
			sum[i][0] = 1;
			sum[i][1] = 1;
			sum[i][i] = 1;
		}
		sum[3][2] = 1;
		
		int totalTestCase = Integer.parseInt(reader.readLine());
		for (int testCase = 0; testCase < totalTestCase; testCase++) {
			int num = Integer.parseInt(reader.readLine());
			writer.append(Integer.toString(findSum(num))).append("\n");
		}
		
		writer.close();
		reader.close();
	}
	
	public static int findSum(int num) {
		if (sum[num][0] == 0) {
			for (int i = 1; i <= 3; i++) {
				if (sum[num - i][0] == 0) findSum(num - i);
			}
			
			sum[num][0] = 1;
			sum[num][1] = sum[num - 1][1];
			sum[num][2] = sum[num - 2][1] + sum[num - 2][2];
			sum[num][3] = sum[num - 3][1] + sum[num - 3][2] + sum[num - 3][3];
		}
		
		return sum[num][1] + sum[num][2] + sum[num][3];
	}

}
