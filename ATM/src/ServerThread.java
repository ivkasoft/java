import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;


public class ServerThread implements Runnable {
    LinkedList<Account> accounts;
    HashMap<Data, Object> sessionData;
    Socket s;
    Scanner reader;
    PrintStream writer;


    public ServerThread(Socket server) {
        //accounts = new LinkedList<>();
        s = server;
        accounts = new LinkedList<Account>();
        sessionData = new HashMap<Data, Object>();
    }


    @Override
    public void run() {
        try {
            writer = new PrintStream(s.getOutputStream());
            reader = new Scanner(s.getInputStream());
            ServerLogic();
            s.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void ProcessMessage(Commands c, String s) {
        switch (c) {
            case PIN:
                writer.println("Welcome to the ATM");
                break;
            case ASK_ACCOUNT_NUMBER:


        }
    }


    public void ServerLogic() {
        accounts.add(new Account(100, 1234, 1));
        accounts.add(new Account(100, 5678, 2));
        accounts.add(new Account(100, 4321, 3));
        accounts.add(new Account(100, 8765, 4));
        boolean flag = false;
        int accNum = 0;
        int pinNum = 0;


        while (flag == false) {
            accNum = reader.nextInt();
            reader.nextLine();
            for (Account account : accounts) {
                if (accNum == account.getAccountNumber()) {
                    writer.println("Successful identification A");
                    flag = true;
                }


            }
            if (flag == false) {
                writer.println("Account number not found. Try again");
            }


        }


        while (flag == true) {


            pinNum = reader.nextInt();
            reader.nextLine();


            for (Account account : accounts) {
                if (account.getPin() == pinNum && account.getAccountNumber() == accNum) {
                    writer.println("Successful identification P");
                    while (true) {
                        try {
                            String s = reader.nextLine();
                            Commands c = Commands.valueOf(s);
                            switch (c) {
                                case WITHDRAW:
                                    double withdraw = reader.nextDouble();
                                    reader.nextLine();
                                    account.withdraw(withdraw);
                                    writer.println("Withdrawal successful");
                                    break;
                                case DEPOSIT:
                                    double deposit = reader.nextDouble();
                                    reader.nextLine();
                                    account.deposit(deposit);
                                    writer.println("Deposit successful");
                                    break;
                                case GET_BALANCE:
                                    writer.println("Your balance is: " + account.getBalance());
                                    break;
                                case EXIT:
                                    writer.println("bye bye");
                                    return;
                                default:
                                    writer.println("Wrong command. Try again. ");
                                    break;
                            }
                        } catch (IllegalArgumentException e) {
                            writer.println("Wrong command. Try again. ");
                        }
                    }
                }
            }
            if (flag == true) {
                writer.println("Wrong PIN. Try again");
            }
        }


    }
}
