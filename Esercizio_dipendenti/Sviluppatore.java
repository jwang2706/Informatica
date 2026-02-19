
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Sviluppatore extends Dipendente{
    // Attributi
    private ArrayList<String> listaLinguaggi;
    private static double percentualeAnzianita;
    // Costruttore
    public Sviluppatore(String codice, String nome, String cognome, LocalDate dataAssunzione, double stipendioBase){
        super(codice, nome, cognome, dataAssunzione, stipendioBase);
        listaLinguaggi = new ArrayList<String>();
    }
    // Metodi setter e getter
    public static double getPercentualeAnzinita() {
        return percentualeAnzianita;
    }
    public static void setPercentualeAnzinita(double percentualeAnzinita) {
        Sviluppatore.percentualeAnzianita = percentualeAnzinita;
    } 
    // Metodi richiesti
    public void addLinguaggio(String l){
        listaLinguaggi.add(l);
    }
    @Override
    public double stipendio(){
        LocalDate oggi = LocalDate.now();
        Period anzianita = Period.between(dataAssunzione, oggi);
        double percentuale = percentualeAnzianita * anzianita.getYears()/5;
        return stipendioBase * (1+percentuale/100);
    }
    @Override
    public String toString(){
        String s;
        LocalDate oggi = LocalDate.now();
        Period anzianita = Period.between(dataAssunzione, oggi);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        s = "SVILUPPATORE: " + codice;
        s += " - Nominativo: " + nome + " " + cognome;
        s += " - Data di assunzione: " + dataAssunzione.format(formatter);
        s += "(Anzianita: " + anzianita.getYears() + ")";
        s += " - Stipendio base: " + stipendioBase + " â¬";
        return s;    
    }
}
