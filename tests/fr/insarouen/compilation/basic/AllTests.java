package fr.insarouen.compilation.basic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.insarouen.compilation.basic.sgbd.AllTestsSGBD;
// import fr.insarouen.compilation.basic.sgbd.ressources.AllTestsRessources;


@RunWith(Suite.class)
@SuiteClasses({
    AllTestsSGBD.class,
})

public class AllTests {}