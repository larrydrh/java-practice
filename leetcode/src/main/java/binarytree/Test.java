package binarytree;


import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

    public static void addTwo( List<String> result) {
        result.add("trwo");
    }

    public class Node{
        public Node(int data) {
            this.data = data;
        }
        Node left;
        Node right;
        int data;


    }
    public Node buildTree(List<Integer> treeList) {

        Deque<Optional<Integer>> treeDeque = new ArrayDeque<>();
        treeList.forEach(e-> treeDeque.add(Optional.ofNullable(e)));
        Deque<Node> rebuildTree = new ArrayDeque<>();
        rebuildTree.add(new Node(treeDeque.poll().get()));
        Node root = rebuildTree.peek();
        while(!rebuildTree.isEmpty()) {
            Node treeNode =rebuildTree.poll();
            if (!treeDeque.isEmpty()) {
                if (!treeDeque.peek().isEmpty()) {
                    treeNode.left = new Node(treeDeque.poll().get());
                    rebuildTree.add(treeNode.left);
                }  else {
                    treeDeque.poll();
                }
            }
            if (!treeDeque.isEmpty()) {
                if (!treeDeque.peek().isEmpty()) {
                    treeNode.right = new Node(treeDeque.poll().get());
                    rebuildTree.add(treeNode.right);
                } else {
                    treeDeque.poll();
                }
            }
        }
        return root;
    }
    public List<Integer> depthTraverse(Node node, final List<Integer> result) {
        if (node == null) {
            return null;
        }
        final List<Integer> beforeResult = result;
        List<Integer> thisResult = new ArrayList<>();
        List<Integer> currentResult = new ArrayList<>();
        if (beforeResult.size() == 0) {
            beforeResult.add(node.data);
            currentResult.add(node.data);
        } else {
            beforeResult.forEach(e-> currentResult.add(e*10+node.data));
        }
        if (node.left == null && node.right == null) {
            return currentResult;
        }
        if (node.left != null) {
            thisResult.addAll(depthTraverse(node.left, currentResult));
        }
        if (node.right != null) {
            thisResult.addAll(depthTraverse(node.right, currentResult));
        }
        return thisResult;
    }

    public static void main(String[] args) {

//        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
//        queue.put();
//        queue.poll();
//        queue.isEmpty();
//        List<String> a = new ArrayList<>();
//a.
//        HashMap<Integer, Integer>  map = new HashMap<>();
        System.out.println(5/3);

        List<Integer> treeList = Arrays.asList(1, 3, 2, 4, 5, 6,7);

        List<Integer> subList = treeList.subList(0,2);
        int[] a = new int[12];
//        Test test = new Test();
//        List<Integer> result = test.depthTraverse(test.buildTree(treeList), new ArrayList<>());
//        for (Integer data : result) {
//            System.out.println(data);
//        }
    }
}
