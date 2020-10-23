package atminterface;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static java.lang.System.*;

public class User {

   private String firstName;
   private String lastName;
   private String uuid;
   private byte pinHash[];
   private ArrayList<Account> accounts;

   public User(String firstName, String lastName, String pin, Bank theBank) {

      // Set user name
      this.firstName = firstName;
      this.lastName = lastName;

      // Store the pin's MD5 hash , rather than original value , for security reason
      try {
         MessageDigest messageDigest = MessageDigest.getInstance("MD5");
         this.pinHash = messageDigest.digest(pin.getBytes());
      } catch (NoSuchAlgorithmException e) {
         err.println("error, caught NoSuchAlgorithmsException");
         e.printStackTrace();
         exit(1);
      }

      // Get a new Unique Universal ID for user
      this.uuid = theBank.getNewUserUUID();

      //create empty list of accounts
      this.accounts = new ArrayList<Account>();

      //print log message
      out.printf("New User %s, %s with ID %s created \n", firstName, lastName, this.uuid);
   }

   public void addAccount(Account anAccount) {
      this.accounts.add(anAccount);
   }

   public String getUuid() {
      return this.uuid;
   }

   public String getFirstName() {
      return firstName;
   }

   public boolean validatePin(String aPin) {
      try {
         MessageDigest messageDigest = MessageDigest.getInstance("MD5");
         return MessageDigest.isEqual(messageDigest.digest(aPin.getBytes()), this.pinHash);
      } catch (NoSuchAlgorithmException e) {
         err.println("error, caught NoSuchAlgorithmsException");
         e.printStackTrace();
         exit(1);
      }
      return false;
   }

   public void printAccountSummary() {
      out.printf("\n\n%s's accounts summary\n", this.getFirstName());
      for (int a = 0; a < this.accounts.size(); a++) {
         out.printf("%d) %s", a+1, this.accounts.get(a).getSummaryLine());
         out.println();
      }
      out.println();
   }

   public int numAccounts() {
      return this.accounts.size();
   }

   public void printAccountTransactionHistory(int accountIndex) {
      this.accounts.get(accountIndex).printTransactionHistory();
   }

   public double getAccountBalance(int fromAccountIndex) {
      return this.accounts.get(fromAccountIndex).getBalance();
   }

   public Object getAccountUUID(int accountIndex) {
      return this.accounts.get(accountIndex).getUuid();
   }

   public void addAccountTransaction(int accountIndex, double amount, String memo) {
      this.accounts.get(accountIndex).addTransaction(amount, memo);
   }
}
