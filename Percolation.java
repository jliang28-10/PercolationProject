import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public boolean[][] grid;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        grid = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                grid[i][j]=false;
            }
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if(row<1 ||row>grid.length )
            throw new IllegalArgumentException("row must be in range between 1 and n inclusive"+ row);
        if(col<1||col>grid[0].length)
            throw new IllegalArgumentException("col must be in range between 1 and n inclusive"+ col);
        grid[row-1][col-1]=true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(row<1 ||row>grid.length )
            throw new IllegalArgumentException("row must be in range between 1 and n inclusive"+ row);
        if(col<1||col>grid[0].length)
            throw new IllegalArgumentException("col must be in range between 1 and n inclusive"+ col);
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){

    }

    // returns the number of open sites
    public int numberOfOpenSites(){

    }

    // does the system percolate?
    public boolean percolates(){

    }

    public static void main(String[] args) {

    }
}
