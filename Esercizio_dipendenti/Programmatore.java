import java.time.LocalDate;
import java.util.ArrayList;


public class Programmatore extends Dipendente{
    private ArrayList<String> linguaggi;
    
    public Programmatore(String codice, String nome, String cognome, LocalDate dataAssunzione, double stipendioBase){
        super(codice, nome, cognome, dataAssunzione, stipendioBase);
    }
    
    public double calcolaStipendio(){
        return 0;
    }
}