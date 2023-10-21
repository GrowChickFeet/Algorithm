package 김진아.additional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;

class Directory {

    String name;
    ArrayList<Directory> subdirectories;

    public Directory(String name) {
        this.name = name;
        subdirectories = new ArrayList<>();
    }

    public void mkdir(Directory directory) {
        subdirectories.add(directory);
    }

    public Directory find(String name) {
        for (Directory subdirectory : subdirectories) {
            if (subdirectory.name.equals(name)) return subdirectory;
        }
        return null;
    }

}

public class BJ7432_디스크_트리 {

    static StringBuilder builder;
    static Directory root;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        builder = new StringBuilder();

        root = new Directory(null);

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String[] directories = reader.readLine().split("\\\\");

            Directory pre = root;
            for (String directory : directories) {
                Directory now = pre.find(directory);
                if (now == null) {
                    now = new Directory(directory);
                    pre.mkdir(now);
                }
                pre = now;
            }
        }

        structure(root, 0);
        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void structure(Directory directory, int depth) {
        directory.subdirectories.sort((subdirectory1, subdirectory2) -> subdirectory1.name.compareTo(subdirectory2.name));
        for (Directory subdirectory : directory.subdirectories) {
            builder.append(" ".repeat(depth)).append(subdirectory.name).append("\n");
            if (!subdirectory.subdirectories.isEmpty()) structure(subdirectory, depth + 1);
        }
    }

}
