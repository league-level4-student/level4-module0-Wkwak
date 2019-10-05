package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		int x = (int) (Math.random() * width);
		int y = (int) (Math.random() * height);
		Cell rand = maze.getCell(x, y);
		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(rand);

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. Get an ArrayList of unvisited neighbors using the current cell and the
		// method below
		ArrayList<Cell> unvisitedNeighbors = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,
		if (unvisitedNeighbors.size() > 0) {
			System.out.println("working");
			// C1. select one at random.
			int posRand = (int) (Math.random() * unvisitedNeighbors.size());
			// C2. push it to the stack
			uncheckedCells.push(unvisitedNeighbors.get(posRand));
			// C3. remove the wall between the two cells
			removeWalls(currentCell, unvisitedNeighbors.get(posRand));
			// C4. make the new cell the current cell and mark it as visited
			currentCell = unvisitedNeighbors.get(posRand);
			currentCell.setBeenVisited(true);
			// C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		// D. if all neighbors are visited
		else if (unvisitedNeighbors.size() == 0) {
			// D1. if the stack is not empty
			if (uncheckedCells.size() > 0) {
				// D1a. pop a cell from the stack
				currentCell = uncheckedCells.pop();
				// D1b. make that the current cell

				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		System.out.println(c1.getX() + "," + c1.getY() + " " + c2.getX() + "," + c2.getY());
		if (c1.getX() == c2.getX() && c1.getY() == c2.getY() - 1) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		} else if (c2.getX() == c2.getX() && c1.getY() == c2.getY() + 1) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		} else if (c1.getY() == c2.getY() && c1.getX() == c2.getX() - 1) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		} else if (c1.getY() == c2.getY() && c1.getX() == c2.getX() + 1) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisted = new ArrayList<Cell>();
		
		if (c.getX()!=maze.getWidth()-1){
			Cell east = maze.getCell(c.getX() + 1, c.getY());
			if(!east.hasBeenVisited()) {
				unvisted.add(east);
			}
		}
		if (c.getX() != 0) { 
			Cell west = maze.getCell(c.getX() - 1, c.getY());
			if (!west.hasBeenVisited()) {
				unvisted.add(west);
			}
		}
		if(c.getY()!=0){
			Cell north = maze.getCell(c.getX(), c.getY() - 1);
			if(!north.hasBeenVisited()){
				unvisted.add(north);
			}
		}
		if(c.getY()!=maze.getHeight()-1){
			Cell south = maze.getCell(c.getX(), c.getY() + 1);
			if(!south.hasBeenVisited()) {
				unvisted.add(south);
			}
		}
		return unvisted;
	}
}
