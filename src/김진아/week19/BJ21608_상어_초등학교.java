package 김진아.week19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.HashSet;

class Student {

    int num;
    HashSet<Integer> like;

    public Student(int num) {
        this.num = num;
        like = new HashSet<>();
    }

}

public class BJ21608_상어_초등학교 {

    final static int DIRECTIONS = 4;
    final static int[] dx = { 1, -1, 0, 0 };
    final static int[] dy = { 0, 0, 1, -1 };

    static int N;
    static int[][] classroom;

    static int[] index;
    static Student[] students;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(reader.readLine());
        classroom = new int[N][N];

        index = new int[N * N + 1];
        students = new Student[N * N];

        for (int i = 0; i < N * N; i++) {
            String[] input = reader.readLine().split(" ");
            students[i] = new Student(Integer.parseInt(input[0]));
            index[students[i].num] = i;
            for (int j = 1; j <= 4; j++) students[i].like.add(Integer.parseInt(input[j]));
        }

        seat();

        writer.write(Integer.toString(satisfaction()));

        writer.close();
        reader.close();
    }

    static void seat() {
        for (int i = 0; i < N * N; i++) seat(i);
    }

    static void seat(int index) {
        int like = -1;
        int empty = -1;
        int[] seat = { -1, -1 };

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (classroom[i][j] != 0) continue;

                int nowLike = 0;
                int nowEmpty = 0;

                for (int k = 0; k < DIRECTIONS; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                    if (classroom[nx][ny] == 0) nowEmpty++;
                    else if (students[index].like.contains(classroom[nx][ny])) nowLike++;
                }

                if (nowLike > like || (nowLike == like && nowEmpty > empty)) {
                    like = nowLike;
                    empty = nowEmpty;
                    seat[0] = i;
                    seat[1] = j;
                }
            }
        }

        classroom[seat[0]][seat[1]] = students[index].num;
    }

    static int satisfaction() {
        int satisfaction = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int student = classroom[i][j];
                if (student == 0) continue;

                int count = 0;

                for (int k = 0; k < DIRECTIONS; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N && classroom[nx][ny] != 0 && students[index[student]].like.contains(classroom[nx][ny])) count++;
                }

                if (count != 0) satisfaction += (int) Math.pow(10, count - 1);
            }
        }

        return satisfaction;
    }

}
