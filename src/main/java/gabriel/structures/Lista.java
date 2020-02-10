package gabriel.structures;

public class Lista {
    int first = 0;
    int elementCount = 0;
    int arraySize;
    String[] ordersArray;

    public Lista() {
        this(100); /// a default okay size
        arraySize = 100;

    }

    public Lista(int arraySize) {
        ordersArray = new String[arraySize];
        this.arraySize = arraySize;
    }

    public boolean createCopy() {
        String[] biggerArray = new String[arraySize * 2]; /// add an exception handler later
        for (int i = 0; i < arraySize; i++) {
            biggerArray[i] = ordersArray[(first + i) % arraySize];
        }
        ordersArray = biggerArray;
        first = 0;
        return true;
    }

    public boolean addOrder(String order) {
        if (elementCount+1 == arraySize){
            createCopy();}
        elementCount++;
        ordersArray[(first + elementCount) % arraySize] = order;
        return true;
    }

    public void DeliverOrder() { // similar to Pop
        first++;
        elementCount--;
    }


    public boolean search(String searched) {
        if (elementCount == 0)
            return false;
        for (int i = 0; i < elementCount; i++) {
            if (ordersArray[i] == searched)
                return true;
        }
        return false;
    }

    public void printOrders() {
        for (int i = 0; i < arraySize; i++) {
            System.out.println(ordersArray[(first + i) % arraySize]);
        }
    }
}
