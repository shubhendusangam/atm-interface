package atminterface;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
   private String name;
   private ArrayList<User> users;
   private ArrayList<Account> accounts;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public ArrayList<User> getUsers() {
      return users;
   }

   public void setUsers(ArrayList<User> users) {
      this.users = users;
   }

   public ArrayList<Account> getAccounts() {
      return accounts;
   }

   public void setAccounts(ArrayList<Account> accounts) {
      this.accounts = accounts;
   }

   public String getNewUserUUID() {
      String uuid;
      Random random = new Random();
      int uuidLeng = 8;
      boolean nonUnique;

      // Continue looping until we get a unique ID
      do {
         // Generate the number
         uuid = "";
         for (int i = 0; i < uuidLeng; i++) {
            uuid += ((Integer)random.nextInt(10)).toString();
         }

         // check to make sure it's unique
         nonUnique = false;
         for (User user : this.users) {
            if (user.getUuid().equals(uuid)) {
               nonUnique = true;
               break;
            }
         }
      } while (nonUnique);
      return uuid;
   }

   public String getNewBankAccountUUID() {
      String uuid;
      Random random = new Random();
      int uuidLeng = 10;
      boolean nonUnique;

      // Continue looping until we get a unique ID
      do {
         // Generate the number
         uuid = "";
         for (int i = 0; i < uuidLeng; i++) {
            uuid += ((Integer)random.nextInt(10)).toString();
         }

         // check to make sure it's unique
         nonUnique = false;
         for (Account account : this.accounts) {
            if (account.getUuid().equals(uuid)) {
               nonUnique = true;
               break;
            }
         }
      } while (nonUnique);
      return uuid;
   }

   public void addAccount(Account anAccount) {
      this.accounts.add(anAccount);
   }

   public User addUser(String firstName, String lastName, String pin) {

      // Create a new user object and add it our list
      User newUser = new User(firstName, lastName, pin, this);
      this.users.add(newUser);

      // Create a saving bank account for user
      Account newAccount = new Account("Savings", newUser, this);
      newUser.addAccount(newAccount);
      this.addAccount(newAccount);
      return newUser;
   }

   public User userLogin(String userId, String pin) {
      // Search through list of users
      for (User user : this.users) {
         if (user.getUuid().equals(userId) && user.validatePin(pin)) {
            return user;
         }
      }
      return null;
   }
}
