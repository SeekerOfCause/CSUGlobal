#include <iostream>
#include <iomanip>

int main() {
    double standardHours, rateOfPay, overtimeHours;
    
    std::cout << "Enter the amount of hours you've worked: ";
    std::cin >> standardHours;
    
    std::cout << "Enter your pay rate: ";
    std::cin >> rateOfPay;
    
    std::cout << "Enter your overtime hours (if applicable): ";
    std::cin >> overtimeHours;
    
    double standardPay = standardHours * rateOfPay;
    double overtimePay = overtimeHours * rateOfPay * 1.5;
    double totalPay = standardPay + overtimePay;
    
    
    std::cout << std::fixed << std::setprecision(2);
    
    std::cout << "" << std::endl;
    std::cout << "Payroll Details" << std::endl;
    std::cout << "" << std::endl;
    std::cout << std::setw(15) << "Standard Hours:" << std::setw(10) << standardHours << std::endl;
    std::cout << std::setw(15) << "Rate of Pay:" << std::setw(10) << rateOfPay << std::endl;
    std::cout << std::setw(15) << "Overtime Hours:" << std::setw(10) << overtimeHours << std::endl;
    std::cout << std::setw(15) << "" << std::endl;
    std::cout << std::setw(15) << "Standard Pay:" << std::setw(10) << standardPay << std::endl;
    std::cout << std::setw(15) << "Overtime Pay:" << std::setw(10) << overtimePay << std::endl;
    std::cout << std::setw(15) << "" << std::endl;
    std::cout << std::setw(15) << "Total Pay:" << std::setw(10) << totalPay << std::endl;
    std::cout << "-" << std::endl;
    
    return 0;
}