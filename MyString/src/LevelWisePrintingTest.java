import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class LevelWisePrintingTest {

	@Test
	public void testGetLevelWise() {
		LevelWisePrinting levelWisePrinting = new LevelWisePrinting();
		int[] values = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		TreeNode root = buildTree(values, 0, values.length-1);
		List<List<TreeNode>> treeNode = levelWisePrinting.getLevelWise(root);
		
		assertEquals(3,treeNode.size());
		assertEquals(1,treeNode.get(0).size());
		assertEquals(2,treeNode.get(1).size());
		assertEquals(4,treeNode.get(2).size());

	}

	public TreeNode buildTree(int[] values, int low, int high) {
		if (low > high) {
			return null;
		}
		int mid = (low + high) / 2;
		TreeNode treeNode = new TreeNode(values[mid]);
		treeNode.left = buildTree(values, low, mid - 1);
		treeNode.right = buildTree(values, mid + 1, high);
		return treeNode;

	}

}
