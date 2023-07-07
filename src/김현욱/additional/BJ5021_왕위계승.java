package 김현욱.additional;

import java.io.*;
import java.util.*;

public class BJ5021_왕위계승 {
    static Map<String,Integer> number = new HashMap<>();//각 이름별 index를 부여해줌
    static double[] percent;//각 이름별 얼마나 피가 섞였는지
    static ArrayList<String>[] graph;
    static int[] indeg;
    static int index=0;
    static int N,M;
    static class Node{
        String name;
        Double rate;

        public Node(String name, Double rate) {
            this.name = name;
            this.rate = rate;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[501];
        indeg = new int[501];//진입차수
        for(int i=0;i<501;i++){
            graph[i] = new ArrayList<>();
        }

        String king  = br.readLine();
        number.put(king,index++);//king은 0번인덱스로

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String father = st.nextToken();
            String mother = st.nextToken();

            if(number.get(child) == null) number.put(child,index++);//인덱스에 없다면 추가
            if(number.get(father) == null) number.put(father,index++);//인덱스에 없다면 추가
            if(number.get(mother) == null) number.put(mother,index++);//인덱스에 없다면 추가
            graph[number.get(father)].add(child);//그래프 연결
            graph[number.get(mother)].add(child);//그래프 연결

            indeg[number.get(child)]+=2;//자식의 진입차수를 갱신해줌
        }
        percent = new double[index+1];
        percent[0] = 1.0;//왕은 1.0

        //위상정렬로 혼혈 수치를 구함
        Queue<Node> que = new LinkedList<>();
        for(String name : number.keySet()){//진입차수가 0인 이름들을 모두 큐에넣음
            int ind = number.get(name);
            if(indeg[ind] == 0){
                que.offer(new Node(name,percent[ind]));
            }
        }

        while(!que.isEmpty()){
            Node node = que.poll();
            String name = node.name;
            double rate = node.rate;
            int ind = number.get(name);

            for(String next : graph[ind]){
                int nextInd = number.get(next);

                indeg[nextInd]--;
                percent[nextInd] += (rate/2);

                if(indeg[nextInd] == 0){
                    que.offer(new Node(next,percent[nextInd]));
                }
            }
        }

        String result = "";
        Double rate = -Double.MAX_VALUE;//최대값을 갱신하기위해 -MAX로 설정
        for(int i=0;i<M;i++){
            String name = br.readLine();
            if(number.get(name) == null){
                if(rate == -Double.MAX_VALUE) result = name;
            }
            else {
                int ind = number.get(name);

                if (rate <= percent[ind]) {//rate보다 현재 수치가 더 높으면 갱시
                    result = name;
                    rate = percent[ind];
                }
            }
        }
        bw.write(result);
        br.close();
        bw.close();
    }
}
