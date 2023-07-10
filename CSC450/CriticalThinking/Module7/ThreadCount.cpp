#include <iostream>
#include <thread>
#include <atomic>
#include <condition_variable>
#include <mutex>
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
    }
}

void countDown()
{
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
    }
}

int main()
{
    std::thread threads[NUM_THREADS];

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

    return 0;
}
