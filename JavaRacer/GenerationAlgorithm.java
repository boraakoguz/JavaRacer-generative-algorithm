package JavaRacer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GenerationAlgorithm {
    public final int    POPULATION;
    public final int    NUMBER_OF_GENERATIONS = 10;
    public final double MUTATION_CHANCE = 0.1;
    public final int    MAX_MUTATION_COUNT = 5;
    public final int    ELITISM_NUMBER = 2;
    public final int    NUMBER_OF_GENERATIONS_PER_INCREASE = 10;
    static Random rand = new Random(0);
    ArrayList<Integer> eliteIndexes = new ArrayList<Integer>();
    int generationNumber = 0;
    int instructionSize = 20;
    Agent[] agents;

    public GenerationAlgorithm(Agent[] agents){
        this.agents = agents;
        this.POPULATION = agents.length;
        createPopulation();
    }
    public void checkGeneration(){
        if(agents[agents.length-1].isFinished){
            ArrayList<Double> fitnesses = getFitness();
            ArrayList<Double> weights = getWeights(fitnesses);
            ArrayList<Integer[]> parentsList = getParents(weights);
            for (Integer[] integers : parentsList) {
                System.out.println(Arrays.toString(integers));
            }
            System.out.printf("Generation completed number: %d\n",generationNumber);
            crossOver(parentsList);
            for(Agent agent : agents){
                agent.reset();
            }
            generationNumber++;
        }
    }
    public void createPopulation(){
        for (Agent agent : agents) {
            assignRandomInstructions(agent,instructionSize);
        }
    }
    public void assignRandomInstructions(Agent agent,int size){
        for(int i = 0; i<size;i++){
            agent.instructions.add(rand.nextInt(5));
        }
    }
    public ArrayList<Double> getFitness(){
        ArrayList<Double> fitness = new ArrayList<>();
        for (Agent agent : agents) {
            fitness.add(agent.getPoints());
        }
        ArrayList<Double> fitnessCopy = new ArrayList<>(fitness);
        Collections.sort(fitnessCopy, Collections.reverseOrder(null));
        for(int i = 0; i<ELITISM_NUMBER;i++){
            eliteIndexes.add(fitness.indexOf(fitnessCopy.get(i)));
        }
        return fitness;
    }
    public ArrayList<Double> getWeights(ArrayList<Double> fitness){
        double sum = 0;
        ArrayList<Double> weights = new ArrayList<Double>();
        for (double d : fitness) {
            sum += Math.exp(d);
        }
        for (int i = 0; i<fitness.size();i++) {
            double softMaxed = Math.exp(fitness.get(i))/sum;
            weights.add(softMaxed);
        }
        return weights;
    }
    public ArrayList<Integer[]> getParents(ArrayList<Double> weights){
        ArrayList<Integer[]> parentsList = new ArrayList<Integer[]>();
        double totalWeight = 0.0; //in case the sum of weights is not 1
        for (double i : weights) {
            totalWeight += i;
        }
        for(int i = 0; i<weights.size(); i++){
            Integer[] parents = new Integer[2];
            for(int j = 0; j<2;j++){
                int idx = 0;
                for (double r = Math.random() * totalWeight; idx < weights.size() - 1; ++idx) {
                    r -= weights.get(idx);
                    if (r <= 0.0) break;
                }
                parents[j] = idx;
            }
            parentsList.add(parents);
        }
        return parentsList;
    }
    public void crossOver(ArrayList<Integer[]> parents){
        ArrayList<ArrayList<Integer>> instructionsTemp = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i<POPULATION; i++){
            instructionsTemp.add(agents[i].instructions);
        }
        for (int i = 0; i<parents.size();i++) {
            Integer[] parentPair = parents.get(i);
            if(eliteIndexes.contains(parentPair[0])){
                continue;
            }
            if(parentPair[0] != parentPair[1]){
                int crossOverLength = rand.nextInt(10);
                int indexOfCrossing = rand.nextInt(agents[parentPair[0]].instructions.size()-crossOverLength);
                for(int j = indexOfCrossing; j<indexOfCrossing+crossOverLength;j++){
                    agents[parentPair[0]].instructions.set(j,instructionsTemp.get(parentPair[1]).get(j));
                }
            }
            if(Math.random()<MUTATION_CHANCE){
                int numberOfMutations = rand.nextInt(MAX_MUTATION_COUNT);
                for(int m = 0; m<numberOfMutations;m++){
                    int randomInstructionIndex = rand.nextInt(agents[parentPair[0]].instructions.size());
                    agents[parentPair[0]].instructions.set(randomInstructionIndex,rand.nextInt(5)); //randomize instructions
                }
            }
            
        } 
    }
}
