/*
 * The MIT License
 *
 * Copyright 2018 sebas.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package geneticalgorithm;



/**
 *
 * @author sebas
 */
public class GeneticAlgorithm {
    
    /** parametrage algorithm **/
    private int numberOfChromosome;
    private int tauxCrossingOver;
    private float tauxMutation;
    
    private int goal; //valeur à obtenir
    private int numberElements; //nombre de chiffre encodé
    
    private final static String encodage = "0123456789+-/%  ";
    private final static int sizeEncodage = 4;
    
    private Chromosome[] population;
    private int[] fitValue;
    private int bestIndex = 0;

    public GeneticAlgorithm(int goal,int numberElements, int numberOfChromosome, int tauxCrossingOver, float tauxMutation) {
        this.numberOfChromosome = numberOfChromosome;
        this.tauxCrossingOver = tauxCrossingOver;
        this.tauxMutation = tauxMutation;
        this.goal = goal;
        this.numberElements = numberElements;
        
        fitValue = new int[this.numberOfChromosome];
        population = new Chromosome[this.numberOfChromosome];
        for(int i = 0; i < this.numberOfChromosome; i++){
            population[i] = new Chromosome(numberElements, sizeEncodage);
        }
    }
    
    public void initiatePopulation() 
    {
        for(int i = 0; i < this.numberOfChromosome; i++)
            population[i].randomlyInit(); 
    }
    
    /**
     * Call correctly selection, crossover and mutation to create the new population
     */
    public void newGeneration()
    {
        
    }
    
    /**
     * Make a selection algorithme elitiste choose only half of the best
     */
    public void selection()
    {
        
    }
    
    /**
     * Crossover two Chromosome
     * randomly choose a point of crossover then split
     */
    public void CrossOver()
    {
        
    }
    
    /**
     * Mutation have a probability to change a nucleotide.
     */
    public void Mutation()
    {
        
    }
    
    
    /**
     * display value of the best Chromosome
     * @return the value of the chromosome
     */
    public String affiche() {
        
        String buffer = "";
        for(int j = 0; j < numberElements;j++)
        {        
            int temp = (j%2==0)?(int)(population[bestIndex].chromosome[j/2])>>4& 0xFF:(int)(population[bestIndex].chromosome[j/2])%16& 0xFF;
            buffer += encodage.charAt(temp);  //DEBUG
        }
        return buffer;
    }
    
    /**
     * Calculate the string and then the difference foreach chromosome
     * @return the best difference to the goal
     * @throws Exception 
     */
    public int fit() {
        
        int min=-1;
        
        for(int i = 0; i < this.numberOfChromosome; i++){
            int value=0;
            
            
            //Calcul
            String buffer = "";
            String valueString = "";
            int secondvalue=0, operation = 0;
            try
            {
            for(int j = 0; j < this.numberElements;j++)
            {         
                
                
                int temp = (j%2==0)?(int)(population[i].chromosome[j/2])>>4& 0xFF:(int)(population[i].chromosome[j/2])%16& 0xFF;
                buffer += encodage.charAt(temp);  //DEBUG
                
                if(temp < 10){
                  valueString += encodage.charAt(temp);
                }
                else if(temp <14){//Si on a un signe
                    secondvalue = Integer.parseInt(valueString);
                    valueString = "";
                    if (operation!=0 && secondvalue == 0) //two signs together error
                        throw new Exception("error");
                    
                    if (operation!=0){// make calculation
                        switch(operation){
                            case 10: //+
                                value += secondvalue;
                                break;
                            case 11: //-
                                value -= secondvalue;
                                break;
                            case 12: ///
                                value = value / secondvalue;
                                break;
                            case 13: //*
                                value = value % secondvalue;
                                break;
                        }
                    }
                    else{
                        value = secondvalue;
                        
                    }
                    
                    operation = temp;
                    secondvalue = 0;
                }
                
                
            }
            
            secondvalue = Integer.parseInt(valueString);
            if(secondvalue!=0)
            {
                if(operation!=0)//need calculation
                    switch(operation){
                            case 10: //+
                                value += secondvalue;
                                break;
                            case 11: //-
                                value -= secondvalue;
                                break;
                            case 12: ///
                                value = value / secondvalue;
                                break;
                            case 13: //%
                                value = value % secondvalue;
                                break;
                        }
                else //no calculation
                    throw new Exception("error");
                    
            }
            
            
            fitValue[i] = Math.abs(goal - value);
            if(min==-1 || fitValue[i]<min){
                min = fitValue[i];
                bestIndex = i;
            }
                
            System.out.println(buffer + "->" + fitValue[i]);
            }
            catch(Exception e)
            {
                fitValue[i] = -1;
                //System.out.println(buffer + "-> error");
            }
            
            
        }
        
        return min;
    }
            
}
    
            
      
