import java.io.*;
import java.util.*;

public class Main{

    static char[][] board = new char[101][101];
    static int minX=50, minY=50, maxX=50, maxY=50;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    static class Pos{
        int x, y;
        int dir;
        Pos(int y, int x, int dir){
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }
    static Pos curPos = new Pos(50, 50, 0);
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        String input = reader.readLine();

        for(int y=0; y<101; ++y){
            for(int x=0; x<101; ++x){
                board[y][x] = '#';
            }
        }
        board[curPos.y][curPos.x] = '.';
        for(int i=0; i<N; ++i){
            setDIR(input.charAt(i));
        }

        printBoard();
    }

    static void setDIR(char cmd){
        char next = cmd;
        switch(next){
            case 'F':
                curPos.y = curPos.y + dy[curPos.dir];
                curPos.x = curPos.x + dx[curPos.dir];
                board[curPos.y][curPos.x] = '.';
                setMinMax();
                break;
            case 'R':
                curPos.dir = (curPos.dir+1+4)%4;
                break;
            case 'L':
                curPos.dir = (curPos.dir-1+4)%4;
                break;
        }
    }

    static void setMinMax(){
        minX = Math.min(minX, curPos.x);
        minY = Math.min(minY, curPos.y);
        maxX = Math.max(maxX, curPos.x);
        maxY = Math.max(maxY, curPos.y);
    }

    static void printBoard(){
        for(int y=minY; y<=maxY; ++y){
            for(int x=minX; x<=maxX; ++x){
                System.out.print(board[y][x]);
            }
            System.out.println();
        }
    }
}