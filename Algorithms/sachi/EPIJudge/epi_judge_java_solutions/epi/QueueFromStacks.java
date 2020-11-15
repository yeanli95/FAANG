package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

public class QueueFromStacks {

    @EpiTest(testDataFile = "queue_from_stacks.tsv")
    public static void queueTest(List<QueueOp> ops) throws TestFailure {
        try {
            Queue q = new Queue();

            for (QueueOp op : ops) {
                switch (op.op) {
                    case "QueueWithMax":
                        q = new Queue();
                        break;
                    case "enqueue":
                        q.enqueue(op.arg);
                        break;
                    case "dequeue":
                        int result = q.dequeue();
                        if (result != op.arg) {
                            throw new TestFailure("Dequeue: expected " +
                                    op.arg + ", got " +
                                    result);
                        }
                        break;
                }
            }
        } catch (NoSuchElementException e) {
            throw new TestFailure("Unexpected NoSuchElement exception");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "QueueFromStacks.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

    public static class Queue {

        private Deque<Integer> enqueue = new ArrayDeque<>();
        private Deque<Integer> dequeue = new ArrayDeque<>();

        public void enqueue(Integer x) {
            enqueue.addFirst(x);
        }

        public Integer dequeue() {

            if (dequeue.isEmpty()) {
                // Transfers the elements from enqueue to dequeue.
                while (!enqueue.isEmpty()) {
                    dequeue.addFirst(enqueue.removeFirst());
                }
            }

            if (!dequeue.isEmpty()) {
                return dequeue.removeFirst();
            }
            throw new NoSuchElementException("Cannot pop empty queue");
        }
    }

    @EpiUserType(ctorParams = {String.class, int.class})
    public static class QueueOp {
        public String op;
        public int arg;

        public QueueOp(String op, int arg) {
            this.op = op;
            this.arg = arg;
        }
    }
}
