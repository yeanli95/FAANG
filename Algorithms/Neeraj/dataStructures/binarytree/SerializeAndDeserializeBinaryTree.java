import java.util.*;

/*
https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/995
 */
public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        Codec codec = new Codec();
        TraverseATree.TreeNode treeNode = TraverseATree.createTreeNode(new ArrayList<>(Arrays.asList(1, 2, 3, null, null, 4, 5)));
        String serialize = codec.serialize(treeNode);
        System.out.println(serialize);
        TraverseATree.TreeNode deserialize = codec.deserialize(serialize);
        System.out.println(deserialize);
        System.out.println("=========================");
        treeNode = null;
        serialize = codec.serialize(treeNode);
        System.out.println(serialize);
        deserialize = codec.deserialize(serialize);
        System.out.println(deserialize);
    }
    static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TraverseATree.TreeNode root) {
            if (root == null) return "[]";
            LinkedList<Integer> output = new LinkedList<>();
            LinkedList<TraverseATree.TreeNode> queue = new LinkedList<>();
            queue.add(root);
            output.add(root.val);
            while (!queue.isEmpty()) {
                int size = queue.size();
//            Iterate and add all the level values to the queue.
                for (int i = 0; i < size; i++) {
                    TraverseATree.TreeNode poll = queue.poll();
                    if (poll != null) {
                        queue.add(poll.left);
                        queue.add(poll.right);
                        Integer leftValue = poll.left != null ? poll.left.val : null;
                        Integer rightValue = poll.right != null ? poll.right.val : null;
                        output.add(leftValue);
                        output.add(rightValue);
                    }
                }
                boolean allNulls = true;
//            Loop through the level queue
                for (int i = 0; i < queue.size(); i++) {
                    if (queue.get(i) != null) {
                        allNulls = false;
                        break;
                    }
                }
                if (allNulls) {
//            If the execution is reaching this point means all the last level values are null that needs to deleted and then break.
                    for (int i = 0; i < queue.size(); i++) {
                        output.pollLast();
                    }
                    return output.toString();
                }
            }
            return output.toString();
        }

        // Decodes your encoded data to tree.
        public TraverseATree.TreeNode deserialize(String data) {
            List<Integer> nums = convertStringToList(data);
            TraverseATree.TreeNode treeNode = null;
            if (nums.size() > 0) {
                Queue<TraverseATree.TreeNode> treeNodeQueue = new LinkedList<>();
                int index = 1;
                treeNode = new TraverseATree.TreeNode(nums.get(0));
                treeNodeQueue.add(treeNode);
                while (index < nums.size() && !treeNodeQueue.isEmpty()) {
                    TraverseATree.TreeNode peek = treeNodeQueue.poll();
                    if (nums.get(index) != null) {
                        TraverseATree.TreeNode left = new TraverseATree.TreeNode(nums.get(index++));
                        peek.left = left;
                        treeNodeQueue.add(left);
                    } else index++;
                    if (index < nums.size() && nums.get(index) != null) {
                        TraverseATree.TreeNode right = new TraverseATree.TreeNode(nums.get(index++));
                        peek.right = right;
                        treeNodeQueue.add(right);
                    } else index++;
                }
            }
            return treeNode;
        }

        private List<Integer> convertStringToList(String data) {
            ArrayList<Integer> output = new ArrayList<>();
            if (data.isEmpty()) return output;
            String[] split = data.replace("[", "").replace("]", "").split(",");
            for (String current : split) {
                if (!current.trim().isEmpty()) {
                    Integer currentValue = current.trim().equals("null") ? null : Integer.valueOf(current.trim());
                    output.add(currentValue);
                }
            }
            return output;
        }
    }
}