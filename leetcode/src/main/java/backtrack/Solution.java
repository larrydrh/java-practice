package backtrack;

public class Solution {
    int result = 0;
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s =  "adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc"

        ;
        String t ="bcddceeeebecbc";
        System.out.println(solution.numDistinct(s, t));;

    }

    public int numDistinct(String s, String t) {
        backtrack(s, t);
        return result;
    }
    public void backtrack(String s, String t) {
        if (t.length() == 1) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == t.charAt(0)) {
                    result++;
                }
            }
            return;
        }

        for (int i =0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(0)) {
                backtrack(s.substring(i+1), t.substring(1));
            }
        }
    }
}
