package gabriel.views.all;


import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

@Route("Admin")
@PreserveOnRefresh


public class AdminInit extends VerticalLayout {



    private int iHateDoubles(Double d){
        int myLovelyInt = d.intValue();
        if (myLovelyInt<0) return 0;
        else return myLovelyInt;
    }

    public AdminInit() {


        Accordion accordion = new Accordion();

        HorizontalLayout prin = new HorizontalLayout();
        TextField plato = new TextField("Plato");
        NumberField cantidad = new NumberField("Cantidad");
        Button addPrin = new Button("Añadir");
//        addPrin.addClickListener(click ->
//                Storage.principales.put(plato.getValue(), cantidad.getValue().intValue())
//        );
        addPrin.addClickShortcut(Key.ENTER);
        addPrin.addClickListener(click -> {
            NumberField tempField = new NumberField();
            tempField.setHasControls(true);
            tempField.setValue((double) iHateDoubles(cantidad.getValue()));
            tempField.setMin(0);
            accordion.getOpenedPanel().get().addContent(
                            new HorizontalLayout(
                                    new Span(plato.getValue()),
                                    tempField
                            )
                    );
                }
        );

        VerticalLayout principal = new VerticalLayout();
        principal.add(new HorizontalLayout(plato, cantidad), addPrin);
        accordion.add("Principales", principal);

        TextField plato2 = new TextField("Acompañamiento");
        NumberField cantidad2 = new NumberField("Cantidad");
        Button addPrin2 = new Button("Añadir");
        addPrin2.addClickListener(click ->
                Notification.show("this should update the database or data structure")
        );
        addPrin2.addClickShortcut(Key.ENTER);

        addPrin2.addClickListener(click -> {
            NumberField tempField = new NumberField();
            tempField.setHasControls(true);
            tempField.setMin(0);
            tempField.setValue((double) iHateDoubles(cantidad2.getValue()));
            accordion.getOpenedPanel().get().addContent(
                            new HorizontalLayout(
                                    new Span(plato2.getValue()),
                                    tempField
                            )
                    );
                }
        );

        VerticalLayout acompañamientos = new VerticalLayout();
        acompañamientos.add(new HorizontalLayout(plato2, cantidad2),addPrin2);
        accordion.add("Acompañamientos", acompañamientos);
        accordion.addOpenedChangeListener(e ->
                Notification.show("this should be updated")
        );


        HorizontalLayout buttons = new HorizontalLayout();
        Button set = new Button("Definir menú");
        set.addClickListener(click ->
                set.getUI().ifPresent(ui ->
                        ui.navigate("AdminSet"))
        );
        Button edit = new Button("Editar  menú");
        edit.addClickListener(click ->
                set.getUI().ifPresent(ui ->
                        ui.navigate("AdminEdit"))
        );
        buttons.add(set);
        Button returnButton = new Button("RETURN");
        returnButton.addClickListener(click -> {
            returnButton.getUI().ifPresent(ui ->
                    ui.navigate("LoginScreen"));
        });

        add(
                new H2("Inventario Actual"),
                accordion,
                buttons,
                returnButton
        );


//        HorizontalLayout prin = new HorizontalLayout();
        //        prin.add( plato, cantidad, addPrin);

//        Button guardar = new Button("Guardar");
//        guardar.addClickListener(click ->
//                guardar.getUI().ifPresent(ui ->
//                        ui.navigate("Admin"))
//        );
    }
}
