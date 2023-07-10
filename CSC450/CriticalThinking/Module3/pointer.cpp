#include <iostream>
#include <limits>

int main() {
    int value1, value2, value3;

    std::cout << "Enter three integer values: ";
    std::cin >> value1 >> value2 >> value3;

    int* ptr1 = new int(value1);
    int* ptr2 = new int(value2);
    int* ptr3 = new int(value3);
    int limit = std::numeric_limits<int>::min();
    int uplimit = std::numeric_limits<int>::max();

    std::cout << "Variable values: " << value1 << ", " << value2 << ", " << value3 << std::endl;
    std::cout << "Pointer values: " << *ptr1 << ", " << *ptr2 << ", " << *ptr3 << std::endl;
    std::cout << "Pointer Location: " << &value1 << ", " << &value2 << ", " << &value3 << std::endl;
    std::cout << limit << std::endl;
    std::cout << uplimit << std::endl;

    delete ptr1;
    delete ptr2;
    delete ptr3;

    return 0;
}
