HOFMANN-ROTH-ZOLLER

1. Kompilieranweisung
	Ins Verzeichnis wechseln, das den Folder �src� enth�lt.
	Einen neuen Folder �bin� erstellen.
	Folgende Anweisung im Terminal eingeben:
	find . -name *.java > sources.txt && javac -d bin @sources.txt
	
	JAR erstellen f�r Mergesort
	jar cfm Mergesort.jar Manifest.txt -C bin/ .
	
	JAR erstellen f�r MergeInsertionSortTest
	jar cfm MergeInsertionSortTest.jar Manifest_MergeInsertionSortTest.txt -C bin/ .

	JARs laufen lassen
	java -jar Mergesort.jar
	java -jar MergeInsertionSortTest.jar


2. Executables in /code/bin
	- InsertionSortBoundary: Test der Grenze f�r Wechsel zwischen Mergesort und Insertionsort
	- MergeSort1: Vergleich der verschiedenen Mergesort-Varianten. Die Arraygr�sse wurde f�r die jar-Files klein gesetzt um eine lange Laufzeit zu verhindern.


3. Interpretation der Grafiken in /doc/