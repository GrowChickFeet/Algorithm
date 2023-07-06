package 김서기.week12;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class B17245_서버실 {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int mp[][] = new int[N][N];
        long total = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                mp[i][j] = Integer.parseInt(st.nextToken());
                total+=mp[i][j];
            }
        }
        long half = (total+1)/2;
        long start = 0;
        long end = Long.MAX_VALUE;
        long answer = Long.MAX_VALUE;
        while(start < end){
            long mid = (start+end)/2;
            long com = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(mp[i][j] < mid){
                        com+=mp[i][j];
                    }
                    else{
                        com+=mid;
                    }
                }
            }
            if(com >= half){
                end = mid;
                answer = Math.min(answer,mid);
            }
            else{
                start = mid+1;
            }
        }
        System.out.println(answer);
    }
}
