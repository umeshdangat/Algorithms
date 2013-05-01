import java.util.ArrayList;
import java.util.List;

public class LevelWisePrinting {

	List<List<TreeNode>> getLevelWise(TreeNode root) {
		List<List<TreeNode>> outerList = new ArrayList<List<TreeNode>>();
		List<TreeNode> first = new ArrayList<TreeNode>();
		first.add(root);
		outerList.add(first);

		for (int i = 0; i < outerList.size(); i++) {
			List<TreeNode> inner = outerList.get(i);
			List<TreeNode> nextLevel = new ArrayList<TreeNode>();
			for (TreeNode n : inner) {
				if (n.left != null) {
					nextLevel.add(n.left);
				}
				if (n.right != null) {
					nextLevel.add(n.right);
				}
			}
			if (!nextLevel.isEmpty()) {
				outerList.add(i + 1, nextLevel);
			}
		}

		return outerList;
	}

}
