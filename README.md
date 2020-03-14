System do przeprowadzania rozgrywek w grę Go. Ogólnie gra polega na rywalizacji dwóch graczy, gdzie drugim może być również BOT. Celem jest zajęcie jak największego terytorium i zduszeniu jak największej ilości kamieni przeciwnika - zdobyciu jeńców.
Gracz przy pomocy aplikacji klienckiej łączy się z serwerem, by dołączyć do gry i rpzeprowadzić rozgrywkę. 
```
Szczegółowy opis zasad i funkcjonalności znajduje się w pliku TPlab04.pdf
```
Aplikacja jest napisana w Javie opiera się na architekturze Klient- Serwer.
GUI zostało zrobione w technologii Java FX.  Serwer weryfikuje poprawność ruchów i pośredniczy w komunikacji. Aplkacja umożliwia też rozgrywkę z Botem, który wykonuje dość sensowne ruchy.
Bot wyszukuje na planszy grupę przeciwnika o jak najmniejszej ilości oddechów i tą właśnie próbuje zdusić, otoczyć. Jednocześnie obserwuje swoje własne kamienie(pionki), i w razie zauważenia redukcji własnych oddechów w kolejnych ruchach próbuje poszerzać grupę dołączając do atakowanej przez przeciwnika grupy nowe kamienie.( W zasadzie dość cięzko z nim wygrać)
Aplikacja jest otwarta na dalsze rozwijanie funkcjonalności.
```
W aplikacji zostały wykorzystane różne wzorce projektowe takie jak:
-singleton
-state
-observer
-builder / factory method
```
Kod jest testowany w package Tests.
