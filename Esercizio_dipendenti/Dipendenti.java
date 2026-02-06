

import java.time.LocalDate;

public class Dipendente {
    protected String codice;
    protected String nome;
    protected String cognome;
    protected LocalDate dataAssunzione;
    protected double stipendioBase;
    
    public Dipendente(String codice, String nome, String cognome, LocalDate dataAssunzione, double stipendioBase){
        this.codice = codice;
        this.nome = nome;
        this.cognome = cognome;
        this.dataAssunzione = dataAssunzione;
        this.stipendioBase = stipendioBase;
    }
    
    public double calcolaStipendio(){
        return 0;
    }
}