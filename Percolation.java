import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public boolean[][] grid;

    public int n;
    public WeightedQuickUnionUF gaoyu;
    // creates n-by-n grid, with all sites initially blocked

    public int conversion(int row, int col){
        return (grid.length)*(row-1)+col;
    }
    //checked
    public Percolation(int n){
        this.n = n;
        if(n<=0)
            throw new IllegalArgumentException("N must be greater than 0"+n);
        grid = new boolean[n][n];
        gaoyu = new WeightedQuickUnionUF(n*n+2);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                grid[i][j]=false;
            }
        }

    }
    public boolean isvalid(int row, int col){
        if(row<1||row>n||col<1||col>n)
            return false;
        return true;
    }

    // opens the site (row, col) if it is not open already
    //checked
    public void open(int row, int col){
        if(row<1 ||row>grid.length )
            throw new IllegalArgumentException("row must be in range between 1 and n inclusive"+ row);
        if(col<1||col>grid[0].length)
            throw new IllegalArgumentException("col must be in range between 1 and n inclusive"+ col);
        grid[row-1][col-1]=true;
        unionNeighbour(row,col);
    }
    public void unionNeighbour(int row, int col){
        int index = conversion(row,col);
        if(isvalid(row-1, col)&&isOpen(row-1, col))
            gaoyu.union(index, conversion(row-1,col));
        if(isvalid(row+1, col)&&isOpen(row+1, col))
            gaoyu.union(index, conversion(row+1,col));
        if(isvalid(row, col-1)&&isOpen(row, col-1))
            gaoyu.union(index, conversion(row,col-1));
        if(isvalid(row, col+1)&&isOpen(row, col+1))
            gaoyu.union(index, conversion(row,col+1));
        if(row==1)
            gaoyu.union(0,index);
        if(row==n)
            gaoyu.union(index, n*n+1);
    }

    // is the site (row, col) open?
    //checked
    public boolean isOpen(int row, int col){
        if(row<1 ||row>grid.length )
            throw new IllegalArgumentException("row must be in range between 1 and n inclusive"+ row);
        if(col<1||col>grid[0].length)
            throw new IllegalArgumentException("col must be in range between 1 and n inclusive"+ col);
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    //checked
    public boolean isFull(int row, int col){
        if(row<1 ||row>grid.length )
            throw new IllegalArgumentException("row must be in range between 1 and n inclusive"+ row);
        if(col<1||col>grid[0].length)
            throw new IllegalArgumentException("col must be in range between 1 and n inclusive"+ col);
        return gaoyu.connected(0, conversion(row, col));
    }

    // returns the number of open sites
    //checked
    public int numberOfOpenSites(){
        int count =0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid.length; j++){
                if(grid[i][j])
                    count++;
            }
        }
        return count;
    }

    // does the system percolate?
    //checked
    public boolean percolates(){

        return gaoyu.connected(0, n*n+1);
    }

    public static void main(String[] args) {

    }
}
