import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.Queue;

class Person {
	
	String name;
	String[] parents;
	ArrayList<String> children;
	
	double royal;
	boolean calculated;
	
	public Person(String name, String[] parents) {
		this.name = name;
		this.parents = parents;
		children = new ArrayList<>();
	}
	
}

public class Main {
	
	static String founder;
	static HashMap<String, Person> people;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = reader.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		people = new HashMap<>();
		
		founder = reader.readLine();
		people.put(founder, new Person(founder, null));
		
		for (int i = 0; i < N; i++) {
			input = reader.readLine().split(" ");
			people.put(input[0], new Person(input[0], new String[] { input[1], input[2] }));
		}
		
		for (Map.Entry<String, Person> person : people.entrySet()) {
			String name = person.getKey();
			if (name.equals(founder)) continue;
			
			for (String parent : person.getValue().parents) {
				if (people.containsKey(parent)) people.get(parent).children.add(name);
			}
		}
		
		calculate();
		
		String king = "";
		double royal = 0;
		for (int i = 0; i < M; i++) {
			String name = reader.readLine();
			if (!people.containsKey(name)) continue;
			Person candidate = people.get(name);
			
			if (candidate.royal > royal) {
				king = candidate.name;
				royal = candidate.royal;
			}
		}
		
		writer.write(king);
		
		writer.close();
		reader.close();
	}
	
	static void calculate() {
		Queue<Person> calculated = new LinkedList<>();
		
		Person person = people.get(founder);
		person.royal = 1;
		person.calculated = true;
		
		calculated.offer(person);
		
		while (!calculated.isEmpty()) {
			person = calculated.poll();
			
			for (String child : people.get(person.name).children) {
				Person now = people.get(child);
				
				if (now.royal != 0) now.calculated = true;
				now.royal += person.royal * 0.5;
				
				for (String parent : now.parents) {
					if (parent.equals(person.name)) continue;
					if (!people.containsKey(parent)) now.calculated = true;
				}
				
				if (now.calculated) calculated.offer(now);
			}
		}
	}
	
}
