package JavaRacer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GenerationAlgorithm {
    public final int    POPULATION;
    public final int    NUMBER_OF_GENERATIONS = 1000;
    public final double MUTATION_CHANCE = 0.4;
    public final int    MAX_MUTATION_COUNT = 8;
    public final int    ELITISM_NUMBER = 2;
    public final int    NUMBER_OF_GENERATIONS_PER_INCREASE = 10;
    public final int    INSTRUCTION_SIZE_INCREASE = 10;
    public final boolean LOAD_FILE = true;
    static Random rand = new Random();
    SaveLoader saveLoader = new SaveLoader();
    ArrayList<Integer> eliteIndexes = new ArrayList<Integer>();
    int generationNumber = 0;
    int instructionSize = 20;
    double improvement = 0;
    double lastIncreasePoints = 0;
    int failedIncreaseCounter = 0;
    double bestFit = 0;
    Agent[] agents;

    public GenerationAlgorithm(Agent[] agents){
        this.agents = agents;
        this.POPULATION = agents.length;
        if(LOAD_FILE&&saveLoader.hasSaveFile()){
            saveLoader.loadSave(this);
        }
        else{
            createPopulation();
        }
    }
    public void checkGeneration(){
        if(agents[agents.length-1].isFinished){
            ArrayList<Double> fitnesses = getFitness();
            ArrayList<Double> weights = getWeights(fitnesses);
            ArrayList<Integer[]> parentsList = getParents(weights);
            this.improvement = fitnesses.get(eliteIndexes.get(0)) - this.bestFit;
            this.bestFit = fitnesses.get(eliteIndexes.get(0));
            System.out.println("Best fit: " + fitnesses.get(eliteIndexes.get(0)) + " "+ fitnesses.get(eliteIndexes.get(1)));
            System.out.printf("Generation completed number: %d\n",generationNumber);
            crossOver(parentsList);
            for(Agent agent : agents){
                agent.reset();
            }
            generationNumber++;
            if(generationNumber>=instructionSize && generationNumber%NUMBER_OF_GENERATIONS_PER_INCREASE == 0 && fitnesses.get(eliteIndexes.get(0))-lastIncreasePoints>10){
                for(Agent agent : agents){
                    assignRandomInstructions(agent, INSTRUCTION_SIZE_INCREASE);
                }
                instructionSize += INSTRUCTION_SIZE_INCREASE;
                saveLoader.save(this);
                lastIncreasePoints = fitnesses.get(eliteIndexes.get(0));
                failedIncreaseCounter = 0;
            }
            else{
                failedIncreaseCounter += 1;
            }
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
        eliteIndexes.clear();
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
        double mutationAgression = 0;
        if(improvement<0.5){
            mutationAgression = 0.2;
            System.out.println("Mutation Agression Activated");
        }
        for(int i = 0; i<POPULATION; i++){
            instructionsTemp.add(new ArrayList<Integer>(agents[i].instructions));
        }
        for (int i = 0; i<parents.size();i++) {
            if(eliteIndexes.contains(i)){
                continue;
            }
            Integer[] parentPair = parents.get(i);
            int crossOverLength = rand.nextInt(10);
            int indexOfCrossing = rand.nextInt(agents[parentPair[0]].instructions.size()-crossOverLength);
            ArrayList<Integer> instruction1 = new ArrayList<Integer>(instructionsTemp.get(parentPair[0]));
            ArrayList<Integer> instruction2 = new ArrayList<Integer>(instructionsTemp.get(parentPair[1]));
            for(int j = indexOfCrossing; j<indexOfCrossing+crossOverLength;j++){
                instruction1.set(j, instruction2.get(j));
            }
            agents[i].instructions.clear();
            agents[i].instructions.addAll(instruction1);
            if(Math.random()<MUTATION_CHANCE+mutationAgression){
                int numberOfMutations = rand.nextInt(MAX_MUTATION_COUNT) + (int)mutationAgression*10;
                int startOfMutations;
                if(failedIncreaseCounter>20){
                    startOfMutations = 40;
                }
                else{
                    startOfMutations = 20;
                }
                for(int m = 0; m<numberOfMutations;m++){
                    int randomInstructionIndex = rand.nextInt(agents[i].instructions.size()-startOfMutations,agents[i].instructions.size());
                    agents[i].instructions.set(randomInstructionIndex,rand.nextInt(5)); //randomize instructions
                }
            }
        } 
    }
}
