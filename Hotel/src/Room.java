public class Room {
    int number;
    String type;
    double price;
    boolean available;


    /*public void setNumber(int number) {
        this.number = number;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setPrice(double price) {
        this.price = price;
    }*/


    public void setAvailable(boolean available) {
        this.available = available;
    }


    public int getNumber() {
        return number;
    }


    public String getType() {
        return type;
    }


    public double getPrice() {
        return price;
    }


    public boolean getAvailable() {
        return available;
    }


    public Room(int number, String type, double price, boolean available){
        this.number=number;
        this.type=type;
        this.price=price;
        this.available=available;
    }
}

