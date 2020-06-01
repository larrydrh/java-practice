package dp;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s =  "rabbbit";
        String t = "rabbit";
        int result =  solution.numDistinct(s, t);

    }

    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length()][s.length()];
        // for(int i=0; i < t.lenght; i++) {
        //     dp[i][0] = 0;
        // }
        // for(int j=0; j < s.length(); j++) {
        //     d[0][j] = 0;
        // }
        int result = 0;
        calculateDp(dp, t.length()-1, s.length()-1, s, t);
        for(int i=0; i < t.length(); i++) {
            for(int j=i; j < s.length(); j ++) {
                if (dp[i][j] == t.length()) {
                    result++;
                }
            }
        }
        return result;
    }
    int calculateDp(int[][] dp, int i, int j , String s, String t) {
        if (i == 0 || j == 0) {
            if ( t.charAt(i) == s.charAt(j)) {
                dp[i][j] = 1;
            } else {
                dp[i][j] = 0;
            }
            return dp[i][j];
        }
        if (t.charAt(i) == s.charAt(j)) {
            dp[i][j] = calculateDp(dp, i - 1, j - 1, s, t) + 1;
        } else {
            dp[i][j] = Math.max(calculateDp(dp, i - 1, j, s, t), calculateDp(dp, i, j - 1, s, t));
        }
        return dp[i][j];
    }
}