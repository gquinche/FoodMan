package gabriel.views.all;

import gabriel.structures.myPair;

public class PriorityQueueClass {

    private final int n = 20;
    private myPair array[];
    private int size;

    public PriorityQueueClass() {
        final int n = 20;
        array = new myPair[n];
        size = 0;
    }

    public void insertItem(myPair x) {
        array[size] = x;
        moveUp();
        size++;
    }
    private void moveUp() {
        int child = size;
        int parent = (child-1)/2;
        myPair temp = array[child];
        while(child > 0 && temp.fst < array[parent].fst) {
            array[child] = array[parent];
            child = parent;
            parent = (child-1)/2;
        }
        array[child]= new myPair(temp.fst,temp.scd);
    }
    public myPair pop() {
        myPair min=array[0];
        array[0]=array[--size];
        moveDown();
        return min;
    }
    private void moveDown() {
        boolean flag = false;
        myPair smallest = new myPair(-1,0); /// asummes al keys are positive
        int parent = 0;
        int child = 2*parent+1;
        myPair temp = array[parent];
        while(child < size && !flag) {
            smallest = array[child];
            if(child+1 < size && array[child+1].fst < array[child].fst)
                smallest = array[++child];
            if(smallest.fst < temp.fst) {
                array[parent] = smallest;
                parent = child;
            }
            else
                flag = true;
            child = 2*parent+1;
        }
        array[parent] = temp;
    }
}
