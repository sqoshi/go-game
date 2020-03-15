System do przeprowadzania rozgrywek w grę Go. 
Ogólnie gra polega na rywalizacji dwóch graczy, gdzie drugim może być również BOT.
Celem jest zajęcie jak największego terytorium i zduszeniu jak największej ilości kamieni przeciwnika - zdobyciu jeńców.
```
Szczegółowy opis zasad i funkcjonalności znajduje się w pliku TPlab04.pdf
```
Aplikacja jest napisana w Javie opiera się na architekturze Klient- Serwer.
Interfejs graficzny GUI jest napisane w Jaview technologii Java FX. 
Serwer weryfikuje poprawność ruchów i pośredniczy w komunikacji. 
Aplkacja umożliwia też rozgrywkę z Botem, który wykonuje dość sensowne ruchy.
Bot wyszukuje na planszy grupę przeciwnika o jak najmniejszej ilości oddechów i tą właśnie próbuje zdusić, otoczyć. Jednocześnie obserwuje swoje własne kamienie(pionki), i w razie zauważenia redukcji własnych oddechów w kolejnych ruchach próbuje poszerzać grupę dołączając do atakowanej przez przeciwnika grupy nowe kamienie.( W zasadzie dość cięzko z nim wygrać)
Aplikacja jest otwarta na dalsze rozwijanie funkcjonalności. Gracz przy pomocy aplikacji klienckiej łączy się z serwerem, by dołączyć do gry i rpzeprowadzić rozgrywkę. 
```
W aplikacji zostały wykorzystane różne wzorce projektowe między innymi:
-singleton
-state
-observer
-builder / factory method
```
Wstępnie tworzony jest Serwer i otwierane jest Okno gracza, w którym wybieramy tryb oraz rozmiar planszy. 
Okno gracza jest Singletonem i jest tworzony przy użyciu wzorca Factory. Składa się z planszy - fields oraz kilku buttonów, których nasłuchuje server. Gracz wykonuje ruch, którego poprawność jest weryfikowana przez Serwer i dopuszcza do ruchu kolejnego gracza lub nie. W każdym ruchu plansza jest odświeżana w celu aktualizacji.

Obiekt gry.
Tworzona jest gra o zadanym wymiarze i nadawane są pierwotne parametry.
oraz tablice przechowujące numerację grupy, martwe kamienie oraz tablica podliczająca terytorium bieżace, oraz sama własciwa tablice obiektów kamieni, oraz ustalany jest state jako "granie". Istnieje rowniez tablica trojwymiarowa, ktora jest historia naszego boardu potrzebna w celu weryfikacji konfliktow.
Po kazdym ruchu tablica jest aktualizowana, sprawdzane są możliwe błędy isInsideBoard,isPositionAvaible
Następnie tworzymy nowy kamie”, który umieszczamy. Pobierani są jego sąsiedzi w celu ustalenia jednoznacznie grupy, ewentualnie połączenia kilku grup w jedną .
Następuje aktualizacja Historii. Na jej podstawie sprawdzamy, czy nie nastąpiło KO  lub gracz nie usiluje dokonac zakazanego ruchu - samobójstwa. Jeśli doszedł do tego miejsca ( nowy kamień) wstawiamy go na tablicę i sprawdzamy czy nie zlikwidował którejś z grup przeciwnika. Jeśli grupa przeciwnika ma zero oddechów znaczy, że została zniszczona i należy dodać punkty dla gracza, następnie zmieniamy Gracza itd. Można też dać state na pass lub surrender wtedy odpowiednio poddajemy sie lub oddajey ruch.
Kod jest testowany w package Tests.
