import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

class Node {

    Character character;
    Node pre;
    Node next;

    public Node(Character character) {
        this.character = character;
    }

}

public class Main {

    static Node first;
    static Node last;
    static Node[] cursor;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();

        String word = reader.readLine();
        int length = word.length();

        first = new Node(null);
        last = new Node(null);

        Node pre = first;
        for (int i = 0; i < length; i++) {
            Node next = new Node(word.charAt(i));
            pre.next = next;
            next.pre = pre;
            pre = next;
        }
        pre.next = last;
        last.pre = pre;

        cursor = new Node[2];
        cursor[0] = last.pre;
        cursor[1] = last;

        int M = Integer.parseInt(reader.readLine());
        for (int i = 0; i < M; i++) execute(reader.readLine());

        for (Node now = first.next; now.character != null; now = now.next) builder.append(now.character);

        writer.write(builder.toString());

        writer.close();
        reader.close();
    }

    static void execute(String command) {
        String[] commandInfo = command.split(" ");
        if (commandInfo[0].equals("L")) {
            if (cursor[0] == first) return;
            cursor[0] = cursor[0].pre;
            cursor[1] = cursor[0].next;
        } else if (commandInfo[0].equals("D")) {
            if (cursor[1] == last) return;
            cursor[0] = cursor[0].next;
            cursor[1] = cursor[0].next;
        } else if (commandInfo[0].equals("B")) {
            if (cursor[0] == first) return;
            cursor[0] = cursor[0].pre;
            cursor[0].next = cursor[1];
            cursor[1].pre = cursor[0];
        } else {
            Node node = new Node(commandInfo[1].charAt(0));
            node.pre = cursor[0];
            node.next = cursor[1];

            cursor[0].next = node;
            cursor[1].pre = node;
            cursor[0] = node;
        }
    }

}
