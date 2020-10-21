package atminterface;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
         System.err.println("error, caught NoSuchAlgorithmsException");
         e.printStackTrace();
         System.exit(1);
      }

      // Get a new Unique Universal ID for user
      this.uuid = theBank.getNewUserUUID();

      //create empty list of accounts
      this.accounts = new ArrayList<Account>();

      //print log message
      System.out.printf("New User %s, %s with ID %s created \n", firstName, lastName, this.uuid);
   }

   public void addAccount(Account anAccount) {
      this.accounts.add(anAccount);
   }

   public String getUuid() {
      return this.uuid;
   }

   public boolean validatePin(String aPin) {
      try {
         MessageDigest messageDigest = MessageDigest.getInstance("MD5");
         return MessageDigest.isEqual(messageDigest.digest(aPin.getBytes()), this.pinHash);
      } catch (NoSuchAlgorithmException e) {
         System.err.println("error, caught NoSuchAlgorithmsException");
         e.printStackTrace();
         System.exit(1);
      }
      return false;
   }
}
