
  import java.io.File;
  import java.io.IOException;
  import java.io.FileInputStream;
  import java.io.FileOutputStream;
  import java.io.ObjectInputStream;
  import java.io.ObjectOutputStream;

  /** classe que uarda e binario
   *
   */
  public class ReadWriteFichBinario{


      /**
       * guarda no fichwiro objeto
       * @param lista
       * @param nomeArq
       */
    public static void gravarArquivoBinario(Model lista, String nomeArq) {
      
      File arq = new File(nomeArq);
      try {
        arq.delete();
        arq.createNewFile();
    
          try (ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq))) {
              objOutput.writeObject(lista);
          }
    
      } catch(IOException erro) {
          System.out.printf("Nao foi possivel aceder ao ficheiro");
      }
     }


      /**
       * le a partir do ficheiro objeto
       * @param nomeArq
       * @return
       */
    public static Model lerArquivoBinario(String nomeArq) {
      Model lista = new Model();
      try {
        File arq = new File(nomeArq);
        if (arq.exists()) {
            try (ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq))) {
                lista = (Model)objInput.readObject();
            }
        }
      } catch(IOException erro1) {
          System.out.printf("Nao foi possivel aceder ao ficheiro");
      } catch(ClassNotFoundException erro2) {
          System.out.printf("Nao foi possivel aceder a Classe");
      }
    
      return(lista);
     }
    
  }
