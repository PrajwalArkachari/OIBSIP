import java.util.*;

public class Main {
    public static String id = "Prajwal";
    public static int pin = 1234;
    public static boolean flag=true;
    public static int c=3;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        login();

        if(flag==true){
            menu();
        }
    }

    public static void login() {
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("ATM InterPhase");
        System.out.println();
        System.out.print("Enter Your User ID: ");
        String eid = sc.nextLine();

        if (id.compareTo(eid)!=0) {
            System.out.println();
            System.out.println("ID does not exists!\nYou have "+(--c)+" chances left");
            if(c==0){
                System.exit(0);
            }
            System.out.println("Press 1 to retry");
            int ip = sc.nextInt();
            if (ip == 1) {
                login();
            }
        }

        System.out.print("Enter PIN: ");
        int epin = sc.nextInt();
        if (epin != pin) {
            flag=false;
            System.out.println();
            System.out.println("Incorrect Pin!\nYou have "+(--c)+" chances left");
            if(c==0){
                System.exit(0);
            }
            System.out.println("Press 1 to retry");
            int ipin = sc.nextInt();
            if (ipin == 1) {
                login();
            }
        }

    }


    public static void menu() {
        int balance=0;
        int value,temp;
        Scanner sc = new Scanner(System.in);
        Transaction_history th = new Transaction_history();
        Withdraw w= new Withdraw();
        Deposit d= new Deposit();
        Transfer t= new Transfer();

        do{
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("Menu");
            System.out.println(" 1. Deposit\n 2. Withdraw\n 3. Transfer\n 4. Transaction History\n 5.Check balance\n 6. Quit");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice= sc.nextInt();
            System.out.println();

            switch (choice){
                case 1: balance = d.adddeposit(balance);
                    break;

                case 2: temp = w.withdraw(balance);
                    if(temp!=-1){
                        balance = temp;
                        System.out.println("Available Balance: "+balance);
                    } else {
                        System.out.println("Withdrawl Failed!");
                    }
                    break;

                case 3: temp = t.transfer(balance);
                    if(temp!=-1){
                        balance = temp;
                        System.out.println("Available Balance: "+balance);
                    } else {
                        System.out.println("Transaction Failed!");
                    }
                    break;

                case 4: if((th.id).compareTo("Transfer")==0){
                    t.transferhistory();
                } else {
                    th.display();
                }
                    break;

                case 5: System.out.println("Account Balance: "+balance);
                    break;
                case 6: System.exit(0);

                default:System.out.println("Invalid Choice");

            }

            System.out.println();
            System.out.println("Press 0 to continue");
            value = sc.nextInt();
        }while(value==0);
        System.out.println();
        System.out.println("Thank you for using ATM services");
        System.out.println("Have a nice day!");
    }
}



class Transaction_history{
    public static String id;
    public static int amt;
    public static int bal;
    public void storehistory(String name, int amount, int balance){
        id=name;
        amt=amount;
        bal=balance;
    }
    public void display(){
        System.out.println("Last transaction details: ");
        System.out.println("Particulars: "+id+"  "+"Amount: "+amt+"  "+"Account Balance: "+bal);
    }

}



class Withdraw{

    public int withdraw(int bal) {
        int nbal;
        Transaction_history t=new Transaction_history();
        String s= "Withdraw";
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter the amount for withdrawl: ");
        int amt=sc.nextInt();
        if(bal==0 || bal<amt){
            System.out.println();
            System.out.println("Insufficient Balance");
            nbal= -1;
        } else {
            System.out.println();
            System.out.println("Please collect your money");
            nbal= bal-amt;
        }
        t.storehistory(s, amt, nbal);
        return nbal ;
    }
}


class Deposit{

    public int adddeposit(int bal) {
        int nbal=0;
        Scanner sc = new Scanner(System.in);
        Transaction_history t=new Transaction_history();
        String s= "Deposit";
        System.out.print("Enter the deposit amount: ");
        int deposit = sc.nextInt();
        nbal= bal+deposit;

        System.out.println();
        System.out.println(deposit+" added sucessfully");
        System.out.println("Available balance: "+nbal);
        System.out.println();
        t.storehistory(s, deposit, nbal);
        return nbal;
    }

}

class Transfer{
    public static int tramt;
    public static String uid;
    public static int uno;
    public static int nbal;

    public int transfer(int bal) {
        Scanner sc = new Scanner(System.in);
        Transaction_history t=new Transaction_history();
        String s= "Transfer";
        System.out.print("Enter the User ID: ");
        uid =sc.next();
        System.out.print("Account number: ");
        uno =sc.nextInt();
        System.out.print("Enter transfer amount: ");
        tramt=sc.nextInt();

        if(tramt>bal){
            nbal= -1;
        } else {
            System.out.println("Type 'OK' to verify transaction");
            String vip = sc.next();
            if(vip.equalsIgnoreCase("OK")){
                System.out.println("Transaction Successful");
                nbal = bal-tramt;
            }
        }
        t.storehistory(s,0 ,0 );
        return nbal;
    }

    public void transferhistory() {
        System.out.println("Last transaction details");
        System.out.println(" Credited To: "+ uid+"\n"+" Account No.: "+uno+"\n"+" Amount Transferred: "+tramt+"\n"+" Balance: "+nbal);

    }
}
