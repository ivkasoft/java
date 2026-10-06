import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String []args) throws IOException{
        ServerSocket ss=new ServerSocket(5678);
        ArrayList<Book> books=new ArrayList();
        books.add(new Book(1, 2, "necronomicon", "az", "library4", true));
        books.add(new Book(3, 4, "kak da otgledame morkovi", "ti", "library4", false));
        books.add(new Book(5, 6, "narychnik za chistene na ushi", "toi", "library4", false));
        books.add(new Book(7, 8, "necronomicon", "tq", "library4", true));
        ClientHandler c=new ClientHandler();

        while(true){
            Socket s=ss.accept();
            c.manageClients(s,books);
        }

    }
}
