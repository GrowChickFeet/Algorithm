package 남현실.week5;

/*
https://www.acmicpc.net/problem/16493
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/*
[입력]
- N: 남은 기간 (1 <= N <= 200)
- M: 챕터 수 (1 <= M <= 20)
- 각 챕터 당
    - 소요 일 수 (1 <= x <= 20)
    - 페이지수 (1 <= y <= 300)


[출력]
- N일간 읽을 수 있는 최대 페이지 수

[풀이]
- 페이지수가 높은 순으로 정렬한다 / 페이지수가 같으면 소요 일수가 짧은 것을 선택한다
- 챕터 수만큼 높은 페이지수부터 소요 일 수를 반복해서 비교한다
    - 남은 수보다
        - 크면 넘긴다
        - 작으면
            - 전체 소요 일 수에서 해당 소요 일 수 만큼 빼준다
            - 전체 가능한 페이지 수를 더해준다
    - 챔터 수나 소요 일 수가 0이면 반복을 멈춘다
- 전체 가능한 페이지 수를 출려한다

 */
public class BJ16493_최대_페이지_수 {
    static class Capability implements Comparable<Capability> {
        int day, page;

        public Capability(int day, int page) {
            this.day = day;
            this.page = page;
        }


        @Override
        public int compareTo(Capability o) {
            if (this.page > o.page) {
                return 1;
            } else if (this.page < o.page) {
                return -1;
            } else {
                if (this.day > o.day) {
                    return -1;
                } else if (this.day < o.day) {
                    return 1;
                }
                return 0;
            }
        }

        @Override
        public String toString() {
            return "Capability{" +
                    "day=" + day +
                    ", page=" + page +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        Capability[] capabilitys = new Capability[M];

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            capabilitys[i] = new Capability(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }

        // 정렬하기 (페이지수 높은순 >> 소요 시간 짧은 순)
        Arrays.sort(capabilitys, Collections.reverseOrder());

        System.out.println(Arrays.toString(capabilitys));

        // 최대 페이지 수 구하기
        int totalPage = 0;
        int totalDay = N;
        for (Capability capability: capabilitys) {
            if(totalDay == 0) { // 남은 일 수 없으면 멈추기
                break;
            }
            if(totalDay < capability.day) { // 소요일이 남은 일 수보다 높으면 넘기기
                continue;
            }
            totalPage += capability.page;
            totalDay -= capability.day;
        }

        // 최대 페이지수 출력
        System.out.println(totalPage);
    }
}
