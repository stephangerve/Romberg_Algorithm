import java.lang.Math.*;
import java.util.*;

public class romberg{

	public static void main(String[] args){
		new romberg();	
	}



	//2^N subintervals
	int N = 5;
	//limits of integration, interval width
	double b = 1.0, a = 0.0, h = 0.0;
	//romberg triangular array
	double rombArr[][] = {
		{0.0,0.0,0.0,0.0,0.0},
		{0.0,0.0,0.0,0.0,0.0},
		{0.0,0.0,0.0,0.0,0.0},		
		{0.0,0.0,0.0,0.0,0.0},
		{0.0,0.0,0.0,0.0,0.0}

	};
	//function f(x)
	public double f(double x){
		return (1.0/(1.0 + Math.pow(x,2)));
	}
	//summation used in first column approximation
	public double sum(double i, double a, double h){
		double total = 0.0;
		for(int k = 1; k <= (Math.pow(2,i) - 1); k+=2){
			total += f(a + k*h);
		}
		return total;
		
	}
	//implementation of each romberg algorithm recursive formula
	public double R(int i, int j){
		if(j == 0){
			if(i == 0){
				//single trapezoid estimation R(0,0)
				return ((h/2.0) * (f(a) + f(b)));
			}
			else{
				//trapezoid approximation with 2^N equal subintervals
				return ((0.5) * (rombArr[i - 1][0]) + h * sum(i,a,h));
			}
		}
		else if(j > 0){
			//successive column entries recursively generated by the extrapolation formula
			return (rombArr[i][j - 1] + (1.0/(Math.pow(4,j) - 1)) * (rombArr[i][j - 1] - rombArr[i - 1][j - 1]));
		}
		else{
			return 0.0;
		}
	}
	public romberg(){
		System.out.println("Romberg Estimation for integral of 4 /(1 + x^2) from 0 to 1 with " + N + " subintervals:");
		//initial step size
		h = b - a;
		//first estimation
		rombArr[0][0] = R(0,0);
		//print with 13 decimals digits of precision
		System.out.printf("%.13f\n", rombArr[0][0]);
		//calculation of row i
		for(int i = 1; i < N; i++){
			h /= 2.0;
			//first calculation for row i
			rombArr[i][0] = R(i,0);
			System.out.printf("%.13f", rombArr[i][0]);
			//calculation of subsequent column entries in row i
			for(int j = 1; j <= i; j++){
				rombArr[i][j] = R(i,j);
				System.out.printf("\t%.13f", rombArr[i][j]);
			}
			System.out.println();
		}
	}
}
