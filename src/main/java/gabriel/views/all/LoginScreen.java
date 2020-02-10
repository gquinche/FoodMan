package gabriel.views.all;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

import javax.management.Notification;

@Route("LoginScreen")
public class LoginScreen extends VerticalLayout {

    public LoginScreen() {
        VerticalLayout LoginScreen = new VerticalLayout();
        TextField usernameTextField = new TextField("Usuario");
        PasswordField passwordTextField = new PasswordField("Contraseña");
        Button addButton = new Button("Login");
        //addButton.getElement().getStyle().set("margin-left", "auto");
        addButton.addClickShortcut(Key.ENTER);
        addButton.addClickListener(click -> {
            String username = usernameTextField.getValue();
            String password = passwordTextField.getValue();
            if(username.equals("admin") && password.equals("admin")) {
                add(new H3("admin entered"));
                addButton.getUI().ifPresent(ui ->
                        ui.navigate("Admin"));
            } else {
                addButton.getUI().ifPresent(ui ->
                        ui.navigate("Mesa"));
                add(new H3("Who are you?"));
            }
        });
        add(
                new H1("sAres"),
                usernameTextField,
                passwordTextField,
                addButton
        );
    }
}