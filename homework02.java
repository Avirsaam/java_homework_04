/*
 * Реализуйте очередь с помощью LinkedList со следующими методами:
 * enqueue() - помещает элемент в конец очереди, 
 * dequeue() - возвращает первый элемент из очереди и удаляет его, 
 * first()   - возвращает первый элемент из очереди, не удаляя.
 */


import java.util.LinkedList;


public class homework02 {
    public static void main(String[] args) {
        
        MyQueue myQueue = new MyQueue();
        
        for (int i = 1; i < 7; i++) {
            myQueue.enqueue(i);
            System.out.println(myQueue);
        }
        
        for (int i = 7; i < 11; i++) {
            myQueue.dequeue();
            myQueue.enqueue(i);
            System.out.println(myQueue);            
        }
        
        while(myQueue.size() != 1) {
            myQueue.dequeue();
            System.out.println(myQueue);
        }

        System.out.println("Now first: " + myQueue.first());
    }
}

class MyQueue  {
    private LinkedList<Integer> data = new LinkedList<Integer>();

    public String toString(){
        return data.toString();
    }

    public void enqueue(int value){
        data.offerLast(value);
    }   
    
    public int dequeue(){        
        return data.poll();
    }

    public int first(){
        return data.peek();
    }

    public int size(){
        return data.size();
    }
}
