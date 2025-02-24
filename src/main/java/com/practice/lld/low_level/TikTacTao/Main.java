package com.practice.lld.low_level.TikTacTao;

import com.practice.lld.low_level.TikTacTao.controllers.GameController;
import com.practice.lld.low_level.TikTacTao.models.*;
import com.practice.lld.low_level.TikTacTao.strategies.winningstrategies.OrderOneColumnWinningStrategy;
import com.practice.lld.low_level.TikTacTao.strategies.winningstrategies.OrderOneDiagonalWinningStrategy;
import com.practice.lld.low_level.TikTacTao.strategies.winningstrategies.OrderOneRowWinningStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //create a game
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();
        Game game;

        try {
            game = gameController.createGame(
              3,
                    List.of(new Player(new Symbol('X'),"rama", PlayerType.HUMAN),
                            new Bot(new Symbol('O'),"naman",BotDifficultyLevel.EASY) ),
                    List.of(
                        new OrderOneDiagonalWinningStrategy(),
                        new OrderOneRowWinningStrategy(),
                        new OrderOneColumnWinningStrategy()
                    )
            );
        }catch (Exception e){
            System.out.println("Somthing went wrong");
            return;
        }


        //while game status is in progress
        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            //print board
            gameController.displayBoard(game);
            //print if undo
            System.out.println("Do you want to undo? (y/n):");
            String input = scanner.next();
            // if yes -> call undo

            if(input.equalsIgnoreCase("y")){
                gameController.makeMove(game);
            }
            else {
                // move next player
                gameController.makeMove(game);
            }
        }

        gameController.printResult(game);
    }
}
