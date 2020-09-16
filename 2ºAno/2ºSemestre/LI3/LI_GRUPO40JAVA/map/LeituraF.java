import java.io.*;
import java.util.*;
import static java.lang.System.out;
import java.nio.file.*;


public class LeituraF
{
    public List<String> linhas;
    
    public LeituraF(){
        this.linhas=new ArrayList<>();
    }
    
    
      public LeituraF (LeituraF l){
        this.linhas = l.getLinhas();
    }
    
       public LeituraF clone(){
        return new LeituraF(this);
    }
   
        
    public void lerFiles (String fichtxt){
        try{
            this.linhas=Files.readAllLines(Paths.get(fichtxt));
        }
        catch(IOException exec) {out.println(exec);}
    }
    
    public List<String> getLinhas(){
        return linhas;
    }
}