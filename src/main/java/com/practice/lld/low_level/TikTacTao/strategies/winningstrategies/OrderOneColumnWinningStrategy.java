package com.practice.lld.low_level.TikTacTao.strategies.winningstrategies;

import com.practice.lld.low_level.TikTacTao.models.Board;
import com.practice.lld.low_level.TikTacTao.models.Move;

public class OrderOneColumnWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }
}
