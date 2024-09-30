package five;

public class BasicHashTable2 {

    // Inner class representing a node in the linked list (used for collision handling)
    public class MyStorage {
        String value; // The string stored in this node
        MyStorage next; // Reference to the next node in the linked list

        // Default constructor
        MyStorage() {
            this.value = "";
            this.next = null;
        }

        // Constructor with an initial value
        MyStorage(String value) {
            this.value = value;
            this.next = null;
        }

        // Adds a new string to the linked list and returns true if rehashing is required
        public boolean add(String newString) {
            MyStorage current = this;
            int nodeCount = 1;

            // Traverse to the end of the linked list
            while (current.next != null) {
                current = current.next;
                nodeCount++;
            }

            // Add the new string at the end of the list
            current.next = new MyStorage(newString);

            // If the number of nodes in the list reaches 5, trigger rehashing
            return nodeCount >= 5;
        }

        // Checks if a string is contained in the linked list
        public boolean contains(String key) {
            MyStorage current = this;

            // Traverse the list and check for the string
            while (current != null) {
                if (current.value.equals(key)) {
                    return true;
                }
                current = current.next;
            }

            return false;
        }

        // Removes a string from the linked list and returns true if removal is successful
        public boolean remove(String key) {
            MyStorage current = this;

            // If the list is empty
            if (current == null) {
                return false;
            }

            // If the string to be removed is the head node
            if (current.value.equals(key)) {
                current = current.next;
                return true;
            }

            // Traverse the list to find and remove the string
            MyStorage previous = current;
            MyStorage nextNode = current.next;
            while (nextNode != null && !nextNode.value.equals(key)) {
                previous = nextNode;
                nextNode = nextNode.next;
            }

            // Remove the node
            if (nextNode != null && nextNode.value.equals(key)) {
                previous.next = nextNode.next;
                return true;
            }

            return false;
        }

        // Convert the linked list to a readable string format
        @Override
        public String toString() {
            StringBuilder printString = new StringBuilder("[ ");
            MyStorage current = this;

            while (current != null) {
                printString.append(current.value).append(" ");
                current = current.next;
            }

            printString.append("]");
            return printString.toString();
        }
    }

    // Array of linked lists (each entry is a MyStorage node) for the hash table
    private MyStorage[] table;
    private int size; // Number of elements in the hash table

    // Constructor to initialize the hash table with a given capacity
    public BasicHashTable2(int capacity) {
        table = new MyStorage[capacity];
        size = 0;

        // Initialize each entry in the array as an empty linked list
        for (int i = 0; i < capacity; i++) {
            table[i] = new MyStorage();
        }
    }

    // Hash function to compute the index of a string in the hash table
    private int hash(String key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    // Rehash the hash table when a bucket gets too large
    public void rehash() {
        BasicHashTable2 newHashTable = new BasicHashTable2(table.length + 10); // Create a larger table

        // Re-insert all elements from the old table to the new one
        for (int i = 0; i < table.length; i++) {
            MyStorage current = table[i];

            while (current != null) {
                newHashTable.insert(current.value);
                current = current.next;
            }
        }

        this.table = newHashTable.table;
        this.size = newHashTable.size;
    }

    // Insert a new string into the hash table
    public void insert(String key) {
        int index = hash(key);

        // If the string doesn't already exist in the bucket, add it
        if (!table[index].contains(key)) {
            boolean rehashRequired = table[index].add(key);
            size++;

            // If the bucket becomes too large, trigger rehashing
            if (rehashRequired) {
                rehash();
            }
        }
    }

    // Check if a string exists in the hash table
    public boolean contains(String key) {
        int index = hash(key);
        return table[index].contains(key);
    }

    // Remove a string from the hash table
    public void remove(String key) {
        int index = hash(key);

        // If the string is successfully removed, decrement the size
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

    // Main method for testing the hash table
    public static void main(String[] args) {
        BasicHashTable2 hashTable = new BasicHashTable2(10);

        // Insert strings into the hash table
        hashTable.insert("apple");
        hashTable.insert("banana");
        hashTable.insert("orange");
        hashTable.insert("oranga");
        hashTable.insert("orangb");
        hashTable.insert("orangc");
        hashTable.printTable();

        hashTable.insert("orangd");
        hashTable.insert("orangf");
        hashTable.insert("orangg");
        hashTable.insert("oranggg");
        hashTable.printTable();

        // Check if strings exist
        System.out.println(hashTable.contains("apple")); // true
        System.out.println(hashTable.contains("grape")); // false

        // Remove a string and print the table
        hashTable.remove("banana");
        hashTable.printTable();

        // Get and print the current size of the hash table
        System.out.println("Size: " + hashTable.getSize());
    }
}
