package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.List;
import java.util.NoSuchElementException;

public class QueueWithMax {

    private StackWithMax.Stack enqueue = new StackWithMax.Stack();
    private StackWithMax.Stack dequeue = new StackWithMax.Stack();

    @EpiTest(testDataFile = "queue_with_max.tsv")
    public static void queueTest(List<QueueOp> ops) throws TestFailure {
        try {
            QueueWithMax q = new QueueWithMax();

            for (QueueOp op : ops) {
                switch (op.op) {
                    case "QueueWithMax":
                        q = new QueueWithMax();
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
                    case "max":
                        int s = q.max();
                        if (s != op.arg) {
                            throw new TestFailure("Max: expected " + op.arg +
                                    ", got " + s);
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
                        .runFromAnnotations(args, "QueueWithMax.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

    public void enqueue(Integer x) {
        enqueue.push(x);
    }

    public Integer dequeue() {

        if (dequeue.empty()) {
            while (!enqueue.empty()) {
                dequeue.push(enqueue.pop());
            }
        }
        if (!dequeue.empty()) {
            return dequeue.pop();
        }
        throw new NoSuchElementException("Cannot get dequeue() on empty queue.");
    }

    public Integer max() {

        if (!enqueue.empty()) {
            return dequeue.empty() ? enqueue.max()
                    : Math.max(enqueue.max(), dequeue.max());
        } else if (!dequeue.empty()) {
            return dequeue.max();
        }
        throw new NoSuchElementException("Cannot get max() on empty queue.");
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
