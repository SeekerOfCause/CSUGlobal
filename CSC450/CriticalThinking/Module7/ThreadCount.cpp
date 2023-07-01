#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable>

std::mutex mtx;
std::condition_variable cv;
bool isCountingUp = true;

void countUp()
{
    for (int i = 0; i <= 20; ++i)
    {
        std::unique_lock<std::mutex> lock(mtx);
        while (!isCountingUp)
            cv.wait(lock);

        std::cout << "Counting Up: " << i << std::endl;
        isCountingUp = (i == 20) ? false : true;
        cv.notify_all();
    }
}

void countDown()
{
    for (int i = 20; i >= 0; --i)
    {
        std::unique_lock<std::mutex> lock(mtx);
        while (isCountingUp)
            cv.wait(lock);

        std::cout << "Counting Down: " << i << std::endl;
        isCountingUp = (i == 0) ? true : false;
        cv.notify_all();
    }
}

int main()
{
    std::thread thread1(countUp);
    std::thread thread2(countDown);

    thread1.join();
    thread2.join();

    return 0;
}