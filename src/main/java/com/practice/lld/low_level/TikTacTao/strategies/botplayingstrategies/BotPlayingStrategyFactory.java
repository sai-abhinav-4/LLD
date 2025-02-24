package com.practice.lld.low_level.TikTacTao.strategies.botplayingstrategies;

import com.practice.lld.low_level.TikTacTao.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
  public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel difficultyLevel){
      return switch (difficultyLevel){
          case EASY -> new EasyBotPlayingStrategy();
          case MEDIUM -> new MediumBotPlayingStrategy();
          case HARD -> new HardBotPlayingStrategy();
      };
  }
} 
