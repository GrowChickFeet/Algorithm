package 김서기.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1347_미로_만들기 {

    static boolean mp[][];
    static int dy[] = {0,1,0,-1};
    static int dx[] = {1,0,-1,0};
    static int MAX = 200;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        mp = new boolean[MAX][MAX];
        Pos cur = new Pos(MAX/2,MAX/2,1);
        Pos mn = new Pos(MAX,MAX,-1);
        Pos mx = new Pos(0,0,-1);
        mp[cur.y][cur.x] = true;
        mn.y = Math.min(mn.y,cur.y);
        mn.x = Math.min(mn.x,cur.x);
        mx.y = Math.max(mx.y,cur.y);
        mx.x = Math.max(mx.x,cur.x);
        for (int i = 0; i < N; i++) {
            char op = str.charAt(i);
            if(op == 'R'){
                cur.d = (cur.d+1)%4;
            }else if(op == 'L'){
                cur.d = (cur.d+3)%4;
            }else{
                cur.y += dy[cur.d];
                cur.x += dx[cur.d];
                mp[cur.y][cur.x] = true;
                mn.y = Math.min(mn.y,cur.y);
                mn.x = Math.min(mn.x,cur.x);
                mx.y = Math.max(mx.y,cur.y);
                mx.x = Math.max(mx.x,cur.x);
            }
        }
        for (int i = mn.y; i <= mx.y ; i++) {
            for (int j = mn.x; j <= mx.x ; j++) {
                if(mp[i][j]){
                    System.out.print('.');
                }else{
                    System.out.print('#');
                }
            }
            System.out.println();
        }
    }
    static class Pos{
        int y;
        int x;
        int d;
        public Pos(int y, int x,int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }
}