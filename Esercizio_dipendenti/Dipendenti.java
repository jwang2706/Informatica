

import java.time.LocalDate;




public abstract class Dipendente {
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
    
    public abstract double calcolaStipendio();
    
    public String getCodice(){
        return codice;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getCognome(){
        return cognome;
    }
    
    public LocalDate getDataAssunzione(){
        return dataAssunzione;
    }
    
    public double getStipendioBase(){
        return stipendioBase;
    }
}