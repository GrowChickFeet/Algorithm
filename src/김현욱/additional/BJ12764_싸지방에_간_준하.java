package 김현욱.additional;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ12764_싸지방에_간_준하 {
    static class Person implements Comparable<Person>{
        int in,out;
        public Person(int in, int out) {
            this.in = in;
            this.out = out;
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.in,o.in);
        }
    }
    static class Computer implements Comparable<Computer>{
        int out,index;

        public Computer(int out, int index) {
            this.out = out;
            this.index = index;
        }

        @Override
        public int compareTo(Computer o) {
            return Integer.compare(this.out,o.out);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Person[] people = new Person[n];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int in = Integer.parseInt(st.nextToken());
            int out = Integer.parseInt(st.nextToken());

            people[i] = new Person(in,out);
        }
        Arrays.sort(people);

        int count = 0;
        int[] useCount = new int[1000001];

        PriorityQueue<Integer> canUse = new PriorityQueue<>();
        PriorityQueue<Computer> computers = new PriorityQueue<>();

        for(int i=0;i<n;i++){
            Person p = people[i];
            int in = p.in;
            int out = p.out;

            while(!computers.isEmpty() && computers.peek().out < in ){
                canUse.add(computers.poll().index);
            }

            if(canUse.isEmpty()){
                computers.add(new Computer(out,count));
                useCount[count++]++;
            }
            else{
                computers.add(new Computer(out,canUse.peek()));
                useCount[canUse.poll()]++;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(count).append('\n');
        for(int i=0;i<count;i++){
            sb.append(useCount[i]).append(' ');
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
