package CriticalThinking.Module8;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountingWithThreads {
    public static void main(String[] args) {
        // Create a Counter object
        Counter counter = new Counter();

        // Create two threads, one for counting up and one for counting down
        Thread countUpThread = new Thread(new CounterThread(counter, true));
        Thread countDownThread = new Thread(new CounterThread(counter, false));

        try {
            // Start the threads
            countUpThread.start();
            countDownThread.start();

            // Wait for both threads to complete
            countUpThread.join();
            countDownThread.join();
        } catch (Throwable ex) {
            System.err.println("Exception occurred in main thread: " + ex.getMessage());
            // Optionally, handle the exception here or perform cleanup tasks
        }
    }
}