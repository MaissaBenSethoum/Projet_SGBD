package fr.insarouen.compilation.basic.sgbd;
import fr.insarouen.compilation.basic.interpreteur.InterpreteurCommandeSQL;
import fr.insarouen.compilation.basic.interpreteur.ParseException;
import fr.insarouen.compilation.basic.sgbd.SGBD;
import java.io.*;

public class MainTest {

    public static void main(String[] args) {
        try {
            InputStream input;

            if (args.length > 0) {
                input = new FileInputStream(args[0]);
            } else {
                System.out.println("Entrez vos commandes SQL :");
                input = System.in;
            }
            
            SGBD sgbd = new SGBD();
            try {
                sgbd.repriseSession();
            } catch (Exception e) {}

            InterpreteurCommandeSQL parser = new InterpreteurCommandeSQL(sgbd, input);

            while (true) {
                try {
                    parser.commande();
                } catch (ParseException e) {
                    System.out.println("Erreur de syntaxe : " + e.getMessage());
                    parser.ReInit(input);
                } catch (Error e) {
                    System.out.println("Erreur lexicale : " + e.getMessage());
                    parser.ReInit(input);
                }catch (Exception e) {
                    System.out.println("Erreur : " + e.getMessage());
                    parser.ReInit(input);
                }
            }

        } catch (Exception e) {
            System.out.println("Erreur fatale : " + e.getMessage());
        }
    }
}