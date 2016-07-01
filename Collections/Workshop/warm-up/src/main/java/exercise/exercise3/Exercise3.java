package exercise.exercise3;

import java.util.*;

/**
 * Created by Radu.Hoaghe on 04/20/2015.
 * <p>
 * Exercise 3: Fill three Set implementations that you know (Hint: they were described during
 * the earlier presentation) with the List<String> that is given to this class by
 * its constructor.
 * <p>
 * Check out the elements that the list mentioned above contains and then, add them
 * to your three Sets. After this check out the elements of your Sets. What do you
 * remark? What could be the reason?
 * <p>
 * Finally, add to the one of the three Sets some elements
 * that already exist in the Set (e.g add("that") and add("collection"))
 * <p>
 * To run your implementation, run the Exercise3Test class.
 */
public class Exercise3 {

    // List containing some elements that need to be added into the Set
    private List<String> listToAdd;
    private HashSet<String> setH;
    private TreeSet<String> setT, setTComparator;
    private LinkedHashSet<String> setL;

    public Exercise3(List<String> l) {
        listToAdd = l;
        setH = new HashSet<String>();
        setT = new TreeSet<String>();
        setTComparator = new TreeSet<String>(new Comparator<String>() {
            public int compare(String o1, String o2) {
                char[] arrO1 = o1.toCharArray();
                char[] arrO2 = o2.toCharArray();
                int sumO1 = 0, sumO2 = 0;

                for(Character ch : arrO1) {
                    sumO1 += ch;
                }

                for(Character ch : arrO2) {
                    sumO2 += ch;
                }

                return sumO1 - sumO2;
            }
        });
        setL = new LinkedHashSet<String>();
    }

    public void addElementsToSets() {

        System.out.print("The elements that will be added to the Sets: ");
        // TODO Exercise #3 a) Check the content of the elements you will add into the Set
        System.out.println(listToAdd);

        // TODO Exercise #3 b) add the elements from listToAdd to the Sets
        setH.addAll(listToAdd);
        setT.addAll(listToAdd);
        setTComparator.addAll(listToAdd);
        setL.addAll(listToAdd);

        // TODO Exercise #3 c) Check the content of the Sets
        System.out.print("\nThe elements contained in the first HashSet: ");
        System.out.println(setH);

        System.out.print("\nThe elements contained in the second TreeSet: ");
        System.out.println(setT);

        System.out.print("\nThe elements contained in the third LinkedHashSet: ");
        System.out.println(setL);

        System.out.println("\nThe elements contained in the TreeSet after inserting two duplicates: ");
        setT.add("collection");
        setT.add("that");

        // TODO Exercise #3 d) Add to the TreeSet two elements that already exist in the Set
        // TODO Exercise #3 d) and print again the TreeSet. What do you see?
        System.out.println(setT);

        System.out.print("\nThe elements contained in the TreeSet constructed with a Comparator: ");
        System.out.println(setTComparator);
    }
}
