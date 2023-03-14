package 김현욱.additional;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ14865_곡선자르기 {
    static class Node{
        int x,index;
        boolean isUp;

        public Node(int x) {
            this.x = x;
        }
    }

    public static boolean isUp(long y1,long y2){
        return y1<y2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] points = new int[n][2];

        int minX = Integer.MAX_VALUE;//시작점
        int minY = Integer.MAX_VALUE;//저장할 변수
        int minI = -1;//시작점의 인덱스
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
            if(points[i][0] <= minX && points[i][1] <= minY){
                minX = points[i][0];
                minY = points[i][1];
                minI = i;
            }
        }

        ArrayList<Node> crossXLine = new ArrayList<>();//x축을 통과하는 교점들
        int index = 1;
        for(int i=0;i<n;i++) {//시작점부터 순서대로 돌면서 교점이 생기는 x축은 넣어줌
            int ii = (minI+i)%n;
            int x = points[ii][0];
            int y = points[ii][1];

            int nx = points[(ii+1)%n][0];
            int ny = points[(ii+1)%n][1];

//            if(y*ny < 0){//x축과 교점이 생기면 // 이거 int로 하면 overflow생김
            if(y<0 && 0<ny || y>0 && 0>ny){//x축과 교점이 생기면
                crossXLine.add(new Node(x));//x축의 좌표를 넣어줌
            }
        }

        for(int i=0;i<crossXLine.size();i+=2){//교점을 다 돌면서 순서대로 열고 닫고 or 닫고 열고이기 때문에 x가 작은곳을 여는곳으로 셋팅해줌
            Node front = crossXLine.get(i);
            Node back = crossXLine.get(i+1);

            if(front.x < back.x){
                front.isUp = true;
                back.isUp=false;
            }
            else{
                front.isUp = false;
                back.isUp = true;
            }
        }
        crossXLine.sort(new Comparator<Node>() {//셋팅한 뒤에 x좌표를 기준으로 오름차순
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.x < o2.x) return -1;
                else if(o1.x==o2.x) return 0;
                else return 1;
            }
        });
        int inside=0;
        int outside = 0;
        Stack<Node> st = new Stack<>();
        for(int i=0;i<crossXLine.size();i++){
            Node node = crossXLine.get(i);
            node.index = i;//i번째 인덱스로 셋팅
            if(node.isUp){//열린 괄호면
                st.push(node);//스택에 넣기
            }
            else{//닫힌 괄호면
                Node popped = st.pop();//스택에 있는 가장 최근의 열린괄호 pop
                if(Math.abs(node.index - popped.index) == 1){//만약 index의 차이가 1이라면 내부에 봉우리가 x
                    inside++;//inside 증가
                }
                if(st.isEmpty()) outside++;//만약 스택에 열린괄호가 없다면, 최외각 봉우리이므로 outside 1 증가
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(outside).append(' ').append(inside);
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
