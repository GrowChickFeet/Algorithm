package 김현욱.week11;

import java.io.*;
import java.util.StringTokenizer;

public class BJ19237_어른상어 {
    static int n, m, k;
    static int[][] maze;
    static int[] sharkDir;
    static int[][][] sharkDirPriority;
    static int[][] sharkPos;
    static boolean[] sharkDead;
    static int[][] mv = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};//위,아래,왼쪽,오른쪽
    static int[][] smell;
    static int[][] markNum;

    static boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        sharkDir = new int[m + 1];
        sharkDirPriority = new int[m + 1][4][4];
        sharkPos = new int[m + 1][2];
        sharkDead = new boolean[m + 1];

        smell = new int[n][n];
        markNum = new int[n][n];

        maze = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
                if (maze[i][j] != 0) {
                    sharkPos[maze[i][j]][0] = i;
                    sharkPos[maze[i][j]][1] = j;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            sharkDir[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int d = 0; d < 4; d++) {
                    sharkDirPriority[i][j][d] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }
        int ret = -1;
        for (int time = 1; time <= 1000; time++) {
            int[][] copy = new int[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    copy[i][j] = maze[i][j];
                }
            }
            //냄새 뿌리기
            for (int shark = 1; shark <= m; shark++) {
                if (sharkDead[shark]) continue;

                int x = sharkPos[shark][0];
                int y = sharkPos[shark][1];

                smell[x][y] = time;
                markNum[x][y] = shark;
            }

            //상어 이동
            for (int shark = 1; shark <= m; shark++) {
                if (sharkDead[shark]) continue;
                int x = sharkPos[shark][0];
                int y = sharkPos[shark][1];
                int dir = sharkDir[shark];
                //주변에 빈공간 있는지 확인
                boolean nearBlank = false;
                for (int d = 0; d < 4; d++) {
                    int priorityDir = sharkDirPriority[shark][dir][d];

                    int nx = x + mv[priorityDir][0];
                    int ny = y + mv[priorityDir][1];

                    if(isIn(nx,ny) && (smell[nx][ny] == 0 || smell[nx][ny] + k <= time)){
                        nearBlank = true;
                        if(copy[nx][ny] == 0 || copy[nx][ny] > shark) {
                            copy[x][y] = 0;
                            copy[nx][ny] = shark;

                            sharkPos[shark][0] = nx;
                            sharkPos[shark][1] = ny;
                            sharkDir[shark] = priorityDir;
                        }
                        else{
                            //자신보다 큰 수가 있으면 dead
                            copy[x][y] = 0;
                            sharkDead[shark] = true;
                        }
                        break;
                    }
                }

                if(nearBlank) continue;//근처에 빈공간이 있으면 다음 상어 이동

                for(int d=0;d<4;d++){
                    int priorityDir = sharkDirPriority[shark][dir][d];

                    int nx = x + mv[priorityDir][0];
                    int ny = y + mv[priorityDir][1];

                    if(isIn(nx,ny) && (smell[nx][ny]+k > time && markNum[nx][ny] == shark)){
                        sharkPos[shark][0] = nx;
                        sharkPos[shark][1] = ny;
                        sharkDir[shark] = priorityDir;

                        copy[x][y] = 0;
                        copy[nx][ny] = shark;
                        break;
                    }
                }
            }
            boolean isAlone = !sharkDead[1];//1번의 생존여부
            for(int i=2;i<=m;i++){
                isAlone = isAlone && (sharkDead[i]);
            }
            if(isAlone){
                ret = time;
                break;
            }
            maze = copy;
        }
        bw.write(Integer.toString(ret));
        br.close();
        bw.close();
    }
}
