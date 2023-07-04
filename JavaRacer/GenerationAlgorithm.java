package JavaRacer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GenerationAlgorithm {
    public final int POPULATION;
    public final int NUMBER_OF_GENERATIONS = 10;
    public final double MUTATION_CHANCE = 0.1;
    public final int ELITISM_NUMBER = 5;
    public final int NUMBER_OF_GENERATIONS_PER_INCREASE = 10;
    static Random rand = new Random(0);
    int[] eliteIndexes = new int[ELITISM_NUMBER];
    int generationNumber = 0;
    boolean sessionStarted = false;
    Agent[] agents;
    public GenerationAlgorithm(Agent[] agents){
        this.agents = agents;
        this.POPULATION = agents.length;
        createPopulation();
    }
    public void checkGeneration(){
        if(agents[agents.length-1].isFinished && !sessionStarted){
            getFitness();
            for(Agent agent : agents){
                agent.reset();
            }
            sessionStarted = true;
            generationNumber++;
        }
    }
    public void createPopulation(){
        for (Agent agent : agents) {
            assignRandomInstructions(agent,20);
        }
    }
    public void assignRandomInstructions(Agent agent,int size){
        for(int i = 0; i<size;i++){
            agent.instructions.add(rand.nextInt(5));
        }
    }
    public ArrayList<Integer> getFitness(){
        ArrayList<Integer> fitness = new ArrayList<>();
        for (Agent agent : agents) {
            fitness.add(agent.getPoints());
        }
        ArrayList<Integer> fitnessCopy = new ArrayList<>(fitness);
        Collections.sort(fitnessCopy, Collections.reverseOrder(null));
        for(int i = 0; i<ELITISM_NUMBER;i++){
            eliteIndexes[i] = fitness.indexOf(fitnessCopy.get(i));
        }
        System.out.println(fitness.toString());
        System.out.println(Arrays.toString(eliteIndexes));
        return fitness;
    }

}
