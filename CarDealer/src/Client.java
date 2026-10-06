import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String args[]) throws IOException {


        Socket s = new Socket("localhost", 2025);
        PrintStream printout = new PrintStream(s.getOutputStream());
        Scanner scan = new Scanner(System.in);
        Scanner serverScanner = new Scanner(s.getInputStream());


        double balance=0;


        while (true) {
            System.out.println("Enter car information:");
            //int num=scan.nextInt();
            String input = scan.nextLine();
            if(input.matches("^\\S+ \\S+$")) {
                printout.println(input);
                System.out.println(serverScanner.nextLine());
            }
            else if(input.matches("^\\S+ \\S+ \\S+$")) {
                printout.println(input);
                System.out.println(serverScanner.nextDouble());
                String result=serverScanner.nextLine();
                if(result.equals("Invalid price format. Please enter a valid number.")==false){
                    System.out.println("would you like to sell your car? ");
                    String answer=scan.nextLine();
                    if(answer.equals("yes")){
                        printout.println(answer);
                        System.out.println(serverScanner.nextLine());
                        balance+=Double.parseDouble(serverScanner.nextLine());
                        System.out.println("Your balance is: "+balance);
                    }
                }
            }
            else if(input.equals("exit")){
                break;
            }
            else{
                System.out.println("Invalid information");
            }


        }
        try{
            s.close();
            scan.close();
            serverScanner.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
}

