package com.mycompany;

public class Skiing {

	private static int maxLength = 0;
	private static int maxDrop = 0;

	public static void main(String[] args) {

		int[][] map = { { 4, 8, 7, 3 }, { 2, 5, 9, 3 }, { 6, 3, 2, 5 }, { 4, 4, 1, 6 } };
		longestPath(map, 4, 4);

		System.out.println("Max length = " + maxLength);
		System.out.println("Max drop = " + maxDrop);
	}

	private static void longestPath(int[][] map, int m, int n) {
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				skiingUtil(i, j, map, visited, m, n, 0, 0);
				resetVisited(visited);

			}
		}

	}

	private static void resetVisited(boolean[][] visited) {
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[i].length; j++) {
				visited[i][j] = false;
			}
		}
	}

	private static void skiingUtil(int i, int j, int[][] map, boolean[][] visited, int m, int n, Integer drop, Integer length) {
		visited[i][j] = true;
		// right
		if (j < n - 1 && map[i][j] > map[i][j + 1] && !visited[i][j + 1]) {
			skiingUtil(i, j + 1, map, visited, m, n, drop + (map[i][j] - map[i][j + 1]), ++length);
		}

		// down
		if (i < m - 1 && map[i][j] > map[i + 1][j] && !visited[i + 1][j]) {
			skiingUtil(i + 1, j, map, visited, m, n, drop + (map[i][j] - map[i + 1][j]), ++length);
		}

		// left
		if (j > 0 && map[i][j] > map[i][j - 1] && !visited[i][j - 1]) {
			skiingUtil(i, j - 1, map, visited, m, n, drop + (map[i][j] - map[i][j - 1]), ++length);
		}

		// up
		if (i > 0 && map[i][j] > map[i - 1][j] && !visited[i - 1][j]) {
			skiingUtil(i - 1, j, map, visited, m, n, drop + (map[i][j] - map[i - 1][j]), ++length);
		}

		// if it ends in coming out of mountain
		if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
			if (maxDrop < drop) {
				maxDrop = drop;
			}
			if (maxLength < length) {
				maxLength = length;
			}
		}

	}
}
