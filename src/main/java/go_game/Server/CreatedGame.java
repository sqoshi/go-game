package go_game.Server;

public class CreatedGame {
    int id;
    Gamee.Player currentPlayer;
    Gamee.Player opponent;
    CreatedGame(Gamee.Player currentPlayer,int id){
        this.id=id;
        this.currentPlayer=currentPlayer;
    }

}
