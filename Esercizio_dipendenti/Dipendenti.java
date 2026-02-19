import java.time.LocalDate;

public abstract class Dipendente {
    // Attributi
    protected String codice;
    protected String nome;
    protected String cognome;
    protected LocalDate dataAssunzione;
    protected double stipendioBase;
    // Costruttore
    public Dipendente(String codice, String nome, String cognome, LocalDate dataAssunzione, double stipendioBase){
        this.codice=codice;
        this.nome=nome;
        this.cognome=cognome;
        this.dataAssunzione=dataAssunzione;
        this.stipendioBase=stipendioBase;
    }
    // Metodi getter e setter
    public String getCodice() {
        return codice;
    }
    public LocalDate getDataAssunzione() {
        return dataAssunzione;
    }
    // Metodi richiesti
    public abstract double stipendio();
}








