package application;

import java.awt.Point;

import searches.*;

public class MazeController {

	private Maze maze;
	private MazeDisplay myMazeDisplay;
	// Where to start and stop the search
	private Point start;
	private Point goal;
	// The search algorithms
	private SearchAlgorithm search;
	private SearchFactory searchFactory;
//	private String search = "";		// This string tells which algorithm is currently chosen.  Anything other than 

	MazeController (int numRows, int numColumns, MazeDisplay mazeDisplay) {
		start = new Point(1,1);
		goal = new Point(numRows-2, numColumns-2);
		maze = new Maze(numRows, numColumns);
		myMazeDisplay = mazeDisplay;
		searchFactory = new SearchFactory();
	}
	
	public void newMaze() {
		maze.createMaze(maze.getNumRows(),maze.getNumCols());
		//search = "";
		//search = new SearchAlgorithm(maze, start, goal);
		myMazeDisplay.redraw();
	}
	
	public void doOneStep(double elapsedTime){
		if (search != null) {
			search.step();
		}
		
//		if(search.equals("DFS")) dfs.step();
//		else if (search.equals("BFS")) bfs.step();
//		else if (search.equals("Greedy")) greedy.step();
//		else if (search.equals("RandomWalk")) rand.step();
//		else if (search.equals("Magic")) magic.step();
		myMazeDisplay.redraw();
	}
	
	public void startSearch(String searchType) {
		maze.reColorMaze();
		search = searchFactory.makeSearch(searchType, maze, start, goal);
		
		// Restart the search.  Since I don't know 
		// which one, I'll restart all of them.
		
//		if(searchType.equals("DFS")) {
//			search = new DFS(maze, start, goal);
//		}
//		else if (searchType.equals("BFS")) {
//			search = new BFS(maze, start, goal);
//		}
//		else if (searchType.equals("Greedy")) {
//			search = new Greedy(maze, start, goal);
//		}
//		else if (searchType.equals("RandomWalk")) {
//			search = new RandomWalk(maze, start, goal);
//		}
//		else if (searchType.equals("Magic")) {
//			search = new Magic(maze, start, goal);
//		}
	}


	public int getCellState(Point position) {
		return maze.get(position);
	}
}
