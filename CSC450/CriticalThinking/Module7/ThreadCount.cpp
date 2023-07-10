#include <iostream>
#include <thread>
<<<<<<< HEAD
#include <mutex>
<<<<<<< HEAD
=======
#include <atomic>
>>>>>>> 0e6dd17 (continued mod2 CSC470)
#include <condition_variable>
#include <mutex>
#include <sstream>

const int MAX_COUNT = 20;
const int NUM_THREADS = 2;

std::atomic<bool> isCountingUp(true);
std::mutex mtx;
std::condition_variable cv;
std::mutex coutMutex; // Mutex to protect std::cout

void countUp()
{
    for (int i = 0; i <= MAX_COUNT; ++i)
    {
        {
            std::unique_lock<std::mutex> lock(mtx);
            cv.wait(lock, [] { return isCountingUp.load(); });

<<<<<<< HEAD
        std::cout << "Counting Up: " << i << std::endl;
        isCountingUp = (i == 20) ? false : true;
        cv.notify_all();
=======
#include <sstream>
#include <vector>

const int64_t MAX_COUNT = 20;
const int NUM_THREADS = 2;

std::atomic<int64_t> counter(0);
std::mutex mtx;
std::condition_variable cv_up, cv_down;
bool counting_up = true;
std::exception_ptr thread_exceptions[NUM_THREADS]; // Array to capture exceptions from threads

std::string getThreadIdString(const std::thread::id &tid)
{
    std::stringstream ss;
    ss << tid;
    return ss.str();
}

void safeAddSubtract(int64_t value)
{
    std::unique_lock<std::mutex> lock(mtx);
    counter += value;
    // If counter reaches MAX_COUNT or MIN_COUNT, switch roles
    if (counter >= MAX_COUNT + 1)
        counting_up = false;
}

void countUp()
{
    try
    {
        for (int64_t i = 0; i <= MAX_COUNT; ++i)
        {
            {
                std::unique_lock<std::mutex> lock(mtx);
                cv_up.wait(lock, [&]
                           { return counting_up; });

                std::string logMessage = "Thread " + getThreadIdString(std::this_thread::get_id());
                logMessage += " is counting up: " + std::to_string(i) + "\n";
                std::cout << logMessage;
            }
            safeAddSubtract(1);
            cv_down.notify_one(); // Notify the countDown() thread to proceed
        }
    }
    catch (const std::exception &ex)
    {
        // Capture the exception to be rethrown in the main thread
        thread_exceptions[0] = std::current_exception();
        cv_down.notify_one(); // Notify the countDown() thread to proceed (in case it's waiting)
>>>>>>> c87f92f (CSC475 Module 3)
=======
            std::stringstream ss;
            ss << "Thread " << std::this_thread::get_id();
            ss << " is counting up: " << i << std::endl;

            // Lock the cout stream to ensure sequential output
            std::lock_guard<std::mutex> coutLock(coutMutex);
            std::cout << ss.str();

            isCountingUp.store((i == MAX_COUNT) ? false : true);
        }
        cv.notify_one();
        std::this_thread::yield(); // Give a hint to the scheduler for fair scheduling
>>>>>>> 0e6dd17 (continued mod2 CSC470)
    }
}

void countDown()
{
<<<<<<< HEAD
<<<<<<< HEAD
    for (int i = 20; i >= 0; --i)
=======
    for (int i = MAX_COUNT; i >= 0; --i)
>>>>>>> 0e6dd17 (continued mod2 CSC470)
    {
        {
            std::unique_lock<std::mutex> lock(mtx);
            cv.wait(lock, [] { return !isCountingUp.load(); });

<<<<<<< HEAD
        std::cout << "Counting Down: " << i << std::endl;
        isCountingUp = (i == 0) ? true : false;
        cv.notify_all();
=======
    try
    {
        for (int64_t i = MAX_COUNT; i >= 0; --i)
        {
            {
                std::unique_lock<std::mutex> lock(mtx);
                cv_down.wait(lock, [&]
                             { return !counting_up; });

                std::string logMessage = "Thread " + getThreadIdString(std::this_thread::get_id());
                logMessage += " is counting down: " + std::to_string(i) + "\n";
                std::cout << logMessage;
            }
            safeAddSubtract(-1);
            cv_up.notify_one(); // Notify the countUp() thread to proceed
        }
    }
    catch (const std::exception &ex)
    {
        // Capture the exception to be rethrown in the main thread
        thread_exceptions[1] = std::current_exception();
        cv_up.notify_one(); // Notify the countUp() thread to proceed (in case it's waiting)
>>>>>>> c87f92f (CSC475 Module 3)
=======
            std::stringstream ss;
            ss << "Thread " << std::this_thread::get_id();
            ss << " is counting down: " << i << std::endl;

            // Lock the cout stream to ensure sequential output
            std::lock_guard<std::mutex> coutLock(coutMutex);
            std::cout << ss.str();

            isCountingUp.store((i == 0) ? true : false);
        }
        cv.notify_one();
        std::this_thread::yield(); // Give a hint to the scheduler for fair scheduling
>>>>>>> 0e6dd17 (continued mod2 CSC470)
    }
}

int main()
{
    std::thread threads[NUM_THREADS];

<<<<<<< HEAD
<<<<<<< HEAD
    thread1.join();
    thread2.join();
=======
    try
    {
        threads[0] = std::thread(countUp);
        threads[1] = std::thread(countDown);

        for (int i = 0; i < NUM_THREADS; ++i)
        {
            threads[i].join();
        }
    }
    catch (const std::exception &ex)
    {
        std::cerr << "Exception occurred in main thread: " << ex.what() << std::endl;
        // Optionally, handle
                // Optionally, handle the exception here or perform cleanup tasks
    }

    // Check for exceptions thrown in the worker threads
    for (int i = 0; i < NUM_THREADS; ++i)
    {
        if (thread_exceptions[i])
        {
            try
            {
                // Rethrow the exception in the main thread
                std::rethrow_exception(thread_exceptions[i]);
            }
            catch (const std::exception &ex)
            {
                std::cerr << "Exception occurred in thread " << i << ": " << ex.what() << std::endl;
                // Optionally, handle the exception here or perform cleanup tasks specific to the thread
            }
        }
    }
>>>>>>> c87f92f (CSC475 Module 3)
=======
    threads[0] = std::thread(countUp);
    threads[1] = std::thread(countDown);

    for (int i = 0; i < NUM_THREADS; ++i)
    {
        threads[i].join();
    }
>>>>>>> 0e6dd17 (continued mod2 CSC470)

    return 0;
}
