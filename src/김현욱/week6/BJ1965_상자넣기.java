package 김현욱.week6;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
*
* https://www.acmicpc.net/problem/1965
*
* */
public class BJ1965_상자넣기 {

    public static int lowerBound(List<Integer>list,int target){
        int left = 0;
        int right = list.size()-1;

        while(left<=right){
            int middle = (left+right)>>1;
            int value = list.get(middle);

            if(value>=target){
                right = middle-1;
            }
            else{
                left = middle+1;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> list = new ArrayList<>();

        for(int i=0;i<n;i++){//모든 박스를 탐색하며
            int box = arr[i];//박스의 크기
            int index = lowerBound(list,box);//최대 증가부분수열을 위해 들어갈 인덱스를 구함
            if(list.size() <= index){
                list.add(box);
            }
            else{
                list.set(index,box);
            }
        }

        bw.write(Integer.toString(list.size()));
        br.close();
        bw.close();
    }
}
