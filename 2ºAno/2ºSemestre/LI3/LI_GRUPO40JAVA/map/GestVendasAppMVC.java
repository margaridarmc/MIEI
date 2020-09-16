
public class GestVendasAppMVC{
  
    public static void main(String[] args) {
        
        GestModel model = new GestModel();
        model.initModel();
        if(model == null) { System.out.println("ERRO INICIALIZACAO"); System.exit(-1); }
        
        GestView view = new GestView();
        GestController control = new GestController();
        
        control.setModel(model);
        control.setView(view);
        control.menu();
        System.exit(0);
    }


}
        
