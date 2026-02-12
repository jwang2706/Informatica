

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


public class Programmatore extends Dipendente{
    private ArrayList<String> linguaggi;
    private static double percentuale = 0.01;
    // private static final String[] listaLinguaggi = {"Java", "Python", "JavaScript", "C", "C++", "Ruby", "Rust", "Visual Basic", "C#", "PHP"};
    private static ArrayList<String> listaLinguaggi = new ArrayList<>(Arrays.asList("Java", "Python", "JavaScript", "C", "C++", "Ruby", "Rust", "Visual Basic", "C#", "PHP"));
    
    public Programmatore(String codice, String nome, String cognome, LocalDate dataAssunzione, double stipendioBase){
        super(codice, nome, cognome, dataAssunzione, stipendioBase);
        linguaggi = new ArrayList<>();
    }
    
    @Override
    public double calcolaStipendio(){
        double stipendio, percentualeAumento;
        LocalDate annoAttuale = LocalDate.now();
        int anniAnzianita = annoAttuale.getYear() - dataAssunzione.getYear();
        int incrementoStipendio;
        
        if (annoAttuale.getDayOfYear() < dataAssunzione.getDayOfYear()){
            anniAnzianita--;
        }
        
        incrementoStipendio = anniAnzianita / 5;
        percentualeAumento = stipendioBase * percentuale * incrementoStipendio;
        
        stipendio = stipendioBase + percentualeAumento;
        
        return stipendio;
    }
    
    public void setPercentuale(double nuovaPercentuale){
        percentuale = nuovaPercentuale;
    }
    
    public void aggiungiLinguaggio(String linguaggio){
        linguaggi.add(linguaggio);
    }
    
    public void stampaListaLinguaggi(){
        for (int i = 0; i < listaLinguaggi.size(); i++){
            System.out.println("- " + listaLinguaggi.get(i));
        }
    }
    
    public boolean esisteLinguaggio(String linguaggio){        
        for (int i = 0; i < listaLinguaggi.size(); i++){
            if (linguaggio.equals(listaLinguaggi.get(i))){
                return true;
            }
        }
        
        return false;
    }
    
    public static void aggiornaLinguaggi(String nuovoLinguaggio){
        listaLinguaggi.add(nuovoLinguaggio);
    }
}