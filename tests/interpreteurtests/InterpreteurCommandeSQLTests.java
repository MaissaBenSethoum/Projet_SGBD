package tests.interpreteurtests;
import fr.insarouen.compilation.basic.interpreteur.InterpreteurCommandeSQL;
import fr.insarouen.compilation.basic.sgbd.SGBD;
import java.io.*;

public class InterpreteurCommandeSQLTests {

    public static void main(String[] args){
        try{
            InputStream input;

            if(args.length > 0){
                input = new FileInputStream(args[0]);
            } else {
                System.out.println("Entrez vos commandes SQL");
                input = System.in;
            }
            SGBD sgbd = new SGBD();
            sgbd.repriseSession();
            InterpreteurCommandeSQL parser = new InterpreteurCommandeSQL(sgbd,input);
            parser.commande();
            System.out.println("Syntaxe valide");

        } catch (Exception e){
            System.out.println("Erreur de parsing");
            System.out.println(e.getMessage());
        }
    
    }
}