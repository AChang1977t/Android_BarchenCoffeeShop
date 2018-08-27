package com.example.android.barchencoffeeshop;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantityA = 0;
    int quantityL = 0;
    int quantityC = 0;
    int quantityM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button of America coffee is clicked.
     */
    public void incrementA(View view) {
        if (quantityA == 100) {
            return;
        }
        quantityA++;
        displayQuantityA(quantityA);
    }

    /**
     * This method is called when the minus button of America coffee  is clicked.
     */
    public void decrementA(View view) {
        if (quantityA == 0) {
            return;
        }
        quantityA--;
        displayQuantityA(quantityA);
    }

    /**
     * This method is called when the plus button of Caffee Latte is clicked.
     */
    public void incrementL(View view) {
        if (quantityL == 100) {
            return;
        }
        quantityL++;
        displayQuantityL(quantityL);
    }

    /**
     * This method is called when the minus button of Caffee Latte is clicked.
     */
    public void decrementL(View view) {
        if (quantityL == 0) {
            return;
        }
        quantityL--;
        displayQuantityL(quantityL);
    }

    /**
     * This method is called when the plus button of Cappuccino is clicked.
     */
    public void incrementC(View view) {
        if (quantityC == 100) {
            return;
        }
        quantityC++;
        displayQuantityC(quantityC);
    }

    /**
     * This method is called when the minus button of Cappuccino is clicked.
     */
    public void decrementC(View view) {
        if (quantityC == 0) {
            return;
        }
        quantityC--;
        displayQuantityC(quantityC);
    }

    /**
     * This method is called when the plus button of Caramel Macchiato is clicked.
     */
    public void incrementM(View view) {
        if (quantityM == 100) {
            return;
        }
        quantityM++;
        displayQuantityM(quantityM);
    }

    /**
     * This method is called when the minus button of Caramel Macchiato is clicked.
     */
    public void decrementM(View view) {
        if (quantityM == 0) {
            return;
        }
        quantityM--;
        displayQuantityM(quantityM);
    }

    private int quantity() {
        return quantityM + quantityC + quantityL + quantityA;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText nameField = (EditText) findViewById(R.id.add_name_view);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedcream_checkbox);
        boolean hasWhippedCreamCheckBox = whippedCreamCheckBox.isChecked();

        CheckBox cholcolateCheckBox = (CheckBox) findViewById(R.id.addchocolate_checkbox);
        boolean hasChocolateCheckBox = cholcolateCheckBox.isChecked();

        CheckBox cinnamonCheckBox = (CheckBox) findViewById(R.id.cinnamon_checkbox);
        boolean hasCinnamonCheckBox = cinnamonCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCreamCheckBox, hasChocolateCheckBox, hasCinnamonCheckBox);
        int quantity = quantity();

        String priceMessage = createOderSummary(name, price, quantity, hasWhippedCreamCheckBox, hasChocolateCheckBox, hasCinnamonCheckBox);
 //       displayMessage(priceMessage);

        /**
              * Send Email to order
              */
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Barchen Coffee Shop order for" + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    /**
     * Calculates the price A. Price L, Price C, Price M,
     * add WhippedCream + 1; add Chocolate +2;
     * return to total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate, boolean addCinnamon) {
        int basePriceA = 5;
        int basePriceL = 6;
        int basePriceC = 7;
        int basePriceM = 8;
        if (addWhippedCream) {
            basePriceA = basePriceA + 1;
        }
        if (addChocolate) {
            basePriceA = basePriceA + 2;
        }
        if (addCinnamon) {
            basePriceL = basePriceL + 1;
        }
        return quantityA * basePriceA + quantityL * basePriceL + quantityC * basePriceC + quantityM * basePriceM;
    }

    /**
     * This method displays Summary of order
     */

    private String createOderSummary(String addName, int price, int quantity, boolean addWhippedCream, boolean addChocolate, boolean addCinnamon) {
        String priceMessage = "name: " + addName;
        priceMessage += "\nCaffee Americano: " + quantityA;
        priceMessage += "\nadd Whipped cream? " + addWhippedCream;
        priceMessage += "\nadd Chocolate? " + addChocolate;
        priceMessage += "\nCaffee Latte: " + quantityL;
        priceMessage += "\nadd Cinnamon? " + addCinnamon;
        priceMessage += "\nCappuccino: " + quantityC;
        priceMessage += "\nCaramel Macchiato: " + quantityM;
        priceMessage += "\n-----------------------------------------------------";
        priceMessage += "\nTotal Quantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";

        return priceMessage;
    }

    /**
     * This method displays the given quanity A value on the screen
     */
    private void displayQuantityA(int numberA) {
        TextView quantityATextView = (TextView) findViewById(R.id.quantity_A_text_view);
        quantityATextView.setText("" + numberA);
    }

    /**
     * This method displays the given quanity L value on the screen
     */
    private void displayQuantityL(int numberL) {
        TextView quantityLTextView = (TextView) findViewById(R.id.quantity_L_text_view);
        quantityLTextView.setText("" + numberL);
    }

    /**
     * This method displays the given quanity C value on the screen
     */
    private void displayQuantityC(int numberC) {
        TextView quantityCTextView = (TextView) findViewById(R.id.quantity_C_text_view);
        quantityCTextView.setText("" + numberC);
    }

    /**
     * This method displays the given quanity M value on the screen
     */
    private void displayQuantityM(int numberM) {
        TextView quantityMTextView = (TextView) findViewById(R.id.quantity_M_text_view);
        quantityMTextView.setText("" + numberM);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.Order_Summary_Text_View);
        orderSummaryTextView.setText(message);
    }
}
