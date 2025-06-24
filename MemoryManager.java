package service; // package declaration

public class MemoryManager { // shows how memory allocation and deallocation is managed
    // array showing fixed size memory blocks
    // each element shows available memory in that block
    private final int[] memoryBlocks = { 100, 200, 300 };
// allocates memory from the first suitable block that has enough space
    public int allocateMemory(int size) {
        for ( int i = 0; i < memoryBlocks.length; i++ ){
            // checks if current block has enough memory
            if ( memoryBlocks[i] >= size ){
                memoryBlocks[i] -= size;
                return i; // returns the index of the allocated block
            }
        }
        return -1; // returns  no suitable memory block found
    }

  // deallocates memory by adding the memory back to the block
    public void deallocateMemory(int index, int size) {
        // ensures the index is within valid bounds
        if ( index >= 0 && index < memoryBlocks.length ){
            memoryBlocks[index] += size;
        }
    }
}
