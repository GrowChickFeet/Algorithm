import java.io.*;

public class Main {
    static long[][] dp;
    static int[][] mods;
    static int MAX_BIT;
    static int n,k;
    static String[] arr;

    public static int getMod(int ind,int mod) {
        if(mods[ind][mod]!=-1) return mods[ind][mod];
        int current = mod;
        int len = arr[ind].length();
        for(int i=0;i<len;i++) {
            current*=10;
            current+=(arr[ind].charAt(i)-'0');
            current%=k;
        }
        return mods[ind][mod]=current;
    }

    public static long solve(int bitmask,int mod) {
        if(dp[bitmask][mod]!=-1) {
            return dp[bitmask][mod];
        }

        if(bitmask==MAX_BIT) {
            return mod==0 ? 1:0;
        }

        long count = 0;
        for(int i=0;i<n;i++) {
            int ind = 1<<i;
            if((bitmask&ind)==0) {
                count+=solve(bitmask|ind,getMod(i,mod));
            }
        }
        return dp[bitmask][mod]=count;
    }
    public static long getGcd(long n,long m) {
        if(m==0) return n;
        return getGcd(m,n%m);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.valueOf(br.readLine());
        arr = new String[n];
        for(int i=0;i<n;i++) {
            arr[i]=br.readLine();
        }
        k = Integer.valueOf(br.readLine());

        MAX_BIT = (1<<n)-1;

        dp = new long[1<<n][k];
        mods = new int[n][k];

        for(int j=0;j<k;j++) {
            for(int i=0;i<n;i++) {
                mods[i][j]=-1;
            }
            for(int i=0;i<=MAX_BIT;i++) {
                dp[i][j]=-1;
            }
        }

        long answer = solve(0,0);
        if(answer ==0) {
            bw.write("0/1");
        }
        else {
            long fac = 1;
            for(int i=2;i<=n;i++) {
                fac*=i;
            }
            long gcd = getGcd(fac,answer);
            sb.append(answer/gcd).append('/').append(fac/gcd);
            bw.write(sb.toString());
        }
        bw.flush();
        bw.close();
        br.close();
    }
}