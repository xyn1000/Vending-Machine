# Vending Machine (Lite Snacks)
This is a Vending Machine (Lite Snacks) Software developed by R18B_Group2. The project 
is written in Java and adheres to the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).

## Requirement
This project requires `Java 11.0.8` or above and `Gradle 5.6.1` or above.
## Run
Run compiled code with `java -jar VendingMachine.jar`

Run source code with `gradle run`.

Run built-in tests, use `gradle build` or `gradle test`.

### Log in
If you have an account, type in the Username and Password and click on `Login` to log in to the system.

If you don't have previous account, you can either choose `Register` or `Guest Login`. 
`Guest Login` lets the user log in as an anonymous customer.

### Register
Enter the user name and the password to create a new account.
The user name must be unique.

## User Type
There are 4 types of user with different interfaces and functionalities.
* Customer: Shopping.
* Seller: Fill/Modify the commodity details.
* Cashier: Fill/Modify the money.
* Owner: Add/Remove Seller or Cashier Or Owner users. Fill/Modify the commodity details. Fill/Modify the money.

## Customer
### Main Panel
After logging in, a reminder box will show the recent purchase for old customers. Then you will see the main panel. All the commodities are listed below the 
`Commodity Details`. You can click on a different category to filter the commodity. There are
4 categories: Drinks, Chocolate, Lollies and Chips.

To add a commodity to your shopping cart, double-click on the number of `Quantity` column then
enter the quantity you want. After entering, press key `Enter` to save the input. Finally, press 
button `ADD TO CART` to add the commodity to the cart.


### Shopping Cart
To check your shopping cart, click on the label `Shopping Cart` in the top left corner of
the window. Then you will see all the commodities in your shopping cart line by line. To remove
a commodity from your shopping cart, press the button `Delete`. You can see the amount of money
in the right bottom corner of the window. There are 3 buttons on the right: `Pay by Cash`, 
`Pay by Card` and `clear`.


### Payment
There are two ways to pay, all limited in 120 seconds.
* `Pay by Cash`: Enter the number of notes/ coins in the input box and press `Confirm` to pay.
If there is enough money to pay, a box will pop out to show the change. Otherwise, the payment will be canceled.

* `Pay by Card`: Enter the name and the number of the card and press `Confirm`. If it is a valid
card, the payment will be successful. Otherwise, the payment will be canceled. If you log in an account
to purchase and pay successfully, the card details will be filled automatically in the next transaction.


## Seller
### Commodity Manage
To modify the information of a commodity, press button `Modify` of the specific row of the item and enter
the information. Then press button `Confirm` to save. 

To delete a commodity, press button `Delete` of the specific row of the item.


### General Manage
Click on the label `General Manage` in the top left corner of the window.

To add a new item, enter the information in the left of the window and press `Save`.

To generate the reports, click on the buttons on the right: `Current Items List` and `Commodity Summary`. 
Press these buttons to form new reports.

To see the reports, go to the root directory of the project and choose `build` folder. All the generated
reports are in the `reports` folder.





## Cashier
### Money Manage
To change the quantity of a note/coin, double-click on the number of `Quantity` column then
enter the quantity. After entering, press key `Enter` to save the input. Finally, press 
button `Save` to change the amount.

### Cashier Summary
To generate the reports, click on the label `Cashier Summary` in the top left corner of
the window. Then you will see 2 buttons: `Current Change List` and `Transaction Summary`. 
Press these buttons to form new reports.

To see the reports, go to the root directory of the project and choose `build` folder. All the generated
reports are in the `reports` folder.





## Owner
### Commodity Manage
To modify the information of a commodity, press button `Modify` of the specific row of the item and enter
the information. Then press button `Confirm` to save. 

To delete a commodity, press button `Delete` of the specific row of the item.

### Money Manage
To change the quantity of a note/coin, double-click on the number of `Quantity` column then
enter the quantity. After entering, press key `Enter` to save the input. Finally, press 
button `Save` to change the amount.

### User Manage
To delete a user, press button `Delete` of the row of the user you want to delete.

### Others
To add a new user, fill in the information in the left of the window and press `Save`.

To generate the reports, click on the buttons on the right.

To see those reports, go to the root directory of the project and choose `build` folder. All the generated
reports are in the `reports` folder.

