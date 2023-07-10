#include <iostream>
#include <thread>
#include <mutex>
#include <vector>
#include <queue>

std::mutex mtx;  //Create mutex to handle shared data
std::queue<int> sharedData;  //Data structure for shared data

//Data access function executed by each thread
void sharedDataAccess(int threadId)
{
    //For loop that iterates and allows access to data to threads
    for (int i = 0; i < 5; ++i)
    {
        //Mutex locks the data to synchonize access by threads
        std::lock_guard<std::mutex> lock(mtx);

        //Allows access to thread currently requesting only
        sharedData.push(threadId);

        //prints out thread id and data pushed to shared data
        std::cout << "Thread " << threadId << " pushed " << threadId << " to sharedData\n";
    }
}
int main()
{
    int numThreads;

    //Requests a number of threads from the user
    std::cout << "Enter the number of threads: ";
    std::cin >> numThreads;

    //Creates number of threads according to user input
    std::vector<std::thread> threads;
    for (int i = 0; i < numThreads; ++i)
    {
        //Runs each thread through sharedDataAccess
        threads.emplace_back(sharedDataAccess, i);
    }

    //Combines threads after completion
    for (auto& thread : threads)
    {
        thread.join();
    }

    //Prints out the finalized shared data the threads were entering
    std::cout << "Final sharedData: ";

    //While loop that pops each entry in shared data until it is empty
    while (!sharedData.empty())
    {
        std::cout << sharedData.front() << " ";
        sharedData.pop();
    }
    std::cout << std::endl;
    return 0;
}
