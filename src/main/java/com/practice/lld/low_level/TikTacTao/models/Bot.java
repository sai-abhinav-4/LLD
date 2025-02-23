package com.practice.lld.low_level.TikTacTao.models;

public class Bot extends Player{

    private BotDifficultyLevel botDifficultyLevel;

    public Bot(Symbol symbol, String name) {
        super(symbol, name, PlayerType.BOT);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
