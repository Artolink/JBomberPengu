# JBomberPengu

Videogame written in java inspired by the online game "BomberPengu".


Authors:

**Emanuele Benedettini** - emanuele.benedettini@studio.unibo.it

**Marco Lombardi** - marco.lombardi15@studio.unibo.it

**Andrea Farneti** - andrea.farneti5@studio.unibo.it

**Naomi Conzo** - naomi.conzo@studio.unibo.it


## Istruzioni per import su Eclipse

* Clonare la repo `git clone ...`
* Assicurarsi di aver installato il plugin `e(fx)clipse` in Eclipse
* Importare il progetto `File -> Import -> Gradle -> Exixsting Gradle Project`
* Attendere la sincronizzazione di tutti i file Gradle

## Setup Gradle

Sono stati adottati tutti i plugin (CheckStyle, PMD, SpotBugs).

## Creazione ed esecuzione JAR

Generare il JAR mediante il comando: `./gradlew fatJar`. Il JAR verra'  esportato in `build/libs/... .jar`  
Eseguire il jar: `java -Dquantum.multithreaded=false -jar ... .jar`


## Known bugs

* Su MAC utilizzando Oracle JDK10, si riscontra una NullPointerException in fase di lancio, ciÃ² Ã¨ causato da un bug noto
corretto su OpenJDK ma a quanto pare non su OracleJDK. Si sconsiglia di testare su Mac con OracleJDK 10 [https://bugs.openjdk.java.net/browse/JDK-8204604]