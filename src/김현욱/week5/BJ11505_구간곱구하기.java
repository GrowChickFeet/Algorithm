package 김현욱.week5;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11505_구간곱구하기 {
    static class SegTree{
        int n;
        int size;
        long[] trees;
        int MOD = 1_000_000_007;

        public SegTree(int[] arr){
            this.n = arr.length;
            int height = (int)Math.ceil(Math.log(n)/Math.log(2));
            this.size = (1<<(height+1));
            trees = new long[this.size];
            makeTree(arr,1,0,n-1);
        }

        private long makeTree(int[] arr, int node, int left, int right) {
            if(left==right){
                return trees[node] = arr[left];
            }

            int middle = (left+right)>>1;
            return trees[node] = (makeTree(arr,node*2,left,middle)*makeTree(arr,node*2+1,middle+1,right))%MOD;
        }

        private long get(int node,int left,int right,int start,int end){
            if(right<start || end<left){
                return 1;
            }
            if(start<=left && right<=end){
                return trees[node];
            }
            int middle = (left+right)>>1;

            return (get(node*2,left,middle,start,end)*get(node*2+1,middle+1,right,start,end))%MOD;
        }
        private long update(int node,int left,int right,int targetIndex,int target){
            if(targetIndex<left||right<targetIndex){
                return trees[node];
            }

            if(left==right){
                return trees[node] = target;
            }

            int middle = (left+right)>>1;
            return trees[node] = (update(node*2,left,middle,targetIndex,target)*update(node*2+1,middle+1,right,targetIndex,target))%MOD;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        SegTree segTree = new SegTree(arr);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m+k;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;

            if(a==1){
                //change
                arr[b] = c+1;
                segTree.update(1,0,n-1,b,c+1);
            }
            else{
                sb.append(segTree.get(1,0,n-1,b,c)).append('\n');
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
