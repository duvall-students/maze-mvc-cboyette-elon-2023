package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import application.Maze;

public class SearchAlgorithm {

	protected Maze maze;					// The maze being solved
	protected Point goal;					// The goal Point - will let us know when search is successful
	protected Collection<Point> data;		// Data structure used to keep "fringe" points
	protected boolean searchOver;	// Is search done?
	protected boolean searchResult;	// Was it successful?
	protected Point current;				// Current point being explored
	
	public SearchAlgorithm(Maze mazeBlocks, Point startPoint, Point goalPoint){
		maze = mazeBlocks;
		goal = goalPoint;
		current = startPoint;
		maze.markPath(current);
		searchOver = false;
		searchResult = false;
	}
	
	public boolean step(){
		// Don't keep computing after goal is reached or determined impossible.
		//System.out.println(searchOver);
		if(searchOver){
			performColorPath();
			return searchResult;
		}
		// Find possible next steps
		Collection<Point> neighbors = getNeighbors();
		// Choose one to be a part of the path
		Point next = chooseNeighbor(neighbors);
		// mark the next step
		if(next!=null){
			maze.markPath(next);
			recordLink(next);
		}
		// if no next step is found, mark current 
		// state "visited" and take off queue.
		else{	
			executeNoNextStepFound();
		}
		resetCurrent();
		checkSearchOver();
		return searchResult;	
	}
	
	protected Collection<Point> getNeighbors() {
			List<Point> maybeNeighbors = new ArrayList<>();
			maybeNeighbors.add(new Point(current.x-1,current.y));
			maybeNeighbors.add(new Point(current.x+1,current.y));
			maybeNeighbors.add(new Point(current.x,current.y+1));
			maybeNeighbors.add(new Point(current.x,current.y-1));
			List<Point> neighbors = new ArrayList<>();
			for(Point p: maybeNeighbors){
				if(maze.inBounds(p)){
					neighbors.add(p);
				}
			}
			return neighbors;
	}
	
	protected void performColorPath() {

	}
	
	protected void executeNoNextStepFound() {
		
	}
	
	protected void recordLink(Point next) {
		
	}
	
	protected void checkSearchOver(){
		if(data!= null && data.isEmpty()) {
			searchOver = true;
			searchResult = false;
		}
		if(isGoal(current)){
			searchOver = true;
			searchResult = true;
		}
	}
	
	protected boolean isGoal(Point square){
		//System.out.println(square!= null && square.equals(goal));
		return square!= null && square.equals(goal);
	}
	
	
	protected void resetCurrent() {
		
	}
	
	protected Point chooseNeighbor(Collection<Point> neighbors) {
		for(Point p: neighbors){
			if(maze.get(p)==Maze.EMPTY){
				return p;
			}
		}
		return null;
	}
}
