HOFMANN-ROTH-ZOLLER

1. Kompilieranweisung
	Ins Verzeichnis wechseln, das den Folder ãsrcÒ enthŠlt.
	Einen neuen Folder ãbinÒ erstellen.
	Folgende Anweisung im Terminal eingeben:
	find . -name *.java > sources.txt && javac -d bin @sources.txt
	
	JAR erstellen fŸr Mergesort
	jar cfm Mergesort.jar Manifest.txt -C bin/ .
	
	JAR erstellen fŸr MergeInsertionSortTest
	jar cfm MergeInsertionSortTest.jar Manifest_MergeInsertionSortTest.txt -C bin/ .

	JARs laufen lassen
	java -jar Mergesort.jar
	java -jar MergeInsertionSortTest.jar


2. Executables in /code/bin
	- InsertionSortBoundary: Test der Grenze für Wechsel zwischen Mergesort und Insertionsort
	- MergeSort1: Vergleich der verschiedenen Mergesort-Varianten. Die Arraygrösse wurde für die jar-Files klein gesetzt um eine lange Laufzeit zu verhindern.


3. Interpretation der Grafiken in /doc/