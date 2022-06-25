package su356076.corejava.rating.model;

public enum Distributions {
    TEST(40), QUIZ(20), LAB_WORK(10), PROJECT(30);
    public final int weight;
    private Distributions(int weight){
        this.weight = weight;
    }
}
