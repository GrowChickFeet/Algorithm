package 정민경;

import java.io.*;
import java.util.*;

public class BJ2468 {

	static int N;
	static int[][] map;
	
	static int[][] mapCopy;
	static boolean[][] visit;
	
	static int low=Integer.MAX_VALUE;
	static int high=Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				low=Math.min(map[i][j], low);
				high=Math.max(map[i][j], high);
			}
		}
		
		for(int x=low;x<high+1;x++) {
			BFS(x);
		}

	}
	
	static void BFS(int water) {
		
		mapCopy=new int [N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]>water) {
					mapCopy[i][j]=1;
				}else {
					mapCopy[i][j]=0;
				}
			}
		}
		
		visit =new boolean [N][N];
		
		ArrayDeque<Integer> q=new ArrayDeque<>();
		
		q.offer(map[0][0]);
		visit[0][0]=true;
		
		while(q.isEmpty()) {
			
		}
		
		
		
	}

}
