import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;


public class Server {
    public static void main() throws IOException{
        ServerSocket ss=new ServerSocket(2025);
        ExecutorService executor=Executors.newFixedThreadPool(3);

        while(true){
            Socket s=ss.accept();
            executor.execute(new ClientHandler(s));
        }
    }
}
