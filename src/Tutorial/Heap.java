package Tutorial;/* Generic Min/Max Binary Tutorial.Heap
 * for /r/javaexamples
 *
 */
import java.util.Arrays;

@SuppressWarnings("unchecked")

/**
 * Based on code by /u/Philboyd_Studge
 */
public class Heap<T extends Comparable<T>> {


    public Heap(T[] array, boolean min){

    }

    public void add(T value)
    {

    }


    public T remove()
    {

    }

    public boolean isEmpty()
    {
    }

    public T peek()
    {

    }

    public int length() {

    }

    private T[] resize() {

    }

    private void bubbleUp() {

    }

    private void bubbleDown() {

    }

    private boolean hasParent(int i) {

    }

    private int leftIndex(int i) {

    }

    private int rightIndex(int i) {

    }

    private boolean hasLeftChild(int i) {

    }

    private boolean hasRightChild(int i) {

    }

    private int parentIndex(int i) {

    }

    private T parent(int i) {

    }

    private void swap(int index1, int index2) {

    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (T item : heap)
        {
            if (item != null) sb.append(item).append(", ");
        }
        return sb.toString();

    }

}