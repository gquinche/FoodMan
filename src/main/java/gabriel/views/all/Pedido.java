package gabriel.views.all;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

class UndoNode {
    public UndoNode next;
    String data;

    public UndoNode(String newString) {
        data = newString;
        next = null;
    }
}

class myUndoStack{
    private  UndoNode head;
    int count;

    public myUndoStack() {

    }
    public void Pop(){
        if(head == null) {
            System.out.println("this shouldn't happen don't call pop if void");
            return ;
        }///else
        count --;
        head = head.next;
    }

    public String Top(){
        if(head == null) {
            System.out.println("this shouldn't happen don't call pop if void");
            return "No hay pedidos";
        }///else
        String cacheString = head.data;
        return cacheString;

    }
    public void insert(String orderText){
        count ++;
         UndoNode cache = new UndoNode("Pedido #" + String.valueOf(count) + " " + orderText);
        cache.next = head;
        head = cache;

    }


}

@Route("Pedido")
public class Pedido extends  VerticalLayout{
    public Pedido(){
        int orderCount = 0;
        myUndoStack orderInstance = new myUndoStack();
        TextArea orderConsole = new TextArea("Ordenes");
orderConsole.setPlaceholder("No hay pedidos");
orderConsole.setWidth("30em");
        // IMPORT FROM ACOMPANAMIENTOS LIST
        String[] Principales = {"CHULETA", "PECHUGA", "ROBALO"};

        RadioButtonGroup<String> selectorPrincipal = new RadioButtonGroup<>();
        selectorPrincipal.setItems(Principales);
        selectorPrincipal.setErrorMessage("El plato no esta disponible");
        selectorPrincipal.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

        add(selectorPrincipal,orderConsole);

        String[] SidePlates = {"GARBANZOS", "TORTA", "VERDURA"};
        ComboBox<String> comboBox1 = new ComboBox<>("ACOMPAÑANTE 1");
        comboBox1.setItems(SidePlates);
        comboBox1.setValue(SidePlates[0]);
//        add(comboBox1);
        ComboBox<String> comboBox2 = new ComboBox<>("ACOMPAÑANTE 2");
        comboBox2.setItems(SidePlates);
        comboBox2.setValue(SidePlates[1]);
        add(comboBox1,comboBox2);

        Button button = new Button("AGREGAR ORDEN",
                ///event -> add(new Span(selectorPrincipal.getValue()+ " " + comboBox1.getValue()+ " " + comboBox2.getValue())));
                event -> {
                    orderInstance.insert(
                            selectorPrincipal.getValue() + " " + comboBox1.getValue() + " " + comboBox2.getValue()
                    );
                    orderConsole.setValue(orderInstance.Top());

                }
        );
        Button returnButton = new Button("RETURN");
        returnButton.addClickListener(click -> {
            returnButton.getUI().ifPresent(ui ->
                    ui.navigate("Mesa"));
            }
        );

        Button undoButton = new Button("UNDO");
        undoButton.addClickListener(click -> {
            orderInstance.Pop();
        orderConsole.setValue(orderInstance.Top());
                }
        );
        button.setEnabled(false);
        selectorPrincipal.addValueChangeListener(event -> button.setEnabled(true));
        add(new HorizontalLayout(button,returnButton,undoButton),orderConsole);




    }

}