import models.Monitor;
import threads.Consumer;
import threads.Product;

public class App {
    public static void main(String[] args) throws Exception {
        Monitor monitor =  new Monitor();

        Thread prod = new Thread(new Product(monitor));
        Thread cons = new Thread(new Consumer(monitor));

        prod.start();
        cons.start();

        try{
            prod.join();
            cons.join();
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
