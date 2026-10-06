import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.regex.*;
import java.util.concurrent.*;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    Socket s;
    PrintStream write;
    Scanner read;
    ArrayList<Car> cars=new ArrayList();

    public ClientHandler(Socket server) throws IOException {
        s=server;
    }



    @Override
    public void run(){
        try{
            write=new PrintStream(s.getOutputStream());
            read=new Scanner(s.getInputStream());
            cars.add(new Car("bmw","a5",20000));
            cars.add(new Car("audi","q7",30000));
            cars.add(new Car("opel","astra",10000));
            cars.add(new Car("vw","corsa",15000));
        }catch(IOException e){
            e.printStackTrace();
        }
        while(true){


            String request = read.nextLine();


            if(request.matches("^\\S+ \\S+$")){
                write.println(brandModel(request));
            }
            else if(request.matches("^\\S+ \\S+ \\S+$")){


                write.println(priceForCar());


                String answer = read.nextLine().toUpperCase();


                if(answer.equals("YES")){
                    String[] product=request.split(" ");
                    String brand=product[0];
                    String model=product[1];
                    try {
                        double price = Double.parseDouble(product[2]); // Correctly parse the price as double
                        cars.add(new Car(brand, model, price)); // Assuming Car constructor takes double for price
                        write.println("Car sold!");
                        write.println(product[2]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price format. Please enter a valid number.");
                    }
                    //как клиента ще получи сумата от продажбата?
                }
            }
            else if(request.equals("EXIT")){
                break;
            }

        }




        try {
            s.close();
            read.close();
            write.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }




    public double brandModel(String request){
        String []product=request.split(" ");
        String brand=product[0];
        String model=product[1];
        for(int i=0;i <cars.size();i++){
            if(cars.get(i).getBrand().equals(brand)&&cars.get(i).getModel().equals(model)){
                return cars.get(i).getPrice();
            }
        }
        return 0.0;
    }

    public double priceForCar(){
        return 10000.0;
    }




}

