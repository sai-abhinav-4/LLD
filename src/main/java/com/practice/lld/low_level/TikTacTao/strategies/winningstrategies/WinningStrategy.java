package com.practice.lld.low_level.TikTacTao.strategies.winningstrategies;

import com.practice.lld.low_level.TikTacTao.models.Board;
import com.practice.lld.low_level.TikTacTao.models.Move;

public interface WinningStrategy {
      boolean checkWinner(Board board, Move move);
}
