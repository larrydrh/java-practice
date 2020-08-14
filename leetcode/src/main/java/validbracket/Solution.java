package validbracket;

import java.security.KeyPair;
import java.util.AbstractMap;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Solution {
    public boolean isValid(String s) {
        Map.Entry<Solution, String> test = new AbstractMap.SimpleEntry<Solution, String>(new Solution(), "test");

        Deque<Character> deque = new LinkedList<>();
        for (int i=0; i < s.length(); i++) {
            if (s.charAt(i) == '(' ) {
                deque.push(')');
            } else if (s.charAt(i) == '[' ) {
                deque.push(']');
            } else if (s.charAt(i) == '{') {
                deque.push('}');
            } else {
                if (deque.peek()!= null && deque.peek().equals(s.charAt(i))) {
                    deque.pop();
                } else {
                    return false;
                }
            }
        }
        if (deque.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isValid("(("));
        Deque<Integer> a = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            a.addLast(i);
        }
        System.out.println(a.peekFirst());
        System.out.println(a.peekLast());
        System.out.println(a);
    }
}