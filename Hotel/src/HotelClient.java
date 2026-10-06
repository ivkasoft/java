import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class HotelClient {
    public static void main(String args[]) throws IOException {


        Socket s = new Socket("localhost", 1212);
        PrintStream printout = new PrintStream(s.getOutputStream());
        Scanner scan = new Scanner(System.in);
        Scanner serverScanner = new Scanner(s.getInputStream());


        while (true) {
            System.out.println("Enter command: | 1 to reserve | 2 to check | 3 to delete | 4 to exit |");
            int number = scan.nextInt();
            if(number==1 || number==2 || number==3){
                int roomNumber=scan.nextInt();
                scan.nextLine();
                printout.println(number);
                printout.println(roomNumber);
                System.out.println(serverScanner.nextLine());
            }
            else if(number==4){
                printout.println(number);
                System.out.println(serverScanner.nextLine());
                break;
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

