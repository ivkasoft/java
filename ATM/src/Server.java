import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    public static void main(String[] args) throws IOException {

        //Въвеждане на някакви книги в свързан списък, структурата ми по избор
        //Отваряне на сокет на сървъра
        ServerSocket ss = new ServerSocket(5678);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        while (true) {
            //While цикъл който за всеки новоприсъединил се клиент създава нова нишка, създаване на инструменти за работа с сървъра
            Socket socket = ss.accept();
            //System.out.println("Socket accepted");
            executor.execute(new ServerThread(socket));
            //System.out.println(s.accounts);
        }
    }
}
