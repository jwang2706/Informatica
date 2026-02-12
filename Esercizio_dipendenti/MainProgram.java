

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;


public class MainProgram {

    private static ArrayList<Dipendente> dipendenti = new ArrayList<>();
    
    public static void main(String[] args) {
        int scelta;
        
        do{
            scelta = menu();
            
            switch (scelta) {
                case 1:
                    aggiungiDipendente();
                    break;
                case 2:
                    assegnaManager();
                    break;
                case 3:
                    modificaPercentualiContributo();
                    break;
                case 4:
                    calcolaStipendio();
                    break;
                case 5:
                    aggiornaListaLinguaggi();
                    break;
                case 0:
                    System.out.println("Uscita dal programma in corso...");
                default:
                    System.out.println("Scelta non valida!");
            }
            
        }while(scelta != 0);
    }
    
    public static int menu(){
        int scelta;
        Scanner in = new Scanner(System.in);
        
        System.out.println("Menù");
        System.out.println("1) Aggiungi dipendente");
        System.out.println("2) Assegna un manager ad un programmatore");
        System.out.println("3) Modifica percentuali ed il contributo");
        System.out.println("4) Calcola stipendio di un dipendente");
        System.out.println("5) Aggiorna lista linguaggi programmazione");
        System.out.println("0) Esci");
        System.out.print("Scelta: ");
        
        scelta = in.nextInt();
        
        return scelta;
    }
    
    private static void aggiungiDipendente(){
        String codice, nome, cognome, tipo, linguaggio;
        LocalDate dataAssunzione;
        double stipendioBase;
        Programmatore prog = null;
        Scanner in = new Scanner(System.in);
        
        System.out.print("Codice: ");
        codice = in.nextLine();
        System.out.print("Nome: ");
        nome = in.nextLine();
        System.out.print("Cognome: ");
        cognome = in.nextLine();
        System.out.print("Data assunzione (AAAA-MM-GG): ");
        dataAssunzione = LocalDate.parse(in.nextLine());
        System.out.print("Stipendio iniziale: ");
        stipendioBase = in.nextDouble();
        in.nextLine();
        System.out.print("Manager o Programmatore: ");
        tipo = in.nextLine();
        
        if (tipo.equals("Manager")){
            dipendenti.add(new Manager(codice, nome, cognome, dataAssunzione, stipendioBase));
        }else if (tipo.equals("Programmatore")){
            prog = new Programmatore(codice, nome, cognome, dataAssunzione, stipendioBase);
            dipendenti.add(prog);
            
            do{
                prog.stampaListaLinguaggi();
                System.out.print("Linguagio di programmazione: ");
                linguaggio = in.nextLine();
                if (prog.esisteLinguaggio(linguaggio)){
                    prog.aggiungiLinguaggio(linguaggio);
                }else{
                    System.out.println("Linguaggio non trovato!");
                }
                System.out.print("Vuoi inserire un altro linguaggio di programmazione? (Si/No): ");
            }while (in.nextLine().equals("Si"));
        }else{
            System.out.println("Opzione non valida!");
        }
        
        System.out.println("Dipendente aggiunto!");
    }
    
    private static void assegnaManager(){
        Scanner in = new Scanner(System.in);
        String codiceProgrammatore, codiceManager;
        Manager man = null;
        Programmatore prog = null;
        
        System.out.print("Codice manager: ");
        codiceManager = in.nextLine();
        
        for (int i = 0; i < dipendenti.size(); i++){
            if (dipendenti.get(i).getCodice().equals(codiceManager)){
                man = (Manager) dipendenti.get(i);
            }
        }
        
        System.out.print("Codice programmatore: ");
        codiceProgrammatore = in.nextLine();
        
        for (int i = 0; i < dipendenti.size(); i++){
            if (dipendenti.get(i).getCodice().equals(codiceProgrammatore)){
                prog = (Programmatore) dipendenti.get(i);
            }
        }
        
        if (man != null && prog != null){
            man.aggiungiProgrammatore(prog);
            System.out.println("Programmatore asssegnato con successo!");
        }else{
            System.out.println("Nessun programmatore/manager trovato!");
        }
    }
    
    private static void modificaPercentualiContributo(){
        Scanner in = new Scanner(System.in);
        Programmatore prog = null;
        Manager man = null;
        String codiceProgrammatore, codiceManager;
        
        System.out.print("Codice programmatore (vuoto se non si vuole modificare): ");
        codiceProgrammatore = in.nextLine();
        System.out.print("Codice manager (vuoto se non si vuole modificare): ");
        codiceManager = in.nextLine();
        
        for (int i = 0; i < dipendenti.size(); i++){
            if (dipendenti.get(i).getCodice().equals(codiceManager)){
                man = (Manager) dipendenti.get(i);
            } else if (dipendenti.get(i).getCodice().equals(codiceProgrammatore)){
                prog = (Programmatore) dipendenti.get(i);
            }
        }
        
        if (man != null){
            System.out.print("Percentuale maggioramento (es: 2): ");
            man.setPercentuale(in.nextDouble() / 100);
            
            System.out.print("Contributo (es: 15): ");
            man.setContributo(in.nextInt());
        } else{
            System.out.println("Manager non trovato!");
        }
        
        if (prog != null){
            System.out.println("Percentuale maggioramento (es: 2): ");
            prog.setPercentuale(in.nextDouble() / 100);
        } else{
            System.out.println("Programmatore non trovato!");
        }
    }
    
    private static void calcolaStipendio(){
        Scanner in = new Scanner(System.in);
        String codice;
        Dipendente dip = null;
        
        System.out.print("Codice: ");
        codice = in.nextLine();
        
        for (int i = 0; i < dipendenti.size(); i++){
            if (dipendenti.get(i).getCodice().equals(codice)){
                dip = dipendenti.get(i);
            }
        }
        
        if (dip != null){
            System.out.println("Stipendio attuale di " + dip.getNome() + " " + dip.getCognome());
            System.out.println("Codice: " + dip.getCodice() + "\nData assunzione: " + dip.getDataAssunzione());
            System.out.println("Stipendio base: " + dip.getStipendioBase());
            System.out.println("Stipendio attuale: " + dip.calcolaStipendio());
        }else{
            System.out.println("Dipendente non trovato!");
        }
    }
    
    private static void aggiornaListaLinguaggi(){
        Scanner in = new Scanner(System.in);
        String linguaggio;
        Programmatore prog = null;
        System.out.print("Linguaggio da aggiungere: ");
        linguaggio = in.nextLine();
        
        Programmatore.aggiornaLinguaggi(linguaggio);
    }
}