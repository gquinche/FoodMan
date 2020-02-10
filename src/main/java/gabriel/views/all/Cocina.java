package gabriel.views.all;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

import java.util.Random;



@Route("Cocina")
public class  Cocina extends VerticalLayout {

    static Random r = new Random();


    static int myRandom(int bound){ /// decorator to allow zero as bound
        if(bound <= 0) return 0;
        else return r.nextInt(bound);
    }
    public Cocina() {

        TextArea orderConsole = new TextArea("Ordenes");
        orderConsole.setPlaceholder("No hay pedidos");
        orderConsole.setWidth("30em");

        String[] platos = {"CHULETA", "PECHUGA", "ROBALO", "GARBANZO", "TORTA", "VERDURA"};


        int numeroDeOrdenes = r.nextInt(10);
        int cantidadDeIngredientes = 6;

        int[] arrayTemp = new int[cantidadDeIngredientes+1]; //array con un espacio extra para el total


        int[][] arrayOfOrders = new int[numeroDeOrdenes][]; /// se crean apuntador a n filas para cada orden

        for (int i = 0; i < numeroDeOrdenes; i++) {
            int comensales = myRandom(10);

//              int cantidadPlatos = myRandom(comensales);
            arrayTemp[0] = myRandom(comensales); // elige un número a lo sumo tan grande como el de comensales para el primer plato
            arrayTemp[1] = myRandom(comensales - arrayTemp[0]); // toma algo de lo que queda de comensales
            arrayTemp[2] = (comensales - arrayTemp[0] - arrayTemp[1]);  // toma el complemento para que halla exactamente platos como comensales
            arrayTemp[3] = myRandom(comensales*2); // elige un maximo del primer sideplata
            arrayTemp[4] = myRandom(comensales*2 - arrayTemp[3]); // toma otra parte de ahí
            arrayTemp[5] = (comensales*2 - arrayTemp[3] - arrayTemp[4]); // completa los sides plates

            arrayTemp[6] = comensales; // guarda el numero de comensales para no volver a calcularlo
//
//            arrayTemp[3] *= 2; // duplica los acompañantes pues ahí dos por plato
//            arrayTemp[4] *= 2;
//            arrayTemp[5] *= 2;
            arrayOfOrders[i] = arrayTemp;
            arrayTemp = new int[cantidadDeIngredientes+1];


        }

        myUndoStack orderInstance = new myUndoStack();

        for (int i = 0; i < numeroDeOrdenes; i++) {
            String orderText = "\n" + " " + "\n";
            for (int j = 0; j < cantidadDeIngredientes; j++) {
                if( arrayOfOrders[i][j] == 0) continue; // skip
                orderText = orderText.concat( arrayOfOrders[i][j] + " " + platos[j]);
                if(arrayOfOrders[i][j] > 1 && !platos[j].endsWith("S") && !platos[j].endsWith("s") && !platos[j].endsWith(".")) orderText += "S";
                orderText += "\n";

            }
            if(arrayOfOrders[i][cantidadDeIngredientes] == 1) orderText += "\n" + "Para 1 persona.";
            else orderText += "\n" +  "Para " + arrayOfOrders[i][cantidadDeIngredientes] + " personas." ;
            orderInstance.insert(orderText);
        }

        Notification.show(String.valueOf(orderInstance.count));

        Button undoButton = new Button("Completar pedido");
        undoButton.addClickListener(click -> {
                    orderInstance.Pop();
                    orderConsole.setValue(orderInstance.Top());
                }
        );

        add(orderConsole, undoButton);
        orderConsole.setValue(orderInstance.Top());
    }
}