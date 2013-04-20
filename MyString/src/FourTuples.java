import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class FourTuples {

	private class Tuple {
		int i;
		int j;

		Tuple(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return Integer.toString(i) + "," + Integer.toString(j);

		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (this == obj)
				return true;
			if (!this.getClass().equals(obj.getClass()))
				return false;

			Tuple that = (Tuple) obj;
			if ((that.i == this.i) && (that.j == that.j))
				return true;

			return false;
		}

	}

	private class FourTupleAddition {

		List<Tuple> getTuples(int[] inputArray) {

			List<Tuple> tuples = new ArrayList<Tuple>();
			for (int i = 0; i < inputArray.length; i++) {
				for (int j = i + 1; j < inputArray.length; j++) {
					Tuple tuple = new Tuple(inputArray[i], inputArray[j]);
					tuples.add(tuple);
				}
			}

			Map<Integer, List<Tuple>> map = new HashMap<Integer, List<Tuple>>();
			for (Tuple tuple : tuples) {
				if (!map.containsKey(tuple.i + tuple.j)) {
					List<Tuple> tupleList = new ArrayList<Tuple>();
					tupleList.add(tuple);
					map.put(tuple.i + tuple.j, tupleList);
				} else {
					List<Tuple> tupleList = map.get(tuple.i + tuple.j);
					tupleList.add(tuple);
					map.put(tuple.i + tuple.j, tupleList);
				}

			}
			tuples.removeAll(tuples);

			Iterator<Entry<Integer, List<Tuple>>> it = map.entrySet()
					.iterator();

			while (it.hasNext()) {
				Entry<Integer, List<Tuple>> entry = it.next();
				if (entry.getValue().size() > 1) {
					tuples.addAll(entry.getValue());
				}
			}
			return tuples;
		}
	}

	@Test
	public void test() {
		int[] inputArray = new int[4];
		inputArray[0] = 10;
		inputArray[1] = 12;
		inputArray[2] = 4;
		inputArray[3] = 2;
		List<Tuple> expected = new ArrayList<Tuple>();
		expected.add(new Tuple(10, 4));
		expected.add(new Tuple(12, 2));
		FourTupleAddition fourTupleAddition = new FourTupleAddition();
		List<Tuple> actual = fourTupleAddition.getTuples(inputArray);
		assertArrayEquals(expected.toArray(), actual.toArray());
	}

}
