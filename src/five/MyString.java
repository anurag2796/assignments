package five;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyString {

    String str;
    public static int instanceCount;
    Map<Character, ArrayList<Integer>> indexStore;

    public void setIndexStore(int index) {
        if (index == 0)
            this.indexStore.clear();
        for (int i = index; i < this.str.length(); i++) {
            if (indexStore.containsKey(this.str.charAt(i))) {
                ArrayList<Integer> indexes = indexStore.get(this.str.charAt(i));
                indexes.set(0, indexes.getFirst() + 1);
                indexes.add(i);
                if(indexStore.get(this.str.charAt(i)).get(1) == -1){
                    indexStore.get(this.str.charAt(i)).set(1,indexStore.get(this.str.charAt(i)).getFirst() - 1);
                }
            } else {
                ArrayList<Integer> indexes = new ArrayList<>();
                indexes.add(3);
                indexes.add(2);
                indexes.add(i);
                this.indexStore.put(this.str.charAt(i), indexes);
            }
        }
    }

    public MyString() {
        this.str = "";
        instanceCount++;
    }

    public MyString(String aString) {
        this.str = aString;
        instanceCount++;
        indexStore = new HashMap<>();
        setIndexStore(0);
    }


    public MyString(MyString aString) {
        this.str = aString.str;
        instanceCount++;
        indexStore = new HashMap<>();
        setIndexStore(0);
    }

    public int howManyInstancesHaveBeenCreated() {
        return MyString.instanceCount;
    }

    public boolean equals(MyString anotherString) {
        if (this.str.length() != anotherString.str.length())
            return false;
        for (int i = 0; i < this.str.length(); i++) {
            if (this.str.charAt(i) != anotherString.str.charAt(i))
                return false;
        }
        return true;
    }

    public boolean equals(String anotherString) {
        if (this.str.length() != anotherString.length())
            return false;
        for (int i = 0; i < this.str.length(); i++) {
            if (this.str.charAt(i) != anotherString.charAt(i))
                return false;
        }
        return true;
    }

    public MyString replace(String str) {
        this.str = str;
        this.setIndexStore(0);
        return this;
    }

    public void replace(MyString str) {
        this.str = str.str;
        this.setIndexStore(0);
    }

    @Override
    public String toString() {
        return this.str;
    }

    public MyString concat(MyString str) {
        int length = this.str.length();
        this.str += str.str;
        setIndexStore(length);
        return this;

    }

    public int indexOf(char aChar) {
        if (!this.indexStore.containsKey(aChar)) {
            return -1;
        }
        int index = this.indexStore.get(aChar).get(1);
        if (index == this.indexStore.get(aChar).getFirst() - 1)
            this.indexStore.get(aChar).set(1, -1);
        else if(this.indexStore.get(aChar).get(1) == -1){
            return -1;
        }else
            this.indexStore.get(aChar).set(1,index+1);
        return this.indexStore.get(aChar).get(index);
    }

    public static void main(String args[]) {
        MyString aMystring = new MyString("aaa");
        MyString bMystring = new MyString("aba");
        MyString cMystring = new MyString("aba");
        System.out.println(aMystring);
        System.out.println(aMystring.equals("aaa"));
        System.out.println(aMystring.equals("baa"));
        System.out.println(aMystring.equals(bMystring));
        System.out.println(aMystring.equals(aMystring));
        System.out.println(aMystring.replace("tt"));
//        System.out.println(aMystring.replace(new MyString("tt")));
//        System.out.println(aMystring.concat(new MyString("xtt")));
        System.out.println(aMystring.concat(new MyString("xtt")));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        cMystring.concat(new MyString("a"));

        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        cMystring.concat(new MyString("a"));
        cMystring.concat(new MyString("a"));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));
        System.out.println("--- " + cMystring.indexOf('a'));



    }
}