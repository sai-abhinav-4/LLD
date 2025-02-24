package com.practice.lld.low_level.TikTacTao.models;
import com.practice.lld.low_level.TikTacTao.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Move> moves;
    private Board board;
    private List<Player> players;
    private int currentMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;
    private GameStatus gameStatus;
    private Player winner;

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        this.moves = new ArrayList<>();
        this.board = new Board(dimension);
        this.players = players;
        this.currentMovePlayerIndex=0;
        this.winningStrategies = winningStrategies;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getCurrentMovePlayerIndex() {
        return currentMovePlayerIndex;
    }

    public void setCurrentMovePlayerIndex(int currentMovePlayerIndex) {
        this.currentMovePlayerIndex = currentMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void printBoard(){
        this.board.print();
    }

    public static Builder getBuilder(){
        return new Builder();
    }
    private boolean validateMove(Cell cell){
        int row =  cell.getRow();
        int col =  cell.getCol();
        if( row<0 || col<0 || row >= board.getSize() || col>= board.getSize()){
            return false;
        }
        if(board.getBoard().get(row).get(col).equals(CellStatus.EMPTY)){
            return true;
        }
        return false;
    }

    public void makeMove(){
        Player currentPlayer = players.get(currentMovePlayerIndex);
        Cell proposedCell = currentPlayer.makeMove();
        if(!validateMove(proposedCell)){
            return;
        }
        Cell cellInBoard = board.getBoard().get(proposedCell.getRow())
                .get(proposedCell.getCol());

        cellInBoard.setCellStatus(CellStatus.FILLED);
        cellInBoard.setPlayer(currentPlayer);
        Move move = new Move(currentPlayer,cellInBoard);
        moves.add(move);

        for(WinningStrategy winningStrategy: winningStrategies){
            if(winningStrategy.checkWinner(board,move)){
                gameStatus= GameStatus.ENDED;
                winner= currentPlayer;
                return;
            }
        }

        if(moves.size() == (board.getSize() * board.getSize())){
            gameStatus = GameStatus.DRAW;
            return;
        }

        currentMovePlayerIndex = (currentMovePlayerIndex + 1)%players.size();


    }

    public static class Builder{

        private List<Player> players;
        private int dimension;
        private List<WinningStrategy> winningStrategies;

        private Builder(){}

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private boolean valid(){
            if(this.players.size()<2){
                return false;
            }
            if(this.players.size()!= this.dimension-1){
                return false;
            }
            int botCount=0;
            for(Player player: this.players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount>=2 || botCount==0){
                return false;
            }

            Set<Character>  existingSymbols = new HashSet<>();
            for(Player player : players){
                if(existingSymbols.contains(player.getSymbol().getaChar())){
                    return false;
                }
                existingSymbols.add(player.getSymbol().getaChar());
            }

            return true;
        }

        public Game build(){
            if(!valid()){
                throw new RuntimeException("Invalid params for game");
            }
            return new Game(dimension,players,winningStrategies);
        }
    }
    public void printResult(){
        if(gameStatus.equals(GameStatus.ENDED)){
            System.out.println("Game as ended.");
            System.out.println("Winner is : "+ winner.getName());
        }else{
            System.out.println("Game is Draw");
        }
    }
}

// 1. without exception all attribute are private.
// 2. Have getters and setters.
// 3. ensure all attributes initialized in constructor.
