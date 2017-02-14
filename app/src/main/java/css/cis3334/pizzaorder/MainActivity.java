/**
 * Editor: Al Zenk
 * 02/13/2017
 * CIS 3334
 */

package css.cis3334.pizzaorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements updateViewInterface {

    //Declare variables to represent GUI elements.
    RadioButton rbSmall;
    RadioButton rbMedium;
    RadioButton rbLarge;
    CheckBox chkbxCheese;
    CheckBox chkbxDelivery;
    TextView txtTotal;
    TextView txtStatus;
    Spinner toppingSpinner;

    //Instance variable used to interface with PizzaOrder Class
    PizzaOrderInterface pizzaOrderSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RBs initialized
        rbSmall = (RadioButton) findViewById(R.id.radioButtonSmall);
        rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        rbLarge = (RadioButton) findViewById(R.id.radioButtonLarge);

        //chkbxs initialized
        chkbxCheese = (CheckBox) findViewById(R.id.checkBoxCheese);
        chkbxDelivery = (CheckBox) findViewById(R.id.checkBoxDeluvery);

        //TextViews initialized
        txtTotal = (TextView) findViewById(R.id.textViewTotal);
        txtStatus = (TextView) findViewById(R.id.textViewStatus);

        //Spinner initialized.
        toppingSpinner = (Spinner) findViewById(R.id.toppingSpinner);



        //POS initialized
        pizzaOrderSystem = new PizzaOrder(this);


    }

    @Override
    public void updateView(String orderStatus) {
        txtStatus.setText("Order Status: " + orderStatus);
    }




    public void onClickOrder(View view) {

        //Variables that will be passed to OrderPizza. Determined by conditional statements below.
        String size = "";
        boolean extraCheese = false;
        String topping = "";
        boolean delivery = false;

        //Determine if chkbxDelivery is checked.
        if(chkbxDelivery.isChecked())
        {
            delivery = true;
        }

        //set delivery(T/F) by  passing status of delivery CB.
        pizzaOrderSystem.setDelivery(delivery);

        //Determine size ordered by checking isChecked property of each radio button
        if(rbSmall.isChecked())
        {
            size = "SMALL";
        }
        if(rbMedium.isChecked())
        {
            size = "MEDIUM";
        }
        if(rbLarge.isChecked())
        {
            size = "LARGE";
        }

        //Set boolean value extraCheese to true if the extra cheese cb is checked.
        if(chkbxCheese.isChecked())
        {
            extraCheese = true;
        }

        //Determine the topping the user has selected and set the topping variable to said topping.
        topping = toppingSpinner.getSelectedItem().toString();



        String orderDescription = pizzaOrderSystem.OrderPizza(topping,size, extraCheese);
        //display a pop up message for a long period of time
        Toast.makeText(getApplicationContext(), "You have ordered a "+orderDescription , Toast.LENGTH_LONG).show();
        txtTotal.setText("Total Due: " + pizzaOrderSystem.getTotalBill().toString());
    }
}
