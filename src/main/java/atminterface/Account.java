package atminterface;

import java.util.ArrayList;

public class Account {

   private String name;
   private String uuid;
   private User holder;
   private ArrayList<Transaction> transactions;

   public Account(String name, User holder, Bank theBank) {
      //Set the account name and holder
      this.name = name;
      this.holder = holder;

      // Get new Account UUID
      this.uuid = theBank.getNewBankAccountUUID();

      // initiate transaction
      this.transactions = new ArrayList<Transaction>();
   }

   public String getUuid() {
      return this.uuid;
   }
}
