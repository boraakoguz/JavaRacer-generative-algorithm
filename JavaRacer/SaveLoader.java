package JavaRacer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveLoader {
    public void loadSave(GenerationAlgorithm genAlg){
        try {
            File saveFile = new File("source/save.txt");
            Scanner scanner = new Scanner(saveFile);
            genAlg.generationNumber = scanner.nextInt();
            genAlg.instructionSize = scanner.nextInt();
            ArrayList<Integer> tempInstructions = new ArrayList<Integer>();
            scanner.nextLine().trim().split(null);
            System.out.println(tempInstructions.toString());
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
            saveFile.print(genAlg.agents[genAlg.eliteIndexes.get(0)].instructions.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
