package TemplateMethod;

enum GameCode {
    CHESS,
    MONOPOLY
}

abstract class Game {
    private int playersAmount;
    protected abstract void initializeGame();
    protected abstract void playGame();
    protected abstract void endGame();
    protected abstract void printWinner();
    public final void playOneGame(int playersAmount){
        setPlayersAmount(playersAmount);
        initializeGame();
        playGame();
        endGame();
        printWinner();
    }
    public void setPlayersAmount(int playersAmount){
        this.playersAmount = playersAmount;
    }
}

class Chess extends Game {
    @Override
    protected void initializeGame() {

    }
    @Override
    protected void playGame() {

    }
    @Override
    protected void endGame() {

    }
    @Override
    protected void printWinner() {

    }
}

class Monopoly extends Game{
    @Override
    protected void initializeGame() {

    }
    @Override
    protected void playGame() {

    }
    @Override
    protected void endGame() {

    }
    @Override
    protected void printWinner() {

    }
}

class TemplateMethodMain {
    public static void main (String [] args){
        final GameCode gameCode = GameCode.CHESS;
        Game game;
        switch (gameCode){
            case CHESS :
                game = new Chess();
                break;
            case MONOPOLY :
                game = new Monopoly();
                break;
            default :
                throw new IllegalStateException();
        }
        game.playOneGame(2);
    }
}