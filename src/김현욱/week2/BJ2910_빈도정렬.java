package 김현욱.week2;

import java.io.*;
import java.util.*;

public class BJ2910_빈도정렬 {
    public static Map<Integer, int[]> count = new HashMap<>();//빈도를 저장할 맵
    public static int N, C;
    public static Integer[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        array = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            array[i] = Integer.parseInt(st.nextToken());
            int[] freq = count.getOrDefault(array[i],new int[]{0,Integer.MAX_VALUE});
            freq[0]++;
            freq[1] = Math.min(freq[1],i);
            count.put(array[i],freq);//array[i]가 나온 빈도를 갱신하는 코드
        }

        Arrays.sort(array,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                int[] c1 = count.get(o1);
                int[] c2 = count.get(o2);

                if(c1[0] > c2[0]){
                    return -1;
                }
                else if(c1[0] < c2[0]){
                    return 1;
                }
                else{
                    return c1[1] - c2[1];
                }
            }
        });

        for(int i=0;i<N;i++){
            sb.append(array[i]).append(' ');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
