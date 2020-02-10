package gabriel.views.all;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("Mesa")
public class Mesa extends VerticalLayout {

    public Mesa() {
        Accordion accordion = new Accordion();
        VerticalLayout principal = new VerticalLayout();

        NumberField numberField = new NumberField();
        numberField.setHasControls(true);
        NumberField numberField1 = new NumberField();
        numberField1.setHasControls(true);
        NumberField numberField2 = new NumberField();
        numberField2.setHasControls(true);
        int myInt = 72;
        principal.add(
                new HorizontalLayout(
                        new Span("CHULETA"), new Span(String.valueOf(myInt)))
        );
        myInt = 69;

        principal.add(
                new HorizontalLayout(
                        new Span("PECHUGA"), new Span(String.valueOf(myInt)))
        );
        myInt = 69;
        principal.add(
                new HorizontalLayout(
                        new Span("ROBALO"), new Span(String.valueOf(myInt)))
        );

        accordion.add("Principales", principal);

        VerticalLayout acompañamientos = new VerticalLayout();

        // IMPORT FROM ACOMPANAMIENTOS LIST
        String[] SidePlates = {"GARBANZOS", "TORTA", "VERDURA"};

        myInt = 0;
        for (String plate : SidePlates) {
            myInt += 13;
            String toDisplay = String.valueOf(myInt);
            acompañamientos.add(new HorizontalLayout(new Span(plate), new Span(toDisplay)));
        }

        accordion.add("Acompañamientos", acompañamientos);
        Button returnButton = new Button("RETURN");
        returnButton.addClickListener(click -> {
                    returnButton.getUI().ifPresent(ui ->
                            ui.navigate("LoginScreen"));
                });
        Button pedidos = new Button("NUEVO PEDIDO");
        pedidos.addClickListener(click -> {
            returnButton.getUI().ifPresent(ui ->
                    ui.navigate("Pedido"));
        });
        add(
                accordion,
                returnButton,
                pedidos

        );
    }

}