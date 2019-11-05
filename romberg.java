import java.lang.Math.*;
import java.math.BigDecimal;
import java.util.*;

public class romberg{

	public static void main(String[] args){
		new romberg();	
	}




	int N = 5;
	double b = 1.0, a = 0.0, h = 0.0;
	double rombArr[][] = {
		{0.0,0.0,0.0,0.0,0.0},
		{0.0,0.0,0.0,0.0,0.0},
		{0.0,0.0,0.0,0.0,0.0},		
		{0.0,0.0,0.0,0.0,0.0},
		{0.0,0.0,0.0,0.0,0.0}

	};
	public double f(double x){
		return (1.0/(1.0 + Math.pow(x,2)));
	}
	public double sum(double i, double a, double h){
		double total = 0.0;
		for(int k = 1; k <= (Math.pow(2,i) - 1); k+=2){
			total += f(a + k*h);
		}
		return total;
		
	}
	public double R(int i, int j){
		if(j == 0){
			if(i == 0){
				return ((h/2.0) * (f(a) + f(b)));
			}
			else{
				return ((0.5) * (rombArr[i - 1][0]) + h * sum(i,a,h));
			}
		}
		else if(j > 0){
			return (rombArr[i][j - 1] + (1.0/(Math.pow(4,j) - 1)) * (rombArr[i][j - 1] - rombArr[i - 1][j - 1]));
		}
		else{
			return 0.0;
		}
	}
	public romberg(){
		System.out.println("Romberg Estimation for integral of 4 /(1 + x^2) from 0 to 1 with" + N + "subintervals:");
		h = b - a;
		rombArr[0][0] = R(0,0);
		System.out.printf("%.13f\n", rombArr[0][0]);
		for(int i = 1; i < N; i++){
			h /= 2.0;
			rombArr[i][0] = R(i,0);
			System.out.printf("%.13f", rombArr[i][0]);
			for(int j = 1; j <= i; j++){
				rombArr[i][j] = R(i,j);
				System.out.printf("\t%.13f", rombArr[i][j]);
			}
			System.out.println();
		}
	}
}
