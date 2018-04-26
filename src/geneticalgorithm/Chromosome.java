/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.util.Random;

/**
 *
 * @author sebas
 */
public class Chromosome {
    
    protected byte[] chromosome;
    protected int valueSize;
    protected int numberValue;

    /**
    *Constructor of the class chromosome
    * @param size
    *       number of element inside a chromosome
    * @param valueSize
    *       Size of a data
    */
    public Chromosome(int size, int valueSize) {
        this.valueSize = valueSize;
        
        numberValue = ((size*valueSize)/8);
        if (((((float)size*valueSize)/8)*10)%10 > 0) //arrondis au superieur
            numberValue++;
        
        this.chromosome = new byte[numberValue];
    }
    
    public void randomlyInit(){
        Random rand = new Random();
        rand.nextBytes(chromosome);
    }

    
    
}
