# --- Variables de configuration ---
SRC_DIR := src
TEST_SRC_DIR := tests
OUT_DIR := classes
TEST_OUT_DIR := classestest

JC := javac
JAVA := java

# --- Variables JavaCC ---
JAVACC := javacc
JJ_DIR := $(SRC_DIR)/fr/insarouen/compilation/basic/interpreteur
JJ_FILE := $(JJ_DIR)/InterpreteurCommandeSQL.jj

# Chemins pour JUnit (à modifier selon votre environnement)
JUNIT_JARS := /usr/share/java/junit4.jar:/usr/share/java/hamcrest-all.jar

# Configuration des Classpaths
CP_SRC := -sourcepath $(SRC_DIR)
CP_TEST := -sourcepath $(SRC_DIR):$(TEST_SRC_DIR) -classpath $(OUT_DIR):$(TEST_OUT_DIR):$(JUNIT_JARS)
RUN_CP := $(OUT_DIR):$(TEST_OUT_DIR):$(JUNIT_JARS)

# Flags de compilation
JFLAGS := -d $(OUT_DIR) $(CP_SRC)
JFLAGS_TEST := -d $(TEST_OUT_DIR) $(CP_TEST)

# --- Recherche automatique des sources ---
SOURCES := $(shell find $(SRC_DIR) -name "*.java")
TEST_SOURCES := $(shell find $(TEST_SRC_DIR) -name "*.java")

# --- Cibles (Targets) ---

# Cible par défaut : compile tout (projet + tests)
all: directories compilation compile_tests

directories:
	@mkdir -p $(OUT_DIR)
	@mkdir -p $(TEST_OUT_DIR)

# Compilation du code principal
compilation: directories
	@echo "Compilation des sources..."
	$(JC) $(JFLAGS) $(SOURCES)

# Compilation des tests (dépend de la compilation principale)
compile_tests: compilation
	@echo "Compilation des tests unitaires..."
	$(JC) $(JFLAGS_TEST) $(TEST_SOURCES)

# --- Exécution ---

# Lancer le programme principal (adapté à votre arborescence)
run: compilation
	@echo "Exécution du programme principal (Main)..."
	$(JAVA) -classpath $(OUT_DIR) fr.insarouen.compilation.basic.sgbd.MainTest
# --- Documentation et Archive ---

# Génération de la Javadoc (adapté à votre package racine)
javadoc:
	@echo "Génération de la documentation (Javadoc)..."
	@mkdir -p doc
	javadoc -d doc -sourcepath $(SRC_DIR) -subpackages fr.insarouen.compilation

# Création de l'archive JAR
jar: compilation
	@echo "Création de l'archive JAR..."
	@mkdir -p jar
	jar cvf jar/projet_compilation.jar -C $(OUT_DIR) .

# --- Exécution des tests ---

# Lancer la suite globale (AllTests.java) vue dans l'arborescence
test: compile_tests
	@echo "Exécution de toute la suite de tests..."
	$(JAVA) -classpath $(RUN_CP) org.junit.runner.JUnitCore fr.insarouen.compilation.basic.AllTests

# Lancer un test spécifique (Exemple : SGBDTests)
test_sgbd: compile_tests
	@echo "Exécution de SGBDTests..."
	$(JAVA) -classpath $(RUN_CP) org.junit.runner.JUnitCore fr.insarouen.compilation.basic.sgbd.SGBDTests

# Génération de l'analyseur syntaxique avec JavaCC
javacc:
	@echo "Génération de l'analyseur syntaxique..."
	$(JAVACC) -OUTPUT_DIRECTORY=$(JJ_DIR) $(JJ_FILE)

# Nettoyage
clean:
	@echo "Suppression des fichiers compilés..."
	rm -rf $(OUT_DIR)/*
	rm -rf $(TEST_OUT_DIR)/*
	rm -rf doc/*
	rm -rf jar/*
	@echo "Nettoyage terminé."

.PHONY: all directories javacc compilation compile_tests run javadoc jar test test_sgbd clean