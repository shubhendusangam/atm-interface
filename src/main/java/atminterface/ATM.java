package atminterface;

import java.util.Scanner;

public class ATM {

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      Bank theBank = new Bank("SBI");

      // add a user, which also creates a savings account
      User aUser = theBank.addUser("Shubhendu", "Sangam","1234");

      // add a checking account for our user
      Account newAccount = new Account("Checking", aUser, theBank);
      aUser.addAccount(newAccount);
      theBank.addAccount(newAccount);
   }

}
