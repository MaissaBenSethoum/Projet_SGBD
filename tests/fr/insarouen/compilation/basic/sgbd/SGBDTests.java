package fr.insarouen.compilation.basic.sgbd;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import fr.insarouen.compilation.basic.sgbd.*;
import fr.insarouen.compilation.basic.sgbd.ressources.*;

public class SGBDTests {
    private SGBD sgbd;
    private LinkedHashSet<Attribut> attributs;

    @Before
    public void setup() {
        sgbd = new SGBD();
        attributs = new LinkedHashSet<>();
        attributs.add(new Attribut("id", TypeSQL.INT));
        attributs.add(new Attribut("nom", TypeSQL.VARCHAR));
    }

    @After
    public void apresTests() {
        sgbd = null;
        attributs = null;
    }

    @Test
    public void testCreerTable() throws TableIntrouvableException {
        sgbd.creerTable("clients", attributs);
        Table resultat = sgbd.produitCartesien("clients", "clients");
        assertThat(resultat != null, is(true));
    }

    @Test(expected = TableIntrouvableException.class)
    public void testSupprimerTable() throws TableIntrouvableException {
        sgbd.creerTable("clients", attributs);
        sgbd.supprimerTable("clients");
        sgbd.produitCartesien("clients", "clients");
    }

    @Test
    public void testInsertInto() {
        sgbd.creerTable("clients", attributs);
        ArrayList<Valeur> valeurs = new ArrayList<>();
        valeurs.add(new IntValeur(1));
        valeurs.add(new VarcharValeur("yesmina"));
        sgbd.insertInto("clients", valeurs);
        
        ArrayList<String> colonnes = new ArrayList<>();
        colonnes.add("id");
        colonnes.add("nom");
        Table resultat = sgbd.projection(colonnes, "clients");
        assertThat(resultat.getLignes().size(), is(equalTo(1)));
    }

    @Test
    public void testProduitCartesien() throws TableIntrouvableException {
        sgbd.creerTable("t1", attributs);
        sgbd.creerTable("t2", attributs);

        ArrayList<Valeur> v1 = new ArrayList<>();
        v1.add(new IntValeur(1));
        v1.add(new VarcharValeur("yesmina"));
        sgbd.insertInto("t1", v1);

        ArrayList<Valeur> v2 = new ArrayList<>();
        v2.add(new IntValeur(2));
        v2.add(new VarcharValeur("jules"));
        sgbd.insertInto("t2", v2);

        Table resultat = sgbd.produitCartesien("t1", "t2");
        assertThat(resultat != null, is(true));
        assertThat(resultat.getNom(), is(equalTo("produit-cartesien_t1_t2")));
        assertThat(resultat.getLignes().size(), is(equalTo(1)));

    }

    @Test(expected = TableIntrouvableException.class)
    public void testProduitCartesienTableIntrouvable() throws TableIntrouvableException {
        sgbd.creerTable("t1", attributs);
        sgbd.produitCartesien("t1", "table_inexistante");
    }
}
