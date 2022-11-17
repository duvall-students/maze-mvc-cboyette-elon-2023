package searches;

import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import application.Maze;

public class BFS extends SearchAlgorithm{	

	// Keeps up with the child-parent trail so we can recreate the chosen path
	HashMap<Point,Point> childParent;

//	private Maze maze;					// The maze being solved
//	private Point goal;					// The goal Point - will let us know when search is successful
//	private Collection<Point> data;		// Data structure used to keep "fringe" points
//	private boolean searchOver = false;	// Is search done?
//	private boolean searchResult = false;	// Was it successful?
//	private Point current;				// Current point being explored


	public BFS(Maze mazeBlocks, Point startPoint, Point goalPoint){
		super(mazeBlocks, startPoint, goalPoint);
		data = new LinkedList<>();
		data.add(startPoint);
		childParent = new HashMap<>();
	}

	/*
	 * Algorithm for Breadth-First Search
	 */
//	public boolean step(){
//		// Don't keep computing after goal is reached or determined impossible.
//		if(searchOver){
//			colorPath();
//			return searchResult;
//		}
//		// Find possible next steps
//		Collection<Point> neighbors = getNeighbors();
//		// Choose one to be a part of the path
//		Point next = chooseNeighbor(neighbors);
//		// mark the next step
//		if(next!=null){
//			maze.markPath(next);
//			recordLink(next);
//		}
//		// if no next step is found, mark current 
//		// state "visited" and take off queue.
//		else{	
//			maze.markVisited(current);
//			Queue<Point> queue = (Queue<Point>) data;
//			queue.remove();
//		}
//		resetCurrent();
//		checkSearchOver();
//		return searchResult;	
//	}

	/*
	 * This method defines which "neighbors" or next points can be reached in the maze from
	 * the current one.  
	 * 
	 * In this method, the neighbors are defined as the four squares immediately to the north, south,
	 * east, and west of the current point, but only if they are in the bounds of the maze.  It does 
	 * NOT check to see if the point is a wall, or visited.  
	 * 
	 * Any other definition of "neighbor" indicates the search subclass should override this method.
	 */


	/*
	 * This method defines the neighbor that gets chosen as the newest "fringe" member
	 * 
	 * It chooses the first point it finds that is empty.
	 */
	
//	@Override
//	protected Point chooseNeighbor(Collection<Point> neighbors){
//		for(Point p: neighbors){
//			if(maze.get(p)==Maze.EMPTY){
//				return p;
//			}
//		}
//		return null;
//	}

	/*
	 * In addition to putting the new node on the data structure, 
	 * we need to remember who the parent is.
	 */
	
	@Override
	protected void recordLink(Point next){	
		Queue<Point> queue = (Queue<Point>) data;
		queue.add(next);
		childParent.put(next,current);
	}

	/*
	 * The new node is the one next in the queue
	 */
	
	@Override
	protected void resetCurrent(){
		Queue<Point> queue = (Queue<Point>) data;
		current = queue.peek();
	}


	/*
	 * Search is over and unsuccessful if there are no more fringe points to consider.
	 * Search is over and successful if the current point is the same as the goal.
	 */

	/*
	 * Tells me when the search is over.
	 */

	/*
	 * Use the trail from child to parent to color the actual chosen path
	 */
	private void colorPath(){
		Point step = goal;
		maze.markPath(step);
		while(step!=null){
			maze.markPath(step);
			step = childParent.get(step);
		}
	}
	
	@Override
	protected void performColorPath() {
		colorPath();
	}
	
	@Override
	protected void executeNoNextStepFound() {
		maze.markVisited(current);
		Queue<Point> queue = (Queue<Point>) data;
		queue.remove();
	}






}
