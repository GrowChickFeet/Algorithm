package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Student {
    
    int num;
    ArrayList<Student> shorter;
    ArrayList<Student> taller;
    
    public Student(int num) {
        this.num = num;
        shorter = new ArrayList<>();
        taller = new ArrayList<>();
    }
    
}

public class BJ2252_줄_세우기 {
    
    static Student[] students;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        
        students = new Student[N];
        for (int i = 0; i < N; i++) students[i] = new Student(i + 1);
        
        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            int shorter = Integer.parseInt(input[0]) - 1;
            int taller = Integer.parseInt(input[1]) - 1;
            
            students[shorter].taller.add(students[taller]);
            students[taller].shorter.add(students[shorter]);
        }

        writer.write(sort());

        writer.close();
        reader.close();
    }
    
    static String sort() {
        StringBuilder builder = new StringBuilder();
        
        Queue<Student> queue = new LinkedList<>();
        
        for (Student student : students) {
            if (student.shorter.size() == 0) {
                builder.append(student.num).append(" ");
                queue.offer(student);
            }
        }
        
        while (!queue.isEmpty()) {
            Student now = queue.poll();
            
            for (Student next : now.taller) {
                next.shorter.remove(now);
                if (next.shorter.size() == 0) {
                    builder.append(next.num).append(" ");
                    queue.offer(next);
                }
            }
        }
        
        return builder.toString();
    }

}
