import java.io.*;
import java.util.*;


public class BJ15831_준표의_조약돌 {
	
	
	static String road;
	static int N, W, B;
	static int curW, curB;
	static char temp;
	static int maxL;
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] input = reader.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		B = Integer.parseInt(input[1]);
		W = Integer.parseInt(input[2]);
		road = reader.readLine();
		int start=0;
		int end=0;
		for(int i=0; i<N; ++i) {
			temp = road.charAt(i);
			if(temp=='W') {
				curW++;
				end=i;
			}else {
				curB++;
				end=i;
			}
			
			if(B < curB) {
				temp = road.charAt(start);
				if(temp == 'W') {
					curW--;
					start++;
				}
				else {
					curB--;
					start++;
				}
			}
		
			if(curW>=W)
				maxL = Math.max(end-start+1, maxL);
			
		}
		System.out.println(maxL);
		
		
	}

}