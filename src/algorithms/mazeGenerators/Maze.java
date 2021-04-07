package algorithms.mazeGenerators;

import algorithms.search.ISearchable;

public class Maze {

    private int[][] map;
    private Position start;
    private Position goal;

    public Maze(Position start, Position goal, int[][]map) {
        this.start = start;
        this.goal = goal;
        this.map = map;
    }
    public Maze(int[][] map){
        this.map = map;
        this.start = null;
        this.goal = null;
    }

    public int[][] getMap() {
        return map;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public void setGoal(Position goal) {
        this.goal = goal;
    }

    public Position getStartPosition() {
        return start;
    }

    public Position getGoalPosition() {
        return goal;
    }

    public void print(){
        for (int i = 0; i < map.length; i++) {
            System.out.print("{ ");
            for (int j = 0; j < map[0].length; j++) {

                if (i == start.getRowIndex() && j == start.getColumnIndex())
                    System.out.print('S' + " ");
                else if (i == goal.getRowIndex() && j == goal.getColumnIndex())
                    System.out.print('E'+" ");
                else
                    System.out.print(map[i][j]+" ");
            }
            System.out.println("}");
        }
    }
}
