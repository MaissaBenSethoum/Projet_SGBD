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

public class TableTests {
    private Table table;
    private LinkedHashSet<Attribut> attributs;

    @Before
    public void setup() {
        attributs = new LinkedHashSet<>();
        attributs.add(new Attribut("id", TypeSQL.INT));
        attributs.add(new Attribut("nom", TypeSQL.VARCHAR));
        table = new Table("clients", attributs);
    }

    @After
    public void apresTests() {
        table = null;
        attributs = null;
    }

    @Test
    public void testGetNom() {
        assertThat(table.getNom(), is(equalTo("clients")));
    }

    @Test
    public void testGetAttributs() {
        assertThat(table.getAttributs(), is(equalTo(attributs)));
    }

    @Test
    public void testGetAttribut() {
        Attribut a = table.getAttribut("id");
        assertThat(a != null, is(true));
        assertThat(a.getNom(), is(equalTo("id")));
    }

    @Test
    public void testGetAttributInexistant() {
        Attribut a = table.getAttribut("adresse");
        assertThat(a == null, is(true));
    }

    @Test
    public void testGenererProchainSerial() {
        int s1 = table.genererProchainSerial();
        int s2 = table.genererProchainSerial();
        assertThat(s1, is(equalTo(1)));
        assertThat(s2, is(equalTo(2)));
    }

    @Test
    public void testInsererLigne() {
        ArrayList<Valeur> valeurs = new ArrayList<>();
        valeurs.add(new IntValeur(1));
        valeurs.add(new VarcharValeur("Alice"));
        table.insererLigne(valeurs);
        assertThat(table.getLignes().size(), is(equalTo(1)));
    }
}