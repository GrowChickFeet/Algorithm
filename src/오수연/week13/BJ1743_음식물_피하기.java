import java.io.*;
import java.util.*;
public class Main {
    static int Y, X;
    static int N;
    static char[][] board;
    static boolean[][] visited;
    static int maxN;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static class Pos{
        int y,x;
        Pos(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new char[Y+1][X+1];
        visited = new boolean[Y+1][X+1];

        for(int y=1; y<=Y; ++y){
            for(int x=1; x<=X; ++x){
                board[y][x] = '.';
            }
        }
        for(int i=0; i<N; ++i){
            String[] input = reader.readLine().split(" ");
            int y = Integer.parseInt(input[0]);
            int x = Integer.parseInt(input[1]);
            board[y][x] = '#';
        }

        for(int y=1; y<=Y; ++y){
            for(int x=1; x<=X; ++x){
                if(board[y][x] == '#' && !visited[y][x])
                {
                    maxN = Math.max(maxN,dfs(y, x));

                }

            }
        }

        System.out.println(maxN);
    }

    static int dfs(int curY, int curX){
        int cnt=1;
        visited[curY][curX] = true;
        for(int d=0; d<4; ++d){
            int nextX = curX+dx[d];
            int nextY = curY+dy[d];
            if(!canGo(nextY, nextX)) continue;
            cnt += dfs(nextY, nextX);
        }
        return cnt;
    }

    private static boolean canGo(int nextY, int nextX) {
        if(!(0<=nextX && nextX<=X && 0<=nextY && nextY<=Y )) return false;
        if(visited[nextY][nextX]) return false;
        if(board[nextY][nextX]!='#') return false;
        return true;
    }
}
