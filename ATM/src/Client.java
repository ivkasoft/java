import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Client {
    public static void main(String args[]) throws IOException {
        //Синтаксис за сокет и инструметни за работа с него, важно е да се помни
        Socket s = new Socket("localhost", 5678);
        PrintStream printout = new PrintStream(s.getOutputStream());
        Scanner scan = new Scanner(System.in);
        Scanner serverScanner = new Scanner(s.getInputStream());
        System.out.println("Enter account number:");
        while (true) {
            int accNum = scan.nextInt();
            scan.nextLine();
            printout.println(accNum);
            String response = serverScanner.nextLine();
            System.out.println(response);
            if (response.equals("Successful identification A")) {
                break;
            }
        }
        System.out.println("Enter PIN:");
        while (true) {
            int pin = scan.nextInt();
            scan.nextLine();
            printout.println(pin);
            String response = serverScanner.nextLine();
            System.out.println(response);
            if (response.equals("Successful identification P")) {
                break;
            }
        }
        while (true) {
            System.out.println("Enter command:");
            String command = scan.nextLine();
            printout.println(command);
            if (command.equals("DEPOSIT") || command.equals("WITHDRAW")) {
                double amount = scan.nextDouble();
                scan.nextLine();
                printout.println(amount);
            }
            String result = serverScanner.nextLine();
            System.out.println(result);
            if (result.equals("bye bye")) {
                break;
            }
        }
        try {
            s.close();
            scan.close();
            serverScanner.close();
            printout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
