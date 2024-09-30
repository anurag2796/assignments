package five;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyStrings {

    // Fields
    private String str;
    public static int instanceCount;
    private Map<Character, ArrayList<Integer>> indexStore;

    // Constructor for empty string
    public MyStrings() {
        this.str = "";
        instanceCount++;
    }

    // Constructor with a string
    public MyStrings(String aString) {
        this.str = aString;
        instanceCount++;
        indexStore = new HashMap<>();
        setIndexStore(0);
    }

    // Deep copy constructor (creates an independent copy)
    public MyStrings(MyStrings aString) {
        this.str = "";
        for (int i = 0; i < aString.str.length(); i++) {
            this.str += aString.str.charAt(i); // Manually copy each character
        }
        instanceCount++;
        indexStore = new HashMap<>();
        setIndexStore(0);
    }

    // Method to update indexStore for characters in the string
    private void setIndexStore(int index) {
        if (index == 0) {
            indexStore.clear(); // Clear index store for fresh input
        }

        for (int i = index; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            if (indexStore.containsKey(currentChar)) {
                ArrayList<Integer> indexes = indexStore.get(currentChar);
                indexes.set(0, indexes.get(0) + 1); // Update the count
                indexes.add(i); // Add the index

                // Reset second index if it's -1
                if (indexes.get(1) == -1) {
                    indexes.set(1, indexes.get(0) - 1);
                }
            } else {
                ArrayList<Integer> indexes = new ArrayList<>();
                indexes.add(3);  // Initial count
                indexes.add(2);  // Placeholder index
                indexes.add(i);  // Add current index
                indexStore.put(currentChar, indexes);
            }
        }
    }

    // Method to count how many instances have been created
    public int howManyInstancesHaveBeenCreated() {
        return MyStrings.instanceCount;
    }

    // Custom equality check between two MyStrings objects
    public boolean equals(MyStrings anotherString) {
        if (this.str.length() != anotherString.str.length()) {
            return false;
        }

        for (int i = 0; i < this.str.length(); i++) {
            if (this.str.charAt(i) != anotherString.str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Custom equality check between MyStrings and standard String
    public boolean equals(String anotherString) {
        if (this.str.length() != anotherString.length()) {
            return false;
        }

        for (int i = 0; i < this.str.length(); i++) {
            if (this.str.charAt(i) != anotherString.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Replace the internal string with a new one (returns updated MyStrings)
    public MyStrings replace(String newStr) {
        this.str = "";
        for (int i = 0; i < newStr.length(); i++) {
            this.str += newStr.charAt(i); // Manually copy each character
        }
        setIndexStore(0); // Reset index store for the new string
        return this;
    }

    // Replace the string using another MyStrings instance (deep copy)
    public void replace(MyStrings newStr) {
        this.str = "";
        for (int i = 0; i < newStr.str.length(); i++) {
            this.str += newStr.str.charAt(i); // Manually copy each character
        }
        setIndexStore(0); // Reset index store for the new string
    }

    // Concatenate another MyStrings to this one (deep copy)
    public MyStrings concat(MyStrings newStr) {
        int currentLength = this.str.length();
        for (int i = 0; i < newStr.str.length(); i++) {
            this.str += newStr.str.charAt(i); // Append manually character by character
        }
        setIndexStore(currentLength); // Update index store from the current length
        return this;
    }

    // Find the index of the first occurrence of a given character
    public int indexOf(char aChar) {
        if (!indexStore.containsKey(aChar)) {
            return -1; // Character not found
        }

        ArrayList<Integer> indexes = indexStore.get(aChar);
        int currentIndex = indexes.get(1);

        if (currentIndex == indexes.get(0) - 1) {
            indexes.set(1, -1); // Reset after reaching the end
        } else if (currentIndex == -1) {
            return -1;
        } else {
            indexes.set(1, currentIndex + 1); // Move to the next index
        }

        return indexes.get(currentIndex);
    }

    // Override toString method for printing the internal string
    @Override
    public String toString() {
        return this.str;
    }

//    // Main method for testing the class
//    public static void main(String[] args) {
//        MyStrings A = new MyStrings("hello");
//        MyStrings B = A; // Shallow copy, both A and B point to the same object
//        B.replace("world");
//
//        System.out.println("A: " + A); // Output: "world" (since B modifies the same object as A)
//        System.out.println("B: " + B);
//
//        // To avoid this, use a deep copy:
//        MyStrings C = new MyStrings(A); // Deep copy
//        C.replace("universe");
//
//        System.out.println("A: " + A); // Output: "world"
//        System.out.println("C: " + C); // Output: "universe"
//    }

    public static void main(String args[]) {
        MyStrings aMystring = new MyStrings("aaa");
        MyStrings bMystring = new MyStrings("aba");
        MyStrings cMystring = new MyStrings("aba");
        System.out.println(aMystring);
        System.out.println(aMystring.equals("aaa"));
        System.out.println(aMystring.equals("baa"));
        System.out.println(aMystring.equals(bMystring));
        System.out.println(aMystring.equals(aMystring));
        System.out.println(aMystring.replace("tt"));
//        System.out.println(aMystring.replace(new MyString("tt")));
//        System.out.println(aMystring.concat(new MyString("xtt")));
        System.out.println(aMystring.concat(new MyStrings("xtt")));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        cMystring.concat(new MyStrings("a"));

        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        cMystring.concat(new MyStrings("a"));
        cMystring.concat(new MyStrings("a"));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));



    }
}

