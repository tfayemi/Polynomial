
/**
 * Write a description of class Polynomial here.
 *
 * @author Toluwa Fayemi
 * @version date March 5th, 2020
 */

import java.lang.Math.*; 

public final class Polynomial implements Comparable<Polynomial> {
    
    public final int[] coeffs;

    public Polynomial(int[] coeffs) {
        
        //Creating a defensive copy of the array just in case the method caller decides to modify the array later.
        
        this.coeffs = new int[coeffs.length]; //Part 1 of creating a defensive copy: creating an empty array of the same length as the original array 
        for (int i = 0; i < coeffs.length; i++) {
            this.coeffs[i] = coeffs[i]; //Part 2: Filling this empty array with the values from the original array
        }
        
    }
    
    public Polynomial() {
        this(new int[0]);
    }
    
    
    public int getDegree() {
        int degree = 0;
        for(int i = coeffs.length - 1; i>0; i--) {
            if (coeffs[i] != 0) {
                degree = i;
                break;
            }           
        }
        return degree;
    }
    
    public int getCoefficient(int k) {
        return coeffs[k];
        
    }
    
    public long evaluate(int x) {
        long sum = 0;
        for (int i = 0; i < coeffs.length; i++) {
            sum += coeffs[i]*(Math.pow(x, i));
        }
        return sum;
        
    }
    
    @Override public String toString() {
        
        StringBuilder sb = new StringBuilder();
        for (int exp = 0; exp < coeffs.length; exp++) {
            if (exp>0) {
                sb.append(" + ");
            }
            sb.append(" (");
            sb.append(Integer.toString(coeffs[exp]));
            sb.append(" )x^");
            sb.append(Integer.toString(exp));
            }
        return sb.toString();
        }
        
    public Polynomial add(Polynomial other) {
        if(this.coeffs.length >= other.coeffs.length) {
            int[] sum = new int[this.coeffs.length];
            for(int i=0; i < this.coeffs.length - (this.coeffs.length - other.coeffs.length); i++) {
                sum[i] = this.coeffs[i] + other.coeffs[i];
            }
            for(int i = this.coeffs.length - (this.coeffs.length - other.coeffs.length); i < this.coeffs.length;i++) {
                sum[i] = coeffs[i];
            }
            return new Polynomial(sum);
        }
        else {
            int[] sum = new int[other.coeffs.length];
            for(int i=0; i < other.coeffs.length - (other.coeffs.length - this.coeffs.length); i++) {
                sum[i] = this.coeffs[i] + other.coeffs[i];
            }
            for(int i = other.coeffs.length - (other.coeffs.length - this.coeffs.length); i < other.coeffs.length;i++) {
                sum[i] = other.coeffs[i];
            }
            return new Polynomial(sum);
        }
    }
    
      public Polynomial multiply(Polynomial other){
        int productDegree = this.getDegree() + other.getDegree();
        int[] productCoeffs = new int[productDegree+1];
        for(int i=0; i<this.coeffs.length;i++){
            for(int j=0; j<other.coeffs.length;j++) {
                try{
                    productCoeffs[i+j] += (this.coeffs[i])*(other.coeffs[j]);
                   }
                catch (Exception e){
                }
            }
        }
        return new Polynomial(productCoeffs);
    }
    
    public int compareTo(Polynomial other) {
    	if (this.getDegree() < other.getDegree()) return -1;
    	if (this.getDegree() > other.getDegree()) return +1;
    	for (int i = this.getDegree(); i >= 0; i--) {
    		if (this.coeffs[i] < other.coeffs[i]) return -1;
    		if (this.coeffs[i] > other.coeffs[i]) return +1;
    	}
    	return 0;
    }
    
    @Override public boolean equals(Object other) {
    	if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Polynomial that = (Polynomial) other;
        if (this.getDegree() != that.getDegree()) return false;
        for (int i = this.getDegree(); i >= 0; i--)
            if (this.coeffs[i] != that.coeffs[i]) return false;
        return true;
    }
    
    @Override public int hashCode() {
        return Math.toIntExact(this.evaluate(3) * 10) + this.getDegree();

    }
}
    

