import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class ClientHandler extends HotelServer {


    public ClientHandler(){};


    public void manageClient(Socket socket ,ArrayList<Room> roomList) throws IOException {
        Thread t=new  Thread(()->{
            try {
                PrintStream printout=new PrintStream(socket.getOutputStream());
                Scanner serverScanner=new Scanner(socket.getInputStream());

                while(true){
                    int command=serverScanner.nextInt();
                    serverScanner.nextLine();

                    if(command==1){
                        int num=serverScanner.nextInt();
                        serverScanner.nextLine();
                        printout.println(reserveRoom(roomList,num));
                    }
                    else if(command==2){
                        int num=serverScanner.nextInt();
                        serverScanner.nextLine();
                        printout.println(checkRoom(roomList,num));
                    }
                    else if(command==3){
                        int num=serverScanner.nextInt();
                        serverScanner.nextLine();
                        printout.println(nullifyRoom(roomList,num));
                    }
                    else if(command==4){
                        printout.println("Goodbye");
                        break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });
        t.start();
    }
}

