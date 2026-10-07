package fr.insarouen.compilation.basic.sgbd;


import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.not;

import java.util.ArrayList;
import fr.insarouen.compilation.basic.sgbd.ressources.*;
import fr.insarouen.compilation.basic.sgbd.*;


public class LigneTests {
    private Ligne ligne;
    private ArrayList<Valeur> valeursInitiales;

    @Before
    public void setup(){
        valeursInitiales = new ArrayList<>();
        valeursInitiales.add(new IntValeur(1));
        valeursInitiales.add(new VarcharValeur("Yesmina"));
        valeursInitiales.add(new SerialValeur(100));
        ligne = new Ligne(valeursInitiales);
    }
    
    @After
    public void apresTests(){
        valeursInitiales = null;
        ligne = null;
    }
    
    @Test
    public void uneLigneEstCorrectementConstruite() {
        assertThat(ligne, is(notNullValue()));
    }

    @Test
    public void constructionAvecUneListeVide() {
        Ligne ligneVide = new Ligne(new ArrayList<>());
        assertThat(ligneVide, is(notNullValue()));
    }

    @Test
    public void getValeurRetourneLaPremiereValeur() {
        Valeur v1 = ligne.getValeur(0);
        Valeur v2 = ligne.getValeur(1);
        Valeur v3 = ligne.getValeur(2);
        assertThat(v1, is(notNullValue()));
        assertThat(v1.getType(), is(equalTo(TypeSQL.INT)));
        assertThat(v2, is(notNullValue()));
        assertThat(v2.getType(), is(equalTo(TypeSQL.VARCHAR)));
        assertThat(v3, is(notNullValue()));
        assertThat(v3.getType(), is(equalTo(TypeSQL.SERIAL)));
    }
    @Test
    public void setValeursTest() {
        IntValeur nouvelleValeur = new IntValeur(42);
        ligne.setValeurs(0, nouvelleValeur);
        assertThat(ligne.getValeur(0).getType(), is(equalTo(TypeSQL.INT)));
    }

    @Test
    public void modifierListeOriginaleNeModifiePasLigne() {
        valeursInitiales.add(new IntValeur(999));
        assertThat(ligne.getValeur(0).getType(), is(equalTo(TypeSQL.INT)));
        assertThat(ligne.getValeur(1).getType(), is(equalTo(TypeSQL.VARCHAR)));
        assertThat(ligne.getValeur(2).getType(), is(equalTo(TypeSQL.SERIAL)));
    }

    @Test
    public void toStringRetourneUneChaineNonVide() {
        assertThat(ligne.toString(), is(notNullValue()));
        assertThat(ligne.toString().isEmpty(), is(false));
    }

    @Test
    public void toStringDUneLigneVideRetourneUneChaineValide() {
        Ligne ligneVide = new Ligne(new ArrayList<>());
        assertThat(ligneVide.toString(), is(notNullValue()));
    }
}
