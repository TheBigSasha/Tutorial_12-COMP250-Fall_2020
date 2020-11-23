package Tutorial;/* Generic Min/Max Binary Tutorial.Heap
 * for /r/javaexamples
 *
 */
import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("unchecked")

/**
 * Based on code by /u/Philboyd_Studge
 */
public class Heap<T extends Comparable<T>> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] heap;
    /*
    tree:
                head
           n1            n2
      n3        n4

    array:
       1     2   3   4    5
      [head, n1, n2, n3 , n4]

     */
    private int length;
    private final boolean min;


    public Heap(T[] array, boolean min){
        heap = (T[]) new Comparable[DEFAULT_CAPACITY];
        length = 0;
        this.min = min;

        for(T element : array){
            add(element);
        }
    }

    public void add(T value)
    {
        if(this.length >= heap.length -1){
            heap = this.resize();       //O(N), but it basically never runs so we don't care (edge case, very rare)
        }

        length++;
        heap[length] = value;          //Array access ( O(1) )

        bubbleUp();                    //Heap traversal through height, worst case logn
    }


    public T pop() {     //Removes and returns the topmost node, like pop on a stack
        //for most data structures, to remove we start by moving!
        //So in a binary search tree, we swap a node for the leftmost child
        //And here, we swap the node to the very end!

        T output = peek();

        swap(1, length);
        heap[length] = null;
        length--;

        bubbleDown();

        return output;

    }

    public void remove(T item){
        for(int i = 0; i < heap.length; i++){       //SLOW BAD AND UNTESTED! DO NOT TRUST, USE WITH CAUTION
            if(item.equals(heap[i])){
                swap(i, length);
                heap[length] = null;
                length--;
                bubbleDown();
            }
        }
    }

    public boolean isEmpty()
    {
        return length == 0;
    }

    public T peek()
    {
        if(!isEmpty()){
            return heap[1];
        }else{
            return null;
        }
    }

    public int length() {
        return length;
    }

    private T[] resize() {
        return Arrays.copyOf(heap, heap.length + DEFAULT_CAPACITY);
        //Increases the size of our array by the default capacity
        //So if we have 11 things, our array will increase to 20 slots
    }

    private void bubbleUp() {
        //Takes the item we just added to the last index, and makes sure that it follows the HEAP RULE
        //The HEAP RULE is that for each subheap (like a subtree), the min or max must be at the top
        //Whether it is min or max depends on the boolean min
        int idx = length;
        if(min){//Situation for a min heap
            while(hasParent(idx) && (parent(idx).compareTo(heap[idx]) > 0)){        //While there is a parent which is bigger (ILLEGAL)
                //FIX THE HEAP!!!!!!!!

                //We know this traverses at worst the height of the tree (we only traverse upwards)
                //Worst case: height of tree. If it takes 2^(n-1) items added to increase the height to n,
                //Then we know that worst case, the height is logn
                //therefore the worst case for this method is logn


                swap(idx,parentIndex(idx)); //Swap the nodes so the smallest is on top
                idx = parentIndex(idx);     //Move up the heap to the next one until top!
            }

        }else{//Situation for a max heap
            while(hasParent(idx) && (parent(idx).compareTo(heap[idx]) < 0)){        //Follow max heap rule
                swap(idx, parentIndex(idx));
                idx = parentIndex(idx);
            }
        }
    }

    private void bubbleDown() {
        int idx = 1;
        if(min){
           while(hasLeftChild(idx)){
               int smaller = leftIndex(idx);
               if(hasRightChild(idx) && heap[leftIndex(idx)].compareTo(heap[rightIndex(idx)]) > 0){
                   smaller = rightIndex(idx);
               }
               if(heap[idx].compareTo(heap[smaller]) > 0){
                   swap(idx, smaller);
               }else break;

               idx = smaller;
           }
        }else{
            while(hasLeftChild(idx)){
                int larger = leftIndex(idx);
                if(hasRightChild(idx) && heap[leftIndex(idx)].compareTo(heap[rightIndex(idx)]) < 0){
                    larger = rightIndex(idx);
                }
                if(heap[idx].compareTo(heap[larger]) < 0){
                    swap(idx, larger);
                }else break;

                idx = larger;
            }
        }
    }

    private boolean hasParent(int i) {
        return i > 1;   //If it is not the head (head is stored at idx 1), then return true. All non head nodes must have a parent
    }

    private int leftIndex(int i) {
        return i * 2;
    }

    private int rightIndex(int i) {
        return i * 2 + 1;
    }

    private boolean hasLeftChild(int i) {
        return leftIndex(i) <= length;  //If we don't have the index of the left child in our data structure, it is obviously not there
    }

    private boolean hasRightChild(int i) {
        return rightIndex(i) <= length;
    }

    private int parentIndex(int i) {
        return i / 2;
    }

    private T parent(int i) {
        return heap[parentIndex(i)];
    }

    private void swap(int index1, int index2) {
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
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

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 1;
            @Override
            public boolean hasNext() {
                return index > length();
            }

            @Override
            public T next() {
                T out = heap[index];
                index++;
                return out;
            }
        };
    }

    public static void main(String args[]){
        Integer[] arr = {3, 15, 99, 214, 7 , 4, 1, 34, 66, 745};

        Heap<Integer> testSubject = new Heap<>(arr, true);

        System.out.println(testSubject);

        if(testSubject.length != arr.length) System.out.println("You done messed it up bruh");
        int size = testSubject.length;

        for(int i = 0; i < size; i++){
            System.out.println(testSubject.pop());
        }


    }
}