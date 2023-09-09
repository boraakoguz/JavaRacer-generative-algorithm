package JavaRacer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public class SaveLoader {
    public void loadSave(GenerationAlgorithm genAlg){
        try {
            File saveFile = new File("source/save.txt");
            Scanner scanner = new Scanner(saveFile).useLocale(Locale.US);
            if(scanner.hasNext()){
                genAlg.generationNumber = scanner.nextInt();
                genAlg.instructionSize = scanner.nextInt();
                genAlg.lastIncreasePoints = scanner.nextDouble();
                scanner.nextLine();
                String[] tempInstructions = scanner.nextLine().replaceAll("[\\[\\]]", "").split(", ");
                System.out.println(tempInstructions.length);
                for (String string : tempInstructions) {
                    int instruct = Integer.parseInt(string);
                    for(Agent agent : genAlg.agents){
                        agent.instructions.add(instruct);
                    }
                }
            }
            else{

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void save(GenerationAlgorithm genAlg){
        try {
            PrintStream saveFile = new PrintStream(new File("source/save.txt"));
            saveFile.println(genAlg.generationNumber);
            saveFile.println(genAlg.instructionSize);
            saveFile.println(genAlg.lastIncreasePoints);
            saveFile.print(genAlg.agents[genAlg.eliteIndexes.get(0)].instructions.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean hasSaveFile(){
        try {
            File saveFile = new File("source/save.txt");
            Scanner scanner = new Scanner(saveFile);
            if(scanner.hasNext()){
                scanner.close();
                return true;
            }
            scanner.close();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
