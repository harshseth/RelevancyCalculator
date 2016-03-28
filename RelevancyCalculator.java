import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hseth on 3/23/2016.
 * Default score range is 0-4. Where 0 indicates irrelevant product and 4 indicates most relevant
 */
public class RelevancyCalculator {
    List<Integer> inputValues = new ArrayList<Integer>();
    NormalizedDiscountedCumulativeGain normalizedDiscountedCumulativeGain = new NormalizedDiscountedCumulativeGain();

    /**
     * Read the file and populate input value list that will be used by other fucntions
     * @param filename
     */
    public void readFile(String filename){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line= null;
            while((line=bufferedReader.readLine())!= null){
                inputValues.add(Integer.parseInt(line));
            }
            normalizedDiscountedCumulativeGain.setRelevanceScore(inputValues);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHighestRelevantScore(int score){
        normalizedDiscountedCumulativeGain.setHighestRelevantScore(score);
    }

    public float calculateScore(){
        return normalizedDiscountedCumulativeGain.calculateNormalizedDiscountedCumulativeGain();
    }


    public static void main(String args[]){
        RelevancyCalculator relevancyCalculator = new RelevancyCalculator();
        String filename = "C:\\datasets\\air_compressor_craftsman_without_qt.txt";
        relevancyCalculator.readFile(filename);
        float output =relevancyCalculator.calculateScore();
        System.out.println(output+" is the value calculated");
    }
}
