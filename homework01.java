import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


/**
 * Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернет “перевернутый” список
 */
public class homework01 {

    public static void main(String[] args) {
        LinkedList<Integer> originalList = new LinkedList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));       

        System.out.println(reverseLinkedList(originalList));
    }

    public static List <Integer> reverseLinkedList(LinkedList<Integer> lList){
        Stack<Integer> tempStack = new Stack<>();
        while(lList.peek() != null) tempStack.push(lList.poll());
        while(!tempStack.empty())   lList.addLast(tempStack.pop());

        return lList;
    }


}