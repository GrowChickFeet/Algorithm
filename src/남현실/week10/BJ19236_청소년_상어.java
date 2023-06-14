package 남현실.week10;

/*
https://www.acmicpc.net/problem/19236
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
[조건]
4*4
- 한 칸: 물고기 한마리
- 물고기: 물고기 번호, 방향 가짐
    - 1 <= 번호 <= 자연수
    - 번호 중복 X
    - 방향 8가지: 상하좌우 대각선
      상에서 시작해서 45도 반시계방향으로: 1~8
- (0,0) 부터 물고기 먹기 시작

- 물고기는 번호가 작은 물고기부터 순서대로 이동
    - 한 칸 이동, 서로의 위치 바꿈
    - 이동 가능할 때까지 45도로 계속 반시게 회전
    - 이동 가능 칸
        - 빈 칸
        - 다른 물고기가 있는 칸
    - 이동 불가능 칸
        - 상어 존재
        - 공간 넘어가는 것
- 물고기의 이동이 모두 끝나면 상어가 이동
    - 한 번에 여러칸 이동,
      방향에 있는 칸으로 이동
    - 이동을 완료해면 물고기 먹고, 물고기 방향 가짐
    - 이동 중에 지나가는 칸에 있는 물고기 안먹음
    - 이동 불가능 칸
        - 물고기 없는 칸
    - 더이상 이동이 불가하면 집으로 감
- 상어 이동 후에는 다시 물고기가 이동, 이 과정을 반복

[출력]
상어가 먹을 수 있는 물고기 번호의 합의 최댓값?
 */

/*
7 6 2 3 15 6 9 8
3 1 1 8 14 7 10 1
6 1 13 6 4 3 11 4
16 1 8 7 5 2 12 2
 */


public class BJ19236_청소년_상어 {
    static final int[][] moves = {
            {-1, 0},
            {-1, -1},
            {0, -1},
            {1, -1},
            {1, 0},
            {1, 1},
            {0, 1},
            {-1, 1}
    };

    static int max = 0;

    static class Info {
        int[] loc;
        int dir;

        public Info(int[] loc, int dir) {
            this.loc = loc;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "info{" +
                    "loc=" + Arrays.toString(loc) +
                    ", dir=" + dir +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp;

        Info[] originFishInfos = new Info[16]; // 위치, 방향
        int[][] originMap = new int[4][4]; // 물고기(0-15) 번호, 상어 정보(-1),  아무것도 없음(-2)

        int tempFishNum, tempFishDir;
        for (int i = 0; i < 4; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                tempFishNum = Integer.parseInt(temp[2 * j]) - 1;
                tempFishDir = Integer.parseInt(temp[2 * j + 1]) - 1;

                originMap[i][j] = tempFishNum;
                originFishInfos[tempFishNum] = new Info(new int[]{i, j}, tempFishDir);
            }
        }

        run(copyMap(originMap), copyFishInfos(originFishInfos), 0, 0, 0);
        System.out.println(max);
    }


    static void run(int[][] map, Info[] fishInfos, int sum, int x, int y) {
        //// eat fish
        // 현재 물고기 번호, 정보 가져오기
        int fishNum = map[x][y];
        Info fishInfo = fishInfos[fishNum];

        // 상어 위치 표시 (-1)
        map[x][y] = -1;

        // 먹은 물고기 번호 올려주기 (원래 번호로 되돌리고 더해주기)
        sum += fishNum + 1;

        // 잡아먹힌 물고기는 null로 만들기
        fishInfos[fishNum] = null;


        //// move fish
        moveFishs(map, fishInfos);


        //// next
        int nx, ny;
        int[] offset = moves[fishInfo.dir];

        map[x][y] = -2; // 현재 자리는 빈공간으로 만들기
        for (int i = 1; i <= 3; i++) {
            nx = x + offset[0] * i;
            ny = y + offset[1] * i;
            if (isOutOfMap(nx, ny) || isEmpty(map[nx][ny])) { // 더이상 갈 곳이 없을 때 종료
                max = Math.max(sum, max);
                continue;
            }
            run(copyMap(map), copyFishInfos(fishInfos), sum, nx, ny);
        }
    }

    static void printMap(int[][] map) {
        System.out.println("=== map ===");
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    static void printSharkInfo(Info sharkInfo) {
        System.out.println("=== sharkInfo ===");
        System.out.println(sharkInfo);
        System.out.println();
    }

    static void printFishInfos(Info[] fishInfos) {
        System.out.println("=== fishInfos ===");
        System.out.println(Arrays.toString(fishInfos));
        System.out.println();
    }

    static int[][] copyMap(int[][] tgMap) {
        int[][] map = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = tgMap[i][j];
            }
        }
        return map;
    }

    static Info[] copyFishInfos(Info[] tgFishInfos) {
        Info[] fishInfos = new Info[16];
        Info tempInfo;
        for (int i = 0; i < 16; i++) {
            tempInfo = tgFishInfos[i];
            if (tempInfo == null) {
                fishInfos[i] = null;
                continue;
            }
            fishInfos[i] = new Info(new int[]{tempInfo.loc[0], tempInfo.loc[1]}, tempInfo.dir);
        }
        return fishInfos;
    }

    static void moveFishs(int[][] map, Info[] fishInfos) {
        Info tempFishInfo;

        for (int i = 0; i < 16; i++) { // 물고기 번호 순차적으로 검사
            tempFishInfo = fishInfos[i];
            if (tempFishInfo == null) { // 물고기 없으면 넘어가기
                continue;
            }
            int x, y, nx, ny, num = i, nNum;
            int curDir; // 현재 방향
            int offset[];
            for (int j = 0; j < 8; j++) { // 방향 45도 반시계방향으로 돌리기
                curDir = (tempFishInfo.dir + j) % 8; // 현재 방향 업데이트
                offset = moves[curDir]; //현재 방향 offset 가져오기
                x = tempFishInfo.loc[0];
                y = tempFishInfo.loc[1];
                nx = x + offset[0];
                ny = y + offset[1];
                if (isOutOfMap(nx, ny) || isShark(map[nx][ny])) { // 다음 좌표가 이동 불가면 넘어가기
                    continue;
                }
                nNum = map[nx][ny];

                // update fish loc, dir
                fishInfos[num].loc[0] = nx;
                fishInfos[num].loc[1] = ny;
                fishInfos[num].dir = curDir;
                if (!isEmpty(nNum)) {
                    fishInfos[nNum].loc[0] = x;
                    fishInfos[nNum].loc[1] = y;
                }

                // change fish num
                map[x][y] = nNum;
                map[nx][ny] = num;
                break;
            }
        }
    }

    static boolean isShark(int num) {
        return (num == -1);
    }

    static boolean isEmpty(int num) {
        return (num == -2);
    }

    static boolean isOutOfMap(int x, int y) {
        return (x < 0 || x >= 4 || y < 0 || y >= 4);
    }
}
