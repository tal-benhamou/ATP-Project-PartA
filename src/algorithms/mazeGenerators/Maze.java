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

    public Maze(byte[] array){

        int rows = this.intoInteger(array,0);

        int columns = (array.length - 13)/rows;

        this.map = this.buildMazeFromBytes(array,rows,columns);
        if (array[4] == -127)
            this.start = new Position(1,0);
        else
            this.start = new Position(0,0);

        this.goal = new Position(this.intoInteger(array,5), this.intoInteger(array,9));
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


    /**
     * @return byte array
     * this func present the maze details in byte array like that
     * [numberOfrows, numberOfcolumns, startRow, startColumn, goalRow, goalColumn, the Maze]
     */
    public byte[] toByteArray(){
        int rows = this.map.length;
        int columns = this.map[0].length;

        int index;
        byte[] mazeInByte = new byte[this.map.length * this.map[0].length + 13];

        tobyte(mazeInByte, 0, rows);
        //tobyte(mazeInByte, 4, columns);
        if (this.start.getRowIndex() == 0)
            mazeInByte[4] = -128;
        else
            mazeInByte[4] = -127;
        tobyte(mazeInByte, 5, this.goal.getRowIndex());
        tobyte(mazeInByte, 9, this.goal.getColumnIndex());

        index = 13;

        // insert the maze to the byte array
        for (int i = 0 ; i < rows ; i++){
            for (int j = 0 ; j < columns ; j++){
                if (this.map[i][j] == 1)
                    mazeInByte[index] = 1;
                else
                    mazeInByte[index] = 0;
                index++;
            }
        }
        return mazeInByte;
    }

    public void tobyte(byte[] array, int index, int arg){
        for (int i = 0 ; i < 4 ; i++){
            if (arg - 255 >= 0 )
                array[index] = 127;
            else if(arg > 0)
                array[index] = (byte) (arg - 128);
            else
                array[index] = -128;
            arg = arg - 255;
            index++;
        }
    }
    public int intoInteger(byte[] array, int index){
        int sum = 0;
        for (int i = index; i < index + 4; i++){
            if (array[i] != 0)
                sum = sum + array[i] + 128;
        }
        return sum;
    }

    public int[][] buildMazeFromBytes(byte[] array, int row, int column){
        int[][] map = new int[row][column];
        int index = 13;
        for (int i = 0; i < row; i++){
            for (int j = 0 ; j < column ; j++){
                map[i][j] = array[index];
                index++;
            }
        }
        return map;
    }
}
