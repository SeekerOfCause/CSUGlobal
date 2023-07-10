package CriticalThinking.Module8;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class Counter {
    // AtomicLong for thread-safe counter value
    private final AtomicLong counter;

    // Locks and conditions for synchronization
    private final Lock countUpLock;
    private final Lock countDownLock;
    private final Condition cvUp;
    private final Condition cvDown;

    // Flag indicating whether the counter is currently counting up or down
    private boolean countingUp;

    // Constructor for Counter
    public Counter() {
        counter = new AtomicLong(0);
        countUpLock = new ReentrantLock();
        countDownLock = new ReentrantLock();
        cvUp = countUpLock.newCondition();
        cvDown = countDownLock.newCondition();
        countingUp = true;
    }

    // Method to safely add or subtract from the counter based on the counting direction
    public void safeAddSubtract(long value, boolean countingUp) {
        if (countingUp) {
            countUpLock.lock();
            try {
                counter.addAndGet(value);
            } finally {
                countUpLock.unlock();
            }
        } else {
            countDownLock.lock();
            try {
                counter.addAndGet(value);
            } finally {
                countDownLock.unlock();
            }
        }
    }

    // Get the maximum count value
    public long getMaxCount() {
        return 20;
    }

    // Get the lock for counting up
    public Lock getCountUpLock() {
        return countUpLock;
    }

    // Get the lock for counting down
    public Lock getCountDownLock() {
        return countDownLock;
    }

    // Get the condition variable for counting up
    public Condition getCvUp() {
        return cvUp;
    }

    // Get the condition variable for counting down
    public Condition getCvDown() {
        return cvDown;
    }

    // Check if the counter is currently counting up
    public boolean isCountingUp() {
        return countingUp;
    }

    // Set the counting direction of the counter
    public void setCountingUp(boolean countingUp) {
        this.countingUp = countingUp;
    }
}