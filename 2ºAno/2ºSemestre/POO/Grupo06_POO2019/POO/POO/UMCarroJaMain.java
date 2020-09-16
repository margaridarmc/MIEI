import java.io.IOException;
import java.util.NoSuchElementException;


/**
 * Main do projeto
 */
public class UMCarroJaMain
{
    public static void main(String[] args) throws NoSuchElementException,PasswordErradaException , ElemNotFoundException ,IOException {

        Model model = new Model();
        
        View view = new View();
        Controller control = new Controller();
        
        control.setModel(model);
        control.setView(view);
        control.controllerInit();
        System.exit(0);
    }
}
