
import geneticalgorithm.Chromosome;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author sebas
 */
public class Test {

    static final int NbrIteration = 3000;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int bestValue;
        
        geneticalgorithm.GeneticAlgorithm test = new geneticalgorithm.GeneticAlgorithm(120, 8,50,70,0.1f);
        test.initiatePopulation();
        for(int i = 0; i < NbrIteration; i++){
            
            System.out.println("******** " + i + " Iteration ********");
            
            test.newGeneration();
            bestValue = test.fit();
            
            if(bestValue == 0){
                System.out.println("Found Element = " + test.affiche());
                
            }
        }
        //System.out.println(test);
        
    }
    
}
