import java.lang.Math.*;
import java.util.*;

public class romberg{

	public static void main(String[] args){
		new romberg();	
	}




	private int b = 1, a = 0, N = 5;
	private double h = 0;
	private double rombArr[][] = {
		{0,0,0,0,0},
		{0,0,0,0,0},
		{0,0,0,0,0},			
		{0,0,0,0,0},
		{0,0,0,0,0}
	};
	public double f(double x){
		return (4/(1 + Math.pow(x,2)));
	}
	public double sum(int i, int a, double h){
		double total = 0.0;
		for(int k = 1; k <= (Math.pow(2,i) - 1); k++){
			total += f(a + k*h);
		}
		return total;
		
	}
	public double R(int i, int j){
		if(j == 0){
			return ((0.5) * (rombArr[i - 1][0]) + h * sum(i,a,h));
		}
		else if(j > 0){
			return (R(i,j - 1) + (1/(Math.pow(4,j) - 1)) * (rombArr[i][j - 1] - rombArr[i - 1][j - 1]));
		}
		else{
			return 0;
		}
	}
	public romberg(){
		System.out.println("Romberg Estimation for integral of 4 /(1 + x^2) from 0 to 1 with 5 subintervals:");
		h = b - a;
		rombArr[0][0] = (h/2) * (f(a) + f(b));
		System.out.println(rombArr[0][0]);
		for(int i = 1; i <= N; i++){
			h /= 2;
			rombArr[i][0] = R(i,0);
			System.out.print(rombArr[i][0]);
			for(int j = 1; j <= i; j++){
				rombArr[i][j] = R(i,j);
				System.out.print("		" + rombArr[i][j]);
			}
			System.out.println();
		}
	}
}
