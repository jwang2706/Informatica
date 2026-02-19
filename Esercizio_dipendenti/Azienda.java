import java.util.ArrayList;
import java.util.Iterator;

public class Azienda {
    // Attributi
    private String ragioneSociale;
    private ArrayList<Dipendente> listaDipendenti;
    private ArrayList<String> listaLinguaggi;
    // Costruttore
    public Azienda(String ragioneSociale){
        this.ragioneSociale=ragioneSociale;
        listaDipendenti = new ArrayList<Dipendente>();
        listaLinguaggi = new ArrayList<String>();
    }
    // Metodi getter e setter
    public ArrayList<Dipendente> getListaDipendenti() {
        return listaDipendenti;
    }
    public ArrayList<String> getListaLinguaggi() {
        return listaLinguaggi;
    }
    // Metodi richiesti
    public void addDipendente(Dipendente d){
        listaDipendenti.add(d);
    }
    public void addLinguaggio(String l){
        listaLinguaggi.add(l);
    }
    public String nuovoCodice(char prefisso){
        Iterator<Dipendente> it;
        int ultimo = 0;
        it = listaDipendenti.iterator();
        Dipendente d;
        while(it.hasNext()){
            d = it.next();
            if(d.getCodice().charAt(0)==prefisso){
                ultimo = Integer.parseInt(d.getCodice().substring(1, d.getCodice().length()));
            }
        }
        ultimo++;
        String riempitivo;
        if(ultimo>99)
            riempitivo="";
        else if(ultimo>9) 
            riempitivo="0";
        else 
            riempitivo="00";
        return prefisso + riempitivo + ultimo;
    }
    // Metodo che verifica se ci sono Manager
    public boolean thereIsManager(){
        boolean thereIs = false;
        Iterator<Dipendente> it;
        Dipendente d;
        it = listaDipendenti.iterator();
        while(it.hasNext()&&!thereIs){
            d = it.next();
            if(d instanceof Manager){
                thereIs = true;
            }
        }
        return thereIs;
    }
        // Metodo che verifica se ci sono Sviluppatori
    public boolean thereIsSviluppatore(){
        boolean thereIs = false;
        Iterator<Dipendente> it;
        Dipendente d;
        it = listaDipendenti.iterator();
        while(it.hasNext()&&!thereIs){
            d = it.next();
            if(d instanceof Sviluppatore){
                thereIs = true;
            }
        }
        return thereIs;
    }
}
