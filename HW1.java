// Programmer: MichaelTran
// HW1 
// 9/6/2024
// Instructor: Dr.JanakaBalasooriya

package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HW1 extends Application {
    // Prices
    private static final double EGG_SANDWICH_PRICE = 7.99;
    private static final double BAGEL_PRICE = 2.50;
    private static final double POTATO_SALAD_PRICE = 4.49;
    private static final double CHICKEN_SANDWICH_PRICE = 9.99;
    private static final double COFFEE_PRICE = 1.99;
    private static final double GREEN_TEA_PRICE = 0.99;
    private static final double BLACK_TEA_PRICE = 1.25;
    private static final double ORANGE_JUICE_PRICE = 2.25;
    private static final double SALES_TAX = 0.07;

    // UI Elements
    private CheckBox eggSandwich;
    private CheckBox chickenSandwich;
    private CheckBox bagel;
    private CheckBox potatoSalad;
    private RadioButton coffee;
    private RadioButton greenTea;
    private RadioButton blackTea;
    private RadioButton orangeJuice;
    private TextArea bill;
    private ToggleGroup drinkGroup;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Joe's Deli");

        // Food selection
        eggSandwich = new CheckBox("Egg Sandwich ($7.99)");
        chickenSandwich = new CheckBox("Chicken Sandwich ($9.99)");
        bagel = new CheckBox("Bagel ($2.50)");
        potatoSalad = new CheckBox("Potato Salad ($4.49)");

        // Drink selection
        coffee = new RadioButton("Coffee ($1.99)");
        greenTea = new RadioButton("Green Tea ($0.99)");
        blackTea = new RadioButton("Black Tea ($1.25)");
        orangeJuice = new RadioButton("Orange Juice ($2.25)");
        drinkGroup = new ToggleGroup();
        coffee.setToggleGroup(drinkGroup);
        greenTea.setToggleGroup(drinkGroup);
        blackTea.setToggleGroup(drinkGroup);
        orangeJuice.setToggleGroup(drinkGroup);

        // Buttons
        Button orderButton = new Button("Order");
        Button cancelButton = new Button("Cancel");
        Button confirmButton = new Button("Confirm");

        // Bill display
        bill = new TextArea();
        bill.setEditable(false);
        bill.setPrefHeight(100);

        // Button actions
        orderButton.setOnAction(e -> updateBill());
     

        cancelButton.setOnAction(e -> cancelOrder());
        confirmButton.setOnAction(e -> confirmOrder());

        // Layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
            new Label("Eat:"),
            eggSandwich,
            chickenSandwich,
            bagel,
            potatoSalad,
            new Label("Drink:"),
            coffee,
            greenTea,
            blackTea,
            orangeJuice,
            orderButton,
            cancelButton,
            confirmButton,
            new Label("Bill:"),
            bill
        );

        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateBill() {
        double total = 0;
        StringBuilder billText = new StringBuilder("Order:\n");

        // Add selected food items to the bill
        if (eggSandwich.isSelected()) {
            total += EGG_SANDWICH_PRICE;
            billText.append("- Egg Sandwich: $").append(String.format("%.2f", EGG_SANDWICH_PRICE)).append("\n");
        }
        if (chickenSandwich.isSelected()) {
            total += CHICKEN_SANDWICH_PRICE;
            billText.append("- Chicken Sandwich: $").append(String.format("%.2f", CHICKEN_SANDWICH_PRICE)).append("\n");
        }
        if (bagel.isSelected()) {
            total += BAGEL_PRICE;
            billText.append("- Bagel: $").append(String.format("%.2f", BAGEL_PRICE)).append("\n");
        }
        if (potatoSalad.isSelected()) {
            total += POTATO_SALAD_PRICE;
            billText.append("- Potato Salad: $").append(String.format("%.2f", POTATO_SALAD_PRICE)).append("\n");
        }

        // Add selected drink to the bill
        RadioButton selectedDrink = (RadioButton) drinkGroup.getSelectedToggle();
        if (selectedDrink != null) {
            double drinkPrice = 0;
            switch (selectedDrink.getText()) {
                case "Coffee ($1.99)": drinkPrice = COFFEE_PRICE; break;
                case "Green Tea ($0.99)": drinkPrice = GREEN_TEA_PRICE; break;
                case "Black Tea ($1.25)": drinkPrice = BLACK_TEA_PRICE; break;
                case "Orange Juice ($2.25)": drinkPrice = ORANGE_JUICE_PRICE; break;
            }
            total += drinkPrice;
            billText.append("- ").append(selectedDrink.getText()).append("\n");
        }

        // Calculate tax and total
        double tax = total * SALES_TAX;
        double finalTotal = total + tax;
        billText.append("\nSubtotal: $").append(String.format("%.2f", total)).append("\n");
        billText.append("Tax (7%): $").append(String.format("%.2f", tax)).append("\n");
        billText.append("Total: $").append(String.format("%.2f", finalTotal));

        bill.setText(billText.toString());
    }

    private void cancelOrder() {
        eggSandwich.setSelected(false);
        chickenSandwich.setSelected(false);
        bagel.setSelected(false);
        potatoSalad.setSelected(false);
        drinkGroup.selectToggle(null);
        bill.clear();
    }

    private void confirmOrder() {
        // Confirm action: clear selections and keep the final bill displayed
        eggSandwich.setSelected(false);
        chickenSandwich.setSelected(false);
        bagel.setSelected(false);
        potatoSalad.setSelected(false);
        drinkGroup.selectToggle(null);
    }
}
