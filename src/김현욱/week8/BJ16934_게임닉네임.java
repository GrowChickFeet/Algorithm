package 김현욱.week8;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class BJ16934_게임닉네임 {
    static class Trie{
        Trie[] child;
        boolean isEnd;
        int count;

        public Trie(){
            this.child = new Trie['z'-'a'+1];
            this.isEnd = false;
            this.count = 0;
        }

        public String insert(String str){
            StringBuilder result = new StringBuilder();

            Trie trie = this;
            char[] carr = str.toCharArray();
            boolean flag = true;

            for(int i=0;i<carr.length;i++){
                char c = carr[i];
                int cIndex = c-'a';

                if(flag) result.append(c);

                if(trie.child[cIndex] == null){
                    trie.child[cIndex] = new Trie();
                    flag = false;
                }


                trie = trie.child[cIndex];
            }

            trie.isEnd = true;
            trie.count++;

            if(result.length() == str.length() && trie.count > 1) result.append(trie.count);

            return result.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        Trie trie = new Trie();

        for(int i=0;i<n;i++){
            String str = br.readLine();

            sb.append(trie.insert(str)).append('\n');
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}