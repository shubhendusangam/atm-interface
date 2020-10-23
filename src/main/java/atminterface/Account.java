package atminterface;

import java.util.ArrayList;

import static java.lang.System.*;

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

   public String getSummaryLine() {
      // get the account's balance
      double balance = this.getBalance();

      // format the summary line, depending on the whether the balance is negative
      return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
      
   }

   public double getBalance() {
      double balance = 0;
      for (Transaction transaction : this.transactions) {
         balance += transaction.getAmount();
      }
      return balance;
   }

   public void printTransactionHistory() {
      out.printf("Transaction history for account %s \n", this.uuid);
      out.println();
      for (int t = this.transactions.size() -1 ; t >= 0; t--) {
         out.println(this.transactions.get(t).getSummaryLine());
      }
      out.println();
   }

   public void addTransaction(double amount, String memo) {

      // Create Transaction object and add it our list
      Transaction newTransaction = new Transaction(amount, memo, this);
      this.transactions.add(newTransaction);
   }
}
