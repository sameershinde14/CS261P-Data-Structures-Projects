package Hashing;

public class UnionFind {
	int[] parents;

	UnionFind(int size) {
		parents = new int[size];
		for (int i = 0; i < parents.length; i++)
			parents[i] = i;
	}

	/**
	 * finds the parent of element i
	 * 
	 * @param i
	 * @return parent of i
	 */
	int find(int i) {
		while (parents[i] != i)
			i = parents[i];
		return i;
	}

	/**
	 * merges set of i with set of j find parent of i and j make j the parent of
	 * i
	 * 
	 * @param i
	 * @param j
	 * @return the root of the new set
	 */
	int union(int i, int j) {
		int sizei = 0, sizej = 0;
		while (parents[i] != i) {
			i = parents[i];
			sizei++;
		}
		while (parents[j] != j) {
			j = parents[j];
			sizej++;
		}

		if (i == j)
			return i;

		int root = 0;

		if (sizei > sizej) {
			parents[j] = i;
			root = i;
		} else {
			parents[i] = j;
			root = j;
		}
		return root;
	}

}
