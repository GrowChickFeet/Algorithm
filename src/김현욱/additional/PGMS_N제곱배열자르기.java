package 김현욱.additional;
/*
* https://school.programmers.co.kr/learn/courses/30/lessons/87390
*
* */
class Solution {
    public int[] solution(int n, long left, long right) {
        int size=(int)(right-left+1);
        int[] answer = new int[size];
        int ind=0;
        for(long i=left;i<=right;i++){
            int row=(int)(i/n)+1;
            int col=(int)(i%n)+1;

            answer[ind++]=Math.max(col,row);
        }
        return answer;
    }
}