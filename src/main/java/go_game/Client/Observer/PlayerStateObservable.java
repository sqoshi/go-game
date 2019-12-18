package go_game.Client.Observer;

/***
 * tutaj dodajemy lub usuwamy oberwartora do/z listy obiektow ktore maja
 *
 */
public interface PlayerStateObservable {
    void addObserver(PlayerStateObserver observer);
    void removeObserver(PlayerStateObserver observer);
}
