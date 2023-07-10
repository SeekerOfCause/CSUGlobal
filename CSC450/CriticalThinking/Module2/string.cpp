#include <iostream>
#include <string>
#include <algorithm>

int main() {
    std::string input;

    for (int i = 0; i < 3; ++i) {
        std::cout << "Enter a string: ";
        std::getline(std::cin, input);

        // Reverse the string
        std::reverse(input.begin(), input.end());

        // Print the reversed string
        std::cout << "Reversed string: " << input << std::endl;
    }

    return 0;
}
