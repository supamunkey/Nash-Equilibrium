package model;

public class Strategy {

    private int[] playerStrategies = new int[2];
    private boolean[] nashCandidates = new boolean[2];

    public int[] getStrategies() {
        return playerStrategies;
    }

    public int getStrategyWeight(int index) {
        return playerStrategies[index];
    }

    public boolean[] getNashCandidates() {
        return nashCandidates;
    }

    /**
     * @param index    The index of a player
     * @param strategy The players strategy for given option
     */
    public void addStrategy(int index, int strategy) {
        playerStrategies[index] = strategy;
    }

    /**
     * @param index The index of a Nash candidate
     */
    public void setNashCandidateToTrue(int index) {
        nashCandidates[index] = true;
    }

    public boolean isNashEquilibrium() {
        return nashCandidates[0] && nashCandidates[1];
    }

    @Override
    public String toString() {
        return String.format("|\t(\t%2s  ,  %2s\t)\t|", playerStrategies[0], playerStrategies[1]);
    }
}
