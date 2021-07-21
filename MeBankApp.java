import javax.swing.JOptionPane;
import java.util.Scanner; 
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MeBankApp
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        int transRec = 0;
        System.out.println("-------Welcome To EamonBank-------");
        System.out.println("Please enter Savings Account amount: ");
        double savings = scanner.nextDouble();  // Read user input
        System.out.println("Please enter Checking Account amount: ");
        double checking = scanner.nextDouble();  // Read user input

        System.out.println("Savings Account (ACC912754) is: " + savings);
        System.out.println("Checking Account (ACC016438) is: " + checking);

        System.out.println("Would you like to transfer funds?(Y/N):  "); 
        String yes = "";
        yes = scanner.next().toLowerCase(); 
        if (yes != "y") 
        {
            transRec = transRec + 1;
            System.out.println("Select account to transfer funds from(1 = SAV, 2 = CHECK): ");      
        } 
        else
        {
            System.out.println("ERROR - Please call maintenance");
            System.exit(0); 
        }
            
        int from = scanner.nextInt();
        double funds;
        System.out.println("How much would you like to transfer?: ");   
        funds = scanner.nextDouble(); 
        String fromAccount = "";
        String toAccount = "";
        if (from == 1) 
        {
            System.out.println("transfering " + funds + " from SAVINGS to CHECKING.");
            fromAccount = "ACC912754";
            toAccount = "ACC016438";
            savings = savings - funds;
            checking = checking + funds;            
        } 
        else if (from == 2)
        {
            System.out.println("transfering " + funds + " from CHECKING to SAVINGS.");
            fromAccount = "ACC016438"; 
            toAccount = "ACC912754";  
            savings = savings + funds;
            checking = checking - funds;
        } 
        else 
        {
            System.out.println("ERROR - Please call maintenance");
            System.exit(0);           
        }      

        System.out.println("Transfer successful.");   

        System.out.println("Savings Account (ACC912754) is: " + savings);
        System.out.println("Checking Account (ACC016438) is: " + checking); 

        //String savingsNumber = "ACC912754";
        //String checkingNumber = "ACC016438";

        System.out.println("Continue?(Y/N): ");
        yes = scanner.next().toLowerCase(); 
        if (yes == "y") 
        {
            System.out.println("Error");      
        }
        else
        {
            System.out.println("Program will terminate.");              
        }        

        Date date = new Date();
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat dateForm = new SimpleDateFormat("dd:MM:yyyy");

        String transactionId = "TX100" + transRec;
        String fromAccountId = fromAccount;
        String toAccountId = toAccount; 
        String createdAtTime = time.format(date);
        String createdAt = dateForm.format(date);
        double amount = funds;
        String transactionType = "PAYMENT";
        String relatedTransaction = "";
        String filepath = "BankData.txt";

        //saveToCheck function

        saveToCsv(transactionId, fromAccountId, toAccountId, createdAtTime, createdAt, amount, transactionType, relatedTransaction, filepath);
    }

    public static void saveToCsv(String transactionId, String fromAccountId, 
    String toAccountId, String createdAtTime, String createdAt, double amount, String transactionType, 
    String relatedTransaction, String filepath) 
    {

        try 
        {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            //fromAccountId, toAccountId, createdAtTime, createdAt, amount, transactionType, relatedTransaction
            pw.println(transactionId);
            pw.println(fromAccountId);
            pw.println(toAccountId);
            pw.println(createdAtTime);
            pw.println(createdAt);
            pw.println(amount);
            pw.println(transactionType);
            pw.println(relatedTransaction);
            pw.flush();
            pw.close();
            JOptionPane.showMessageDialog(null, "Record saved");
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
    }
}