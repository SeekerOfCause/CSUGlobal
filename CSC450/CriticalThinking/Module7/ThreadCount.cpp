#include <iostream>
#include <thread>
#include <atomic>
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
    }
}

void countDown()
{
    for (int i = MAX_COUNT; i >= 0; --i)
    {
        {
            std::unique_lock<std::mutex> lock(mtx);
            cv.wait(lock, [] { return !isCountingUp.load(); });

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
    }
}

int main()
{
    std::thread threads[NUM_THREADS];

    threads[0] = std::thread(countUp);
    threads[1] = std::thread(countDown);

    for (int i = 0; i < NUM_THREADS; ++i)
    {
        threads[i].join();
    }

    return 0;
}
