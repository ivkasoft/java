import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class HotelServer {


    public synchronized static String reserveRoom(ArrayList<Room> list, int number) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNumber() == number) {
                if (list.get(i).getAvailable() == false) {
                    return "Room already reserved";
                } else {
                    list.get(i).setAvailable(false);
                    return "Room successfully reserved";
                }
            }
        }
        return "Room not found";
    }


    public synchronized static String checkRoom(ArrayList<Room> list, int number) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNumber() == number) {
                String s = "Room number: " + list.get(i).getNumber() + " Room type: " + list.get(i).getType() + " Room price: " + list.get(i).getPrice() + " Room availability: " + list.get(i).getAvailable();
                return s;
            }
        }
        return "Room not found";
    }


    public synchronized static String nullifyRoom(ArrayList<Room> list, int number) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNumber() == number) {
                list.remove(list.get(i));
                return "Room removed";
            }
        }
        return "Room not found";
    }


    public static void main(String args[]) throws IOException {
        ServerSocket ss = new ServerSocket(1212);
        ArrayList<Room> roomList = new ArrayList();
        ClientHandler c = new ClientHandler();
        roomList.add(new Room(1, "single", 100, true));
        roomList.add(new Room(2, "double", 200, true));
        roomList.add(new Room(3, "single", 300, true));
        roomList.add(new Room(4, "double", 400, true));


        PrintStream reader;
        Scanner writer;


        while (true) {
            Socket s = ss.accept();
            c.manageClient(s, roomList);
        }
    }
}

