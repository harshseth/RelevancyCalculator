/**
 * Created by hseth on 3/23/2016.
 */

import java.util.ArrayList;
import java.util.List;

public class NormalizedDiscountedCumulativeGain {

    private static List<Integer> relevanceScore = new ArrayList<Integer>();
    private static int highestRelevantScore = 4;


    public void setRelevanceScore(List<Integer> inputValues) {
        this.relevanceScore = inputValues;
    }

    public static int getHighestRelevantScore() {
        return highestRelevantScore;
    }

    public static void setHighestRelevantScore(int highestRelevantScore) {
        NormalizedDiscountedCumulativeGain.highestRelevantScore = highestRelevantScore;
    }


    public static float caluclateDiscountedCumulativeGain(List<Integer> relevanceScore){
        int rank= 0;
        float dcg =0;
        for (int score: relevanceScore){
            dcg +=  (Math.pow(2,score) - 1)/(Math.log(rank+2)/Math.log(2));
            rank++;
        }
        return dcg;
    }

    public static float calculateNormalizedDiscountedCumulativeGain(){
        if(!relevanceScore.isEmpty()) {
            float dcg = caluclateDiscountedCumulativeGain(relevanceScore);
            float idcg = calucalteIdealDiscountedCumulativeGain(relevanceScore.size());
            return dcg/idcg;
        }
        else
            return (float) 0.0;

    }

    private static float calucalteIdealDiscountedCumulativeGain(int size) {
        float idcg=0;
        for (int i=0;i<size; i++){
            idcg += (Math.pow(2,highestRelevantScore) - 1.0)/(Math.log(i+2)/Math.log(2));
        }
       return idcg;
    }


}

