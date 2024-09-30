package five;

public class BasicHashTable1 {

    public class MyStorage{
        String str;
        MyStorage next;

        MyStorage(){
            this.str="";
            this.next =null;
        }
        MyStorage(String str){
            this.str = str;
            this.next = null;
        }
        public boolean add(String aString){
            MyStorage head= this;
            int count= 1;
            while(head.next != null){
                head= head.next;
                count++;
            }
            head.next = new MyStorage(aString);
            if(count >=  5){
                return true;
            }
            return false;
        }

        public boolean contains(String key){
            MyStorage head= this;
            while(head!= null){
                if(head.str.equals(key))
                    return true;
                head = head.next;
            }
            return false;
        }

        public boolean remove(String key){
            MyStorage head = this;
            if(head == null)
                return false;
            if (head.str.equals(key)){
                head= head.next;
                return true;
            }
            MyStorage next = head.next;
            while(next!=null && !next.str.equals(key)){
                head =head.next;
                next=next.next;
            }
            if(next.str.equals(key)) {
                head.next=next.next;
                return true;
            }
            return false;
        }
        @Override
        public String toString(){
            String printString="[ ";
            MyStorage head = this;
            while (head != null){
                printString+=head.str+" ";
                head=head.next;
            }
            printString+="]";
            return printString;
        }

    }
    // Array of linked lists to handle collisions
    private MyStorage[] table;
    private int size;
    // Constructor
    public BasicHashTable1(int capacity) {
        table = new MyStorage[capacity];
        size = 0;
// Initialize each bucket as a linked list
        for (int i = 0; i < capacity; i++) {
            table[i] = new MyStorage();
        }
    }
    // Hash function to compute an index for the string
    private int hash(String key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    public void rehash(){
        BasicHashTable1 basicHashTable1 = new BasicHashTable1(table.length+10);
        for(int i=0; i<table.length; i++){
            MyStorage head = table[i];
            while(head!=null){
                basicHashTable1.insert(head.str);
                head = head.next;
            }
        }
        this.table = basicHashTable1.table;
        this.size = basicHashTable1.size;
    }
    // Method to insert a string into the hash table
    public void insert(String key) {
        int index = hash(key);
// Add the string to the appropriate linked list if it’s not already presen
        if (!table[index].contains(key)) {
            boolean rehashRequired = table[index].add(key);
            size++;
            if(rehashRequired)
                rehash();

        }
    }
    // Method to check if a string exists in the hash table
    public boolean contains(String key) {
        int index = hash(key);
        return table[index].contains(key);
    }
    // Method to remove a string from the hash table
    public void remove(String key) {
        int index = hash(key);
        if (table[index].remove(key)) {
            size--;
        }
    }
    // Get the current size of the hash table
    public int getSize() {
        return size;
    }
    // Print the contents of the hash table
    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.println("Bucket " + i + ": " + table[i]);
        }
    }
    public static void main(String[] args) {
        BasicHashTable1 hashTable = new BasicHashTable1(10);
// Insert strings
        hashTable.insert("apple");
        hashTable.insert("banana");
        hashTable.insert("orange");
        hashTable.insert("oranga");
        hashTable.insert("orangb");
        hashTable.insert("orangc");
        hashTable.printTable();
        hashTable.insert("orangd");
//        hashTable.printTable();
        hashTable.insert("orangf");
        hashTable.insert("orangg");
        hashTable.insert("oranggg");
        hashTable.insert("orangggg");
        hashTable.insert("oranggggg");
        hashTable.insert("orangggggg");
        hashTable.insert("oranggggggg");
        hashTable.insert("orangggggggg");
        hashTable.insert("oranggggggggg");
        hashTable.insert("orangggggggggg");
        hashTable.insert("orangggggggggg");
        hashTable.printTable();

        hashTable.insert("oranggggggggggg");
        hashTable.insert("orangggggggggggg");
        hashTable.insert("oranggggggggggggg");
        hashTable.insert("orangggggggggggggg");
        hashTable.insert("oranggggggggggggggg");
        hashTable.insert("orangggggggggggggggg");
        hashTable.insert("oranggggggggggggggggg");
        hashTable.insert("orangggggggggggggggggg");
        hashTable.insert("oranggggggggggggggggggg");
        hashTable.insert("orangggggggggggggggggggg");
        hashTable.insert("oranggggggggggggggggggggg");
        hashTable.printTable();

        hashTable.insert("orangggggggggggggggggggggg");
        hashTable.insert("oranggggggggggggggggggggggg");
        hashTable.insert("orangggggggggggggggggggggggg");
        hashTable.insert("oranggggggggggggggggggggggggg");
        hashTable.insert("orangggggggggggggggggggggggggg");
        hashTable.insert("oranggggggggggggggggggggggggggg");
        hashTable.insert("orangggggggggggggggggggggggggggg");
        hashTable.insert("oranggggggggggggggggggggggggggggg");
        hashTable.insert("orangggggggggggggggggggggggggggggg");
        hashTable.insert("oranggggggggggggggggggggggggggggggg");
//        hashTable.printTable();
        hashTable.printTable();

// Check if strings exist
        System.out.println(hashTable.contains("apple"));
        System.out.println(hashTable.contains("grape"));
// true
// false
// Remove a string
        hashTable.remove("banana");
// Print the hash table contents
        hashTable.printTable();
// Get the size
        System.out.println("Size: " + hashTable.getSize()); // 2
    }
}