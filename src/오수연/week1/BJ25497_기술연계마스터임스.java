package com.ssafy.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ25497_기술연계마스터임스 {
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	static int N;
	static String s;
	static int cnt;
	static Stack<Character> st1 = new Stack<Character>();
	static Stack<Character> st2 = new Stack<Character>();
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(reader.readLine());
		s = reader.readLine();
		
		for(int i=0; i<N; ++i) {
			char temp = s.charAt(i);
			if(temp == 'L') {
				st1.add(temp);
			}else if(temp == 'S') {
				st2.add(temp);
			}else if(temp == 'R') {
				if(st1.isEmpty()) break;
				st1.pop();
				cnt++;
			}else if(temp == 'K') {
				if(st2.isEmpty()) break;
				st2.pop();
				cnt++;
			}else {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
