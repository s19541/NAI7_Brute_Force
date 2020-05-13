import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BruteForce {
    private static int capacity;
    private static int quantity;
    static void findVector(String path){
        Item[]items=readFromFile(path);
        System.out.println("Found Vector:"+findBestCombination(items));
    }
    private static Item[] readFromFile(String path){
        Item[] items={};
        try{
            BufferedReader bf=new BufferedReader(new FileReader(path));
            String line=bf.readLine();
            capacity = Integer.parseInt(line.substring(0,line.indexOf(" ")));
            quantity = Integer.parseInt(line.substring(line.indexOf(" ")+1));
            items=new Item[quantity];
            for (int i = 0; i < items.length; i++) {
                items[i]=new Item();
            }
            line=bf.readLine();
            StringTokenizer stringTokenizer=new StringTokenizer(line,",");
            for (int i = 0; i < items.length; i++)
                items[i].value=Integer.parseInt(stringTokenizer.nextToken());
            line=bf.readLine();
            stringTokenizer=new StringTokenizer(line,",");
            for (int i = 0; i < items.length; i++)
                items[i].weight=Integer.parseInt(stringTokenizer.nextToken());

            bf.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return items;
    }
    static String findBestCombination(Item[]items){
        int maxValueSum=0;
        String bytes="";
        int numberOfCombinations = (int)Math.pow(2,items.length);
        for (int i = 1; i < numberOfCombinations; i++) {
            String combinationString = Integer.toBinaryString(i);
            String formattedCombinationString="";
            for (int j = combinationString.length()-1; j >=0 ; j--) {
                formattedCombinationString+=combinationString.substring(j,j+1);
            }
            for (int j = 0; j < quantity-combinationString.length(); j++) {
                formattedCombinationString+=0;
            }
            char[]combination=formattedCombinationString.toCharArray();
            int valueSum=0;
            int weightSum=0;
            for (int j = 0; j < combination.length; j++) {
                if(combination[j]=='1'){
                    valueSum+=items[j].value;
                    weightSum+=items[j].weight;
                }
            }
            System.out.println("CurrentBest:"+bytes+"("+maxValueSum+")");
            System.out.println(i+".Testing:"+formattedCombinationString+"  value:"+valueSum+" Weight:"+weightSum);
            if(weightSum<=capacity&&valueSum>maxValueSum){
                maxValueSum=valueSum;
                bytes=formattedCombinationString;
            }
        }
        return bytes;
    }

    static void printVector(int[]usedNumbers){
        for (int i = 0; i < quantity; i++) {
            boolean tmp=false;
            for (int j = 0; j <usedNumbers.length ; j++) {
                if(usedNumbers[j]==i)
                    tmp=true;
            }
            if(tmp)
                System.out.print(1);
            else
                System.out.print(0);
        }
        System.out.println();
    }

}
