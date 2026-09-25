import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    public int[] thresh;
    public int n;
    public int trials;
    // perform independent trials on an n-by-n grid
    //checked ig
    public PercolationStats(int n, int trials){
        if(n<1 || trials<1)
            throw new IllegalArgumentException ("Enter number greater than 0");
        this.n=n;
        this.trials=trials;
        thresh = new int[trials];
        for(int i=0; i<trials; i++){
            int opennum =0;
            Percolation p = new Percolation(n);
            while(!p.percolates()){
                int row = rng(n);
                int col = rng(n);
                while(p.isOpen(row,col)){
                     row = rng(n);
                     col = rng(n);
                }
                p.open(row,col);
                opennum++;
            }
            thresh[i]=opennum;
        }
    }
    public int rng(int range){
        return (int)(Math.random()*range)+1;
    }

    // sample mean of percolation threshold
    //checked
    public double mean(){
        double total =0;
        for(int i:thresh){
            total+=(double)i/(n*n);
        }
        return  total /trials;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        int total = 0;
        for(int i:thresh){
            total +=(Math.pow(((double)i/(n*n))-mean(),2));
        }
        return (double) total /(trials-1);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean()-(1.96*stddev()/(Math.sqrt(trials)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean()+(1.96*stddev()/(Math.sqrt(trials)));
    }

    // test client (see below)
    public static void main(String[] args){
        PercolationStats l = new PercolationStats(200,100);
    }
}
