import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler extends Server{
    Scanner read;
    PrintStream write;
    public ClientHandler(){};
    public void manageClients(Socket s, ArrayList<Book> books) throws IOException {
        Thread t=new Thread(()->{
            ArrayList<Book> list=new ArrayList();
            try{
                read=new Scanner(s.getInputStream());
                write=new PrintStream(s.getOutputStream());
            }catch(IOException e){
                e.printStackTrace();
            }

            while(true){
                int req=read.nextInt();
                read.nextLine();

                if(req==1){
                    boolean available=false;
                    String buildString="";
                    String title=read.nextLine();
                    for(int i=0;i<books.size();i++){
                        if(books.get(i).getTitle().equals(title)&&books.get(i).getAvailable()==true){
                            buildString+=books.get(i).getLibrary();
                            available=true;
                        }
                    }
                    if(!available){
                        write.println("Book not found");
                    }
                    else{
                        write.println(buildString);
                    }
                }
                else if(req==2){
                    int id=read.nextInt();
                    boolean flag=false;
                    read.nextLine();
                    for(int i=0;i<books.size();i++) {
                        if (books.get(i).getId() == id) {
                            for (int j = 0; j < list.size(); j++) {
                                if (list.get(i).getId() == id) {
                                    flag = true;
                                    write.println("Book already added");
                                    break;
                                }
                            }
                        }
                        if (flag == false) {
                            list.add(books.get(i));
                            write.println("Book added");
                        }
                    }
                }
                else if(req==3){
                    String library=read.nextLine();
                    boolean exist=false;
                    String printString="";
                    for(int i=0;i<books.size();i++){
                        if (books.get(i).getLibrary().equals(library)) {
                            printString+=books.get(i);
                            exist=true;
                        }
                    }
                    if(exist==false){
                        write.println("No books in this library or library does not exist");
                    }
                    else{
                        write.println(printString);
                    }

                }
                else if(req==4){
                    write.println("bye bye");
                    break;
                }
                else{
                    write.println("Wrong input");
                }

            }

        });
        t.start();
    }
}
