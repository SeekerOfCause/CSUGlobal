package com.example.mymortgagecalculator;

import java.util.Locale;

public class Monthly {
    private final float payment;
    private final float interest;
    private final float principal;

    public Monthly(float payment, float interest, float principal) {
        this.payment = payment;
        this.interest = interest;
        this.principal = principal;
    }

    public float getPayment() {
        return this.payment;
    }

    public float getInterest() {
        return this.interest;
    }

    public float getPrincipal() {
        return this.principal;
    }


    public String monthlyString(int i) {
        String string;
        switch (i) {
            case 1:
                string = String.format(Locale.US, "$%.2f", this.payment);
                return string;
            case 2:
                string = String.format(Locale.US, "$%.2f", this.interest);
                return string;
            case 3:
                string = String.format(Locale.US, "$%.2f", this.principal);
                return string;
            default:
                string = String.format(Locale.US, "Payment: $%.2f, Interest: $%.2f, Principal: $%.2f", this.payment, this.interest, this.principal);
                return string;
        }
    }
}
