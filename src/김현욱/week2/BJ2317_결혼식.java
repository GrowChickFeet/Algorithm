package 김현욱.week2;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BJ2317_결혼식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] lions = new int[k];
        int lionMax = -1;
        int lionMin = Integer.MAX_VALUE;
        int result = 0;


        for(int i=0;i<k;i++){
            lions[i] = Integer.parseInt(br.readLine());
            lionMax = Math.max(lionMax,lions[i]);
            lionMin = Math.min(lionMin,lions[i]);
            if(i!=0) result += Math.abs(lions[i] - lions[i-1]);
        }

        int humanMax= -1;
        int humanMin = Integer.MAX_VALUE;
        for(int i=0;i<n-k;i++){
            int height = Integer.parseInt(br.readLine());
            humanMin = Math.min(humanMin,height);
            humanMax = Math.max(humanMax,height);
        }

        //최솟값
        if(lionMin > humanMin){
            int minInside = (lionMin - humanMin) * (lions[0] != lionMin && lions[k-1] != lionMin ? 2 : 1);
            int minOutside = Math.min(lions[0] - humanMin, lions[k-1] - humanMin);

            result += Math.min(minInside,minOutside);
        }

        if(lionMax < humanMax){
            int maxInside = (humanMax - lionMax) * (lions[0] != lionMax && lions[k-1] != lionMax ? 2 : 1);
            int maxOutside = Math.min(humanMax - lions[0],humanMax-lions[k-1]);
            result+= Math.min(maxInside,maxOutside);
        }

        bw.write(Integer.toString(result));
        br.close();
        bw.close();
    }
}