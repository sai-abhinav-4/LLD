package com.practice.lld.low_level.TikTacTao.strategies.botplayingstrategies;

import com.practice.lld.low_level.TikTacTao.models.Board;
import com.practice.lld.low_level.TikTacTao.models.Cell;

public interface BotPlayingStrategy {
    Cell makeMove(Board board);
}
