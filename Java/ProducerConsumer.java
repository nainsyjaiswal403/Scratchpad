
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumer {
    public static void main(String[] args) {
        EvenOddThread obj = new EvenOddThread(true, 1);
        
        Thread evenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                obj.evenPrint();
            }
        });
        Thread oddThread = new Thread(new Runnable() {
            @Override
            public void run() {
                obj.oddPrint();
            }
        });
        
        evenThread.start();
        oddThread.start();
        
        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class EvenOddThread {
    boolean flag;
    int num;
    EvenOddThread(boolean f, int n){
       flag = f;
       num = n;
    }
    
    void evenPrint(){
        while(true) {
            synchronized(this) {
                while(flag == true){
                    try{
                        wait();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }              
                }
                System.out.println("Even Thread : " + num);
                num++;
                flag = true;
                notify();
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(EvenOddThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    void oddPrint(){
        while(true) {
            synchronized(this) {
                while(flag == false){
                    try{
                        wait();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }              
                }
                System.out.println("Odd Thread : " + num);
                num++;
                flag = false;
                notify();
            }            
            try{
                Thread.sleep(200);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}   
