package 김현욱.week17;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2759_팬케이크_뒤집기 {
    static int k;
    static void reverse(int[] arr,int idx){
        int[] temp = new int[arr.length];
        for(int i=0;i<=idx;i++){
            temp[i] = arr[idx-i];
        }
        for(int i=0;i<=idx;i++){
            arr[i] = temp[i];
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=0;t<T;t++){
            k=0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> answer = new ArrayList<>();
            int n = Integer.parseInt(st.nextToken());
            int[] cakes = new int[n];
            boolean finish = true;
            for(int i=0;i<n;i++){
                cakes[i] = Integer.parseInt(st.nextToken());
                if(cakes[i] != i+1) finish = false;
            }
            if(finish){
                sb.append(0).append('\n');
                continue;
            }
            for(int i=n;i>=1;i--){
                int idx = -1;
                for(int j=0;j<n;j++){
                    if(cakes[j] == i){
                        idx = j;
                        break;
                    }
                }

                if(idx == i - 1) continue;//제자리에 있는게 맞으니 continue

                //옮기려는 숫자가 첫번째 인덱스에 있으면 바꿀필요 없기때문
                if(idx > 0) {
                    reverse(cakes, idx);
                    k++;
                    answer.add(idx + 1);
                }

                //최종 목적지가 1번째 인덱스라면 이미 뒤집었으므로 안해줘도됨
                if(i > 1) {
                    reverse(cakes, i - 1);
                    k++;
                    answer.add(i);
                }
            }
            sb.append(k).append(' ');
            answer.stream().forEach(ans -> sb.append(ans).append(' '));
            sb.append('\n');
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}