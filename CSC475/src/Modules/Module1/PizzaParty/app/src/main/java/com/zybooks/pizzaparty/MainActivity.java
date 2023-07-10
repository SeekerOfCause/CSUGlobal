package com.zybooks.pizzaparty;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText numPeople;
    private TextView numPizzas;
    private RadioGroup howHungry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numPeople = findViewById(R.id.numPeople);
        howHungry = findViewById(R.id.howHungry);
        numPizzas = findViewById(R.id.numReqPizzas);

    }

    public void submitHunger (View view) {
        String peopleAttending = numPeople.getText().toString();
        int numAttending = Integer.parseInt(peopleAttending);
        int index = howHungry.indexOfChild(findViewById(howHungry.getCheckedRadioButtonId()));

        int slicesPerPerson;
        switch (index) {
            case 0:
                slicesPerPerson = 2;
                break;
            case 1:
                slicesPerPerson = 3;
                break;
            case 2:
                slicesPerPerson = 4;
                break;
            default:
                slicesPerPerson = 1;
        }

        int slicesPerPizza = 8;
        System.out.println(index);
        System.out.println(slicesPerPerson);
        int slices = (int) Math.ceil(numAttending * slicesPerPerson / (double) slicesPerPizza);

        String response = "You will require " + slices + " pizzas my friend...";
        numPizzas.setText(response);
    }
}