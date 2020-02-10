package gabriel.views.all;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Random;
class Node {
    public Node right;
    public Node left;
    String data;
    long quantity;

    public Node(String newString, long quantity) {
        data = newString;
        this.quantity = quantity;
        left = null;
        right = null;
    }
}

class myThree {
    private Node root;
    public int count;
    int height, cacheHeight = 0;

    public myThree() {
        count = 0;
        root = null;

    }

    public void insertPublic(String newString, long quantity) {
        root = insert(newString, root, quantity);
        if (cacheHeight > height) height = cacheHeight;
        cacheHeight = 0;
        count++;

    }

    private Node insert(String newString, Node p, long quantity) {
        cacheHeight++;
        if (p == null) {
            p = new Node(newString, quantity);
            if (cacheHeight > height) height = cacheHeight;
        } else if (newString.compareTo(p.data) > 0) {
            p.left = insert(newString, p.left, quantity);
        } else if (newString.compareTo(p.data) < 0) {
            p.right = insert(newString, p.right, quantity);
        } else
            System.out.println("Item in tree and not inserted.");
        return p;
    }

    public void traverseBST() {
        Notification.show("THREE");
        if (root != null) traverseAndPrint(root);
        else Notification.show("EMPTY");
    }

    public long search(String searched, Node p){
        if(p != null)
            if(searched.compareTo(p.data)>0)
                return search(searched,p.left);
            else
            if(searched.compareTo(p.data)<0)
                return search(searched,p.right);
            else{
                if ((long) p.quantity != Long.parseLong(p.data)){
                Notification.show("this shouldn't happen!!!");
                }
                return p.quantity;}
        else
            Notification.show("object "+ searched + " not found");
            return -1;
    }

    public void publicSearch(String searched) {

//        Notification.show(
//                String.valueOf(
//                        search(searched, root)));
    }

    public void insertPublic(String newString, int quantity, String debugging) {
        long start = System.nanoTime();
        root = insert(newString, root, quantity);
        cacheHeight = 0;
        long end = System.nanoTime() - start;
        Notification.show(String.valueOf(end / 1000) + " Miliseconds");
        count++;

    }

    public void traverseBST(String debugging) {

        // this code doesn't print content cus it's for testing exhorbitant big data structures
        Notification.show("THREE of size " + count + " was traversed in ");
        long start = System.nanoTime();

        if (root != null) traverseTester(root);
        else Notification.show("EMPTY");
        long end = System.nanoTime() - start;

        Notification.show(String.valueOf(end / 1000) + " Miliseconds");
    }

    private void traverseAndPrint(Node ptr) {
        if (ptr.left != null)
            traverseAndPrint(ptr.left);
        Notification.show(" " + ptr.data);
        if (ptr.right != null)
            traverseAndPrint(ptr.right);
    }

    private void traverseTester(Node ptr) {
        if (ptr.left != null)
            traverseTester(ptr.left);
        if (ptr.right != null)
            traverseTester(ptr.right);
    }

}

@Route("AdminSet")
public class AdminSetMenu extends VerticalLayout {

    public AdminSetMenu() {


        TextField plato = new TextField("Plato");
        NumberField cantidad = new NumberField("Cantidad");
        Button addPrincipalButton = new Button("Añadir");

        addPrincipalButton.addClickListener(click ->
                add(
                        new Span(
                                plato.getValue()
                        )
                )
        );

        add(new HorizontalLayout(plato, cantidad), addPrincipalButton);

        TextField plato2 = new TextField("Acompañamiento");
        NumberField cantidad2 = new NumberField("Cantidad");
        Button addPrin2 = new Button("Añadir");

        Button guardar = new Button("Guardar");
        guardar.addClickListener(click ->
                guardar.getUI().ifPresent(ui ->
                        ui.navigate("Admin"))
        );

        add(new HorizontalLayout(plato2, cantidad2), new HorizontalLayout(addPrin2, guardar));


        myThree testThree = new myThree();
        Random r = new Random();


        /*int bound = 100000; /// insert tester
        long n = r.nextInt(bound);
        Notification.show("quantity of data is " + String.valueOf(n));
        for (int i = 0; i < n; i++) {
            testThree.insertPublic(String.valueOf(r.nextInt()), 10);
        }
        testThree.insertPublic(String.valueOf(r.nextInt()), 10, "debugging = True");*/
        int bound = 1-000; /// insert
        long n = 100000;
long cache;
        Notification.show("quantity of data is " + String.valueOf(n*3));
        ArrayList<Long> toCheck = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cache = r.nextInt();
            toCheck.add(cache);
            testThree.insertPublic(String.valueOf(cache), cache );
        cache = r.nextInt();
            toCheck.add(cache);
            testThree.insertPublic(String.valueOf(cache), cache );
        cache = r.nextInt();
            toCheck.add(cache);
            testThree.insertPublic(String.valueOf(cache), cache );
        }
        for (long data: toCheck) {

            testThree.publicSearch(String.valueOf(data));
        }

    }
}
