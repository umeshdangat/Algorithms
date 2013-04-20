import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MakingChange {

	public int getWays(int number) {

		Set<Integer> visited = new HashSet<Integer>();

		return getNumberOfWays(number, 0, visited);

		// int count = 0;
		// Queue<Integer> queue = new LinkedList<Integer>();
		// queue.add(number);
		// while(!queue.isEmpty()) {
		//
		// Integer front = queue.poll();
		//
		// if(front == 0) {
		// count++;
		// continue;
		// }
		//
		// if(front < 0) {
		// continue;
		// }
		//
		// if(visited.contains(front)) {
		// continue;
		// }
		//
		// visited.add(front);
		//
		// queue.add(front-25);
		// queue.add(front-10);
		// queue.add(front-5);
		// queue.add(front-1);
		// }
		// return count;
	}

	public int getNumberOfWays(int number, int count, Set<Integer> visited) {

	
		
		return count;
	}

	public static int makeChange(int n, int denom) {

		int next_denom = 0;

		switch (denom) {
		case 25:
			next_denom = 10;
			break;
		case 10:
			next_denom = 5;
			break;
		case 5:
			next_denom = 1;
			break;
		case 1:
			return 1;
		}
		int ways = 0;
		for (int i = 0; i * denom <= n; i++) {
			ways += makeChange(n - i * denom, next_denom);
		}
		return ways;
	}

}
