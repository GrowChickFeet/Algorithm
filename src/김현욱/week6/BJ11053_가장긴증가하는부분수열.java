package 김현욱.week6;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ11053_가장긴증가하는부분수열 {
    static int lowerBound(List<Integer> list, int target){
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
        for(int i=0;i<n;i++){
            int index = lowerBound(list,arr[i]);
            if(list.size()<=index){
                list.add(arr[i]);
            }
            else{
                list.set(index,arr[i]);
            }
        }
        bw.write(Integer.toString(list.size()));
        br.close();
        bw.close();
    }
}
