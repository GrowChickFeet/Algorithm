package 김현욱.additional;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1469_숌_사이_수열 {
    static int N; // 크기
    static int[] per; // 순열
    static int[] result;
    static int[] array;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        per = new int[N];
        array = new int[N];
        result = new int[N*2];
        visited = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);//오름차순 정렬, 사전순으로 가장 빠른걸 찾기위해

        boolean res = dfs(0);

        if(res){
            for(int i=0;i<2*N;i++){
                sb.append(result[i]).append(' ');
            }
        }
        else{
            sb.append(-1);
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }

    private static boolean dfs(int index) {
        if(index == N){//기저조건
            return isValid();//가능하면 true,아니면 false 리턴
        }

        for(int i=0;i<N;i++){//0~N-1 원소를 돌며
            if(!visited[i]){//방문하지 않았던거면
                visited[i] = true;//방문 체크
                per[index] = array[i];//INDEX번째 원소에 array[i] 를 입력

                boolean res = dfs(index+1);//다음 index를 수행하기 위한 재귀 호출
                if(res) return true;//만약 이 경우에 수열이 만들어 진다면 더이상 필요x, return

                visited[i] = false;//방문 체크 해제
            }
        }
        return false;//이 경우는 다 돌아도 가능한 경우가 없으므로 false 리턴
    }

    private static boolean isValid() {//해당 순서로 만들어지는 수열이 옳바른지 확인
        Arrays.fill(result,-1);//먼저 -1로 초기화

        for(int i=0;i<N;i++){//만들어진 순열을 이용해서
            int idx = i;
            while(idx < 2*N && result[idx]!=-1) idx++;//i번째 순열의 원소가 들어갈 처음 위치를 찾아줌, 2*N보다 작아야하고, 그 위치에 원소가 들어있으면 안됨

            if(idx==2*N) return false;//조건을 탐색해가며 idx가 2*N까지 갔다면, 즉 해당범위 내에 i번째 원소가 들어갈 자리가 없다면, 수열을 만들 수 없으므로 false

            int nextIndex = idx+per[i]+1;//쌍으로 들어갈 원소의 인덱스를 구해줌
            if(nextIndex >=2*N || result[nextIndex] != -1){//만약 그 인덱스가 범위를 벗어나거나 값이 이미 있는경우
                return false;//수열을 만들 수 없음
            }
            result[idx] = per[i];//만들 수 있으면 idx위치와
            result[nextIndex] = per[i];//nextIndex에 해당 값을 넣어줌
        }

        return true;//모두 통과하면 수열이 만들어진것이므로 true리턴
    }

}
