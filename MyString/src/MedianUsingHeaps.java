import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


public class MedianUsingHeaps {
	
	
	
	private Comparator<Integer> maxHeapComparator = new MaxHeapComparator();
	private Queue<Integer> maxHeap = new PriorityQueue<Integer>(10,maxHeapComparator);
	private Queue<Integer> minHeap = new PriorityQueue<Integer>(10);
	
	public void addNumber(int randomNumber){
		
		//both are null
		if( maxHeap.isEmpty()  && minHeap.isEmpty() ){
			maxHeap.offer(randomNumber);
			return;
		}

		if(randomNumber < maxHeap.peek()){
			maxHeap.offer(randomNumber);
		}
		else {
			minHeap.offer(randomNumber);
		}
		
		//check if size is different by more than one
		int minHeapSize = minHeap.size();
		int maxHeapSize = maxHeap.size();
		
		if(maxHeapSize - minHeapSize > 1){
			minHeap.offer(maxHeap.poll());
		}
		
		if(minHeapSize - maxHeapSize > 1) {
			maxHeap.offer(minHeap.poll());
		}
			
		
	}
	
	public int getMedian() {
		
		if (maxHeap.isEmpty() && minHeap.isEmpty())
			return 0;
		
		if(maxHeap.isEmpty())
			return minHeap.peek();
		
		if(minHeap.isEmpty())
			return maxHeap.peek();
		
		if( maxHeap.size()== minHeap.size()){
			return (minHeap.peek() + maxHeap.peek())/2;
		}
		else {
			if(maxHeap.size() > minHeap.size()){
				return maxHeap.peek();
			}
			else {
				return minHeap.peek();
			}
		}
		
	}

}
