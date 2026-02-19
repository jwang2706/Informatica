
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;

public class MainProgram {
    // Attributi
    private static Azienda a;
    private static Scanner in;
    // Metodo principale di avvio
    public static void main(String[] args){
        int scelta;
        a = new Azienda("Pippo s.p.a.");
        a.addLinguaggio("C++");
        a.addLinguaggio("Java");
        a.addLinguaggio("Javascript");
        a.addLinguaggio("PHP");
        do{
            scelta = menu();
            switch(scelta){
                case 1 -> {addDipendente();}
                case 2 -> {assignSviluppatoreToManager();}
                case 3 -> {setParametri();}
                case 4 -> {stipendio();}
                case 0 -> {System.out.println("│ Chiusura programma ... OK");}
                default -> {System.out.println("│ ATTENZIONE: scelta errata!!!");}
            }
        }while(scelta!=0);
    } 
    // Metodo che visualizza il menu e restituisce la scelta dell'utente
    public static int menu(){
        int scelta;
        in = new Scanner(System.in);
        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│         GESTIONE DIPENDENTI           │");
        System.out.println("├────────────────────────────────────────────────┤");
        System.out.println("│ 1) Inserisci dipendente               │");
        System.out.println("│ 2) Assegna sviluppatore a manager     │");
        System.out.println("│ 3) Modifica parametri di anzianità    │");
        System.out.println("│ 4) Calcola stipendio                  │");
        System.out.println("├────────────────────────────────────────────────┤");
        System.out.println("│ 0) Uscita                             │");
        System.out.println("├────────────────────────────────────────────────┘");
        System.out.print("│ Scelta: ");
        try{
            scelta = Integer.parseInt(in.nextLine());
        }
        catch(NumberFormatException e){
            scelta=-1;
        }
        return scelta;
    }
    // Metodo di aggiunta di un nuovo dipendente
    public static void addDipendente(){
        char tipologia;
        String codice;
        String nome;
        String cognome;
        LocalDate dataAssunzione;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        double stipendioBase;
        do{
            System.out.print("│ Scegli la tipologia di dipendente (M = Manager, S = Sviluppatore): ");
            try{
                tipologia = in.nextLine().charAt(0);
            }
            catch(Exception e){
                tipologia = ' ';
            }
        }while(tipologia!='M' && tipologia!='S');
        System.out.print("│ Codice del dipendente: ");
        codice = a.nuovoCodice(tipologia);
        System.out.println(codice);
        System.out.print("│ Nome del dipendente: ");
        nome = in.nextLine();
        System.out.print("│ Cognome del dipendente: ");
        cognome = in.nextLine();
        do{
            System.out.print("│ Data di assunzione (gg/mm/aaaa): ");
            try{
                dataAssunzione = LocalDate.parse(in.nextLine(), formatter);
            }
            catch(DateTimeParseException e){
                dataAssunzione=null;
            }
        }while(dataAssunzione==null);
        do{
            System.out.print("│ Stipendio base: ");
            try{
                stipendioBase = Double.parseDouble(in.nextLine());
            }
            catch(NumberFormatException e){
                stipendioBase=0;
            }            
        }while(stipendioBase==0);
        // Creazione dipendente
        if(tipologia=='S'){
            Sviluppatore s;
            s = new Sviluppatore(codice, nome, cognome, dataAssunzione, stipendioBase);
            a.addDipendente(s);
            System.out.println("│ Elenco dei linguaggi disponibili: ");
            for(int i=0;i<a.getListaLinguaggi().size();i++){
                System.out.println("│ " + i + ") " + a.getListaLinguaggi().get(i));
            }
            int scelta;
            boolean continua=false;
            do{
                do{
                    System.out.print("│ Scegli l'indice per un linguaggio: ");
                    try{
                        scelta = Integer.parseInt(in.nextLine());
                    }
                    catch(NumberFormatException e){
                        scelta=-1;
                    }
                }while(scelta<0 || scelta>a.getListaLinguaggi().size()-1);
                s.addLinguaggio(a.getListaLinguaggi().get(scelta));
                System.out.print("│ Scegli un altro linguaggio (s/n)? ");
                if(in.nextLine().equals("s"))
                    continua = true;
                else
                    continua = false;
            }while(continua);
        }
        else if(tipologia=='M'){
            Manager m;
            m = new Manager(codice, nome, cognome, dataAssunzione, stipendioBase);
            a.addDipendente(m);
        }
    }
    // Metodo che assegna ad un Manager uno Sviluppatore
    public static void assignSviluppatoreToManager(){
        Manager m = null;
        Sviluppatore s = null;
        Dipendente d = null;
        String codice;
        if(a.thereIsManager() && a.thereIsSviluppatore()){
            // Selezione del manager
            Iterator<Dipendente> it;
            it = a.getListaDipendenti().iterator();
            System.out.println("| Elenco dei manager: ");
            while(it.hasNext()){
                d = it.next();
                if(d instanceof Manager){
                    System.out.println("| " + d.toString());
                }
            }
            System.out.print("| Scegli il codice del manager: ");
            codice = in.nextLine();
            it = a.getListaDipendenti().iterator();  
            while(it.hasNext()){
                d = it.next();
                if(d instanceof Manager && d.getCodice().equals(codice)){
                    m = (Manager)d;
                }
            }   
            if (m!=null){
                it = a.getListaDipendenti().iterator();
                System.out.println("| Elenco degli sviluppatori: ");
                while(it.hasNext()){
                    d = it.next();
                    if(d instanceof Sviluppatore){
                        System.out.println("| " + d.toString());
                    }
                }
                System.out.print("| Scegli il codice dello sviluppatore: ");
                codice = in.nextLine();
                it = a.getListaDipendenti().iterator();  
                while(it.hasNext()){
                    d = it.next();
                    if(d instanceof Sviluppatore && d.getCodice().equals(codice)){
                        s = (Sviluppatore)d;
                    }
                }
                if (s!=null){
                    m.addDiretto(s);
                }
                else{
                    System.out.println("│ ATTENZIONE: codice dello sviluppatore errato!!!");
                }
            }
            else{
                System.out.println("│ ATTENZIONE: codice del manager errato!!!");
            }
        }
        else{
            System.out.println("| ATTENZIONE: non ci sono ancora manager o sviluppatori inseriti!!!");
        }
    }
    // Metodo che modifica gli attributi statici per il calcolo degli stipendi
    public static void setParametri(){
        boolean valid;
        System.out.println("│ I parametri attualmente utilizzati per il calcolo degli stipendi sono: ");
        System.out.println("│ SVILUPPATORI: +" + Sviluppatore.getPercentualeAnzinita() + "% ogni 5 anni");
        System.out.println("│ MANAGER: +" + Manager.getPercentualeAnzinita() + "% ogni 4 anni + contributo di " + Manager.getContributoSviluppatore() + "€ per ogni sviluppatore diretto");
        do{
            valid = true;
            System.out.print("│ Inserisci la nuova percentuale per gli sviluppatori: ");
            try{
                Sviluppatore.setPercentualeAnzinita(Double.parseDouble(in.nextLine()));
            }
            catch(Exception e){
                valid = false;
            }
        }while(!valid);
        do{
            valid = true;
            System.out.print("│ Inserisci la nuova percentuale per i manager: ");
            try{
                Manager.setPercentualeAnzinita(Double.parseDouble(in.nextLine()));
            }
            catch(Exception e){
                valid = false;
            }
        }while(!valid);
        do{
            valid = true;
            System.out.print("│ Inserisci il nuovo contributo per i manager: ");
            try{
                Manager.setContributoSviluppatore(Double.parseDouble(in.nextLine()));
            }
            catch(Exception e){
                valid = false;
            }
        }while(!valid);
    }   
    // Metodo di calcolo dello stipendio
    public static void stipendio(){
        if(a.thereIsManager() || a.thereIsSviluppatore()){
            boolean trovato = false;
            System.out.println("| Elenco dei dipendenti inseriti:");
            Iterator<Dipendente> it;
            Dipendente d;
            it = a.getListaDipendenti().iterator();  
            while(it.hasNext()){
                d = it.next();
                System.out.println("| " + d.toString());
            }
            System.out.print("| Scegli il codice del dipendente per calcolare lo stipendio: ");
            String codice = in.nextLine();
            // Per tornare all'inizio, ricreo un nuovo iteratore
            it = a.getListaDipendenti().iterator(); 
            while(it.hasNext()){
                d = it.next();
                if(d.getCodice().equals(codice)){
                    trovato = true;
                    System.out.println("| Stipendio: " + d.stipendio() + " €");
                }
            }
            if(!trovato){
                System.out.println("| ATTENZIONE: codice errato!!!");
            }
        }
        else{
            System.out.println("| ATTENZIONE: non ci sono ancora dipendenti inseriti!!!");
        }
    }
}
