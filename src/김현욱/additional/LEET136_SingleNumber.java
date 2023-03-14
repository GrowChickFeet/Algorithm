package 김현욱.additional;
/*
* https://leetcode.com/problems/single-number/description/
*
* */
public class LEET136_SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int i=0;i<nums.length;i++){
            result^=nums[i];
        }
        return result;
    }
}
