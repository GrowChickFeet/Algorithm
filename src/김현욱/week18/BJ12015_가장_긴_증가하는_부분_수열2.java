package 김현욱.week18;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
public class BJ12015_가장_긴_증가하는_부분_수열2 {
    public static int lower_bound(List<Integer> list,int target) {
        int left = 0;
        int right = list.size();

        while(left<right) {
            int middle = (left+right)/2;

            if(target <= list.get(middle)) {
                right =middle;
            }
            else {
                left = middle+1;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n =Integer.valueOf(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            arr[i]= Integer.valueOf(st.nextToken());
        }

        List<Integer> result = new ArrayList<>();
        for(int i=0;i<n;i++) {
            int ind = lower_bound(result, arr[i]);
            if(result.isEmpty()) {
                result.add(arr[i]);
            }
            else {
                if(ind>= result.size()) {
                    result.add(arr[i]);
                }
                else {
                    result.set(ind, arr[i]);
                }
            }
        }

        sb.append(result.size());
        bw.write(sb.toString());

        bw.flush();

        bw.close();
        br.close();
    }
}
