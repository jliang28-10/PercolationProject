import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Scanner;

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
    //checked
    public double stddev(){
        double total = 0;
        for(int i:thresh){
            total +=  Math.pow(((double)i/(n*n))-mean(),2);
        }
        return Math.sqrt( total /(trials-1));
    }

    // low endpoint of 95% confidence interval
    //checked
    public double confidenceLo(){
        return (this.mean()-(1.96*this.stddev()/(Math.sqrt(trials))));
    }

    // high endpoint of 95% confidence interval
    //checked
    public double confidenceHi(){
        return (this.mean()+(1.96*this.stddev()/(Math.sqrt(trials))));
    }

    // test client (see below)
    public static void main(String[] args){
        System.out.println("Enter 2 integers, grid size then number of trials");
        Scanner scan = new Scanner(System.in);
        PercolationStats p= new PercolationStats(scan.nextInt(), scan.nextInt());
        System.out.println("Mean = " + p.mean());
        System.out.println("stddev = "+p.stddev());
        System.out.println("95% confidence interval = " + "[" + p.confidenceLo()+", "+ p.confidenceHi()+"]");

    }
}
