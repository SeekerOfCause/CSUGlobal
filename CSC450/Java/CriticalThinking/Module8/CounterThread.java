package CriticalThinking.Module8;

import java.util.concurrent.atomic.AtomicLong;

class CounterThread implements Runnable {
    // AtomicLong for thread-safe thread ID generation
    private static final AtomicLong THREAD_ID_COUNTER = new AtomicLong(0);

    // Counter object shared by all threads
    private final Counter counter;

    // Flag indicating whether the thread is counting up or down
    private final boolean countingUp;

    // Thread ID of the current thread
    private final long threadId;

    // Constructor for CounterThread
    public CounterThread(Counter counter, boolean countingUp) {
        this.counter = counter;
        this.countingUp = countingUp;
        this.threadId = THREAD_ID_COUNTER.incrementAndGet();
    }

    // Get the string representation of the thread ID
    private String getThreadIdString() {
        return String.valueOf(threadId);
    }

    @Override
    public void run() {
        try {
            // Validate Counter object and associated locks/conditions
            if (counter != null && counter.getCountUpLock() != null && counter.getCountDownLock() != null &&
                    counter.getCvUp() != null && counter.getCvDown() != null) {

                StringBuilder logMessageBuilder = new StringBuilder();  // Use StringBuilder for efficient string manipulation

                if (countingUp) {
                    for (long i = 0; i <= counter.getMaxCount(); i++) {
                        counter.getCountUpLock().lock();  // Acquire the lock for counting up
                        try {
                            while (!counter.isCountingUp()) {  // Wait until it's the turn for counting up
                                counter.getCvUp().await();
                            }

                            logMessageBuilder.setLength(0);  // Clear StringBuilder
                            logMessageBuilder.append("Thread ").append(getThreadIdString());  // Build log message
                            logMessageBuilder.append(" is counting up: ").append(i);
                            String logMessage = logMessageBuilder.toString();  // Convert StringBuilder to String
                            System.out.println(logMessage);  // Print the log message

                            counter.safeAddSubtract(1, true);  // Safely add to the counter

                            if (i == counter.getMaxCount()) {  // Check if reached the maximum count
                                counter.getCountDownLock().lock();  // Acquire the lock for counting down
                                try {
                                    counter.setCountingUp(false);  // Set counting direction to down
                                    counter.getCvDown().signal();  // Notify the countDown() thread to proceed
                                } finally {
                                    counter.getCountDownLock().unlock();  // Release the lock for counting down
                                }
                            }
                            counter.getCvUp().signal();  // Notify the countUp() thread to proceed
                        } finally {
                            counter.getCountUpLock().unlock();  // Release the lock for counting up
                        }
                    }
                } else {
                    for (long i = counter.getMaxCount(); i >= 0; --i) {
                        counter.getCountDownLock().lock();  // Acquire the lock for counting down
                        try {
                            while (counter.isCountingUp()) {  // Wait until it's the turn for counting down
                                counter.getCvDown().await();
                            }

                            logMessageBuilder.setLength(0);  // Clear StringBuilder
                            logMessageBuilder.append("Thread ").append(getThreadIdString());  // Build log message
                            logMessageBuilder.append(" is counting down: ").append(i);
                            String logMessage = logMessageBuilder.toString();  // Convert StringBuilder to String
                            System.out.println(logMessage);  // Print the log message

                            counter.safeAddSubtract(-1, false);  // Safely subtract from the counter

                            if (i == 0) {  // Check if reached the minimum count
                                counter.getCountUpLock().lock();  // Acquire the lock for counting up
                                try {
                                    counter.setCountingUp(true);  // Set counting direction to up
                                    counter.getCvUp().signal();  // Notify the countUp() thread to proceed
                                } finally {
                                    counter.getCountUpLock().unlock();  // Release the lock for counting up
                                }
                            }
                            counter.getCvDown().signal();  // Notify the countDown() thread to proceed
                        } finally {
                            counter.getCountDownLock().unlock();  // Release the lock for counting down
                        }
                    }
                }
            } else {
                throw new RuntimeException("Counter object or its associated locks/conditions are null.");
            }
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }
}