package CriticalThinking.Module2;

public interface BagInterface<T> {
	
	//adds 
    void add(T newEntry);
    T[] toArray();
    boolean isFull();
}
