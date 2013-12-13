/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scaperothbacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mscapero
 */
public class ScaperothBackTracking {

    int[] currency = {1, 5, 10, 25};
    /*MAKE CHANGE FUNCTIONS*/
    /*===============================================================*/
    //print out all different ways to make change for the 
    //amunt of pennies

    public void makeChange(int amount) {
        List<Integer> list = new ArrayList();  // you should declare as List, not ArrayList

        /*add a position in the least for each coin value*/
        for (int i = 0; i < currency.length; i++) {
            list.add(0);
        }

        /*start it up*/
        findChangeRecursive(list, amount, 0);
    }

    /**
     * recursively finds and prints all all the different ways you can provide
     * change for the cent value "amount"
     *
     * @param list list to hold combination
     * @param amount value to break into change
     * @param index current index of list (start at 0)
     */
    public void findChangeRecursive(List<Integer> list, int amount, int index) {
        int change = getListValue(list);
        if (change == amount) {
            System.out.println(list.toString());
        } else {
            while (index < list.size()) {

                /*add another of the current coin*/
                list.set(index, list.get(index) + 1);

                /*is the new amount lte n?*/
                if (getListValue(list) <= amount) {
                    findChangeRecursive(list, amount, index);
                }

                /*gone too far, remove one of the curr coins*/
                list.set(index, list.get(index) - 1);

                /*next coin*/
                index++;
            }
        }
    }

    /**
     * return the total value of all coins in the list
     *
     * @param list holds the count of each coin
     * @return total sum of list values
     */
    public int getListValue(List<Integer> list) {
        int sum = 0, whichCurrencyValue = 0;
        /*add weights (values) to the counts in the list*/
        for (int d : list) {
            sum += d * this.currency[whichCurrencyValue];
            whichCurrencyValue++;
        }
        return sum;
    }
    /*===============================================================*/

    /*MAX SUBSET FUNCTIONS*/
    /*===============================================================*/
    /**
     * print the maximum sum of subset that does not exceed n
     *
     * @param list list of available candidates for sum
     * @param n max sum to shoot for
     * @return
     */
    public int maxSubsetSub(List<Integer> list, int n) {
        int sum = 0;
        sum = findSubsetRecursiveBnB(list, sum, 0, n);
        System.out.println("L = "+ list.toString());
        System.out.println("n="+n);
        System.out.println("Answer = "+sum+"\n");
        return sum;
    }

    /**
     * 
     * @param list
     * @param sum
     * @param level
     * @param n
     * @return 
     */
    private int findSubsetRecursiveBnB(List<Integer> list, int sum, int level, int n) {
        int temp_sum = sum;
        
        if (level == list.size()) {
            return sum;
        }
        temp_sum += list.get(level);
        level++;
        if (temp_sum <= n) {
            sum = temp_sum;
            sum = findSubsetRecursiveBnB(list, sum, level, n);
            
        } else {
            sum = findSubsetRecursiveBnB(list, sum, level, n);
        }
        return sum;
    }
    /*===============================================================*/

    /* show your test code here*/
    public static void main(String[] args) {
        ScaperothBackTracking sbt = new ScaperothBackTracking();

        /*makeChange*/
        sbt.makeChange(27);
        System.out.println("\n==================\n");
        
        /*Max Subsets*/
        List<Integer> list1 = new ArrayList();
        List<Integer> list2 = new ArrayList();

        list1 = Arrays.asList(6, 30, 8, 22, 7, 1, 14);
        list2 = Arrays.asList(6, 2, 6, 9, 1);

        sbt.maxSubsetSub(list1, 19);
        sbt.maxSubsetSub(list2, 30);


    }
}