package fr.insarouen.compilation.basic.sgbd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.insarouen.compilation.basic.sgbd.LigneTests;
import fr.insarouen.compilation.basic.sgbd.TableTests;
import fr.insarouen.compilation.basic.sgbd.SGBDTests;

@RunWith(Suite.class)
@SuiteClasses({
    SGBDTests.class,
    TableTests.class,
    LigneTests.class,
})

public class AllTestsSGBD {}