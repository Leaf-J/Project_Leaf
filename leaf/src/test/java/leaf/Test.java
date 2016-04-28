package leaf;

import java.util.Date;

public class Test {
	public static void main(String [] args){
		long i1 = new Date().getTime();
		long i2 = new Date().getTime();
		System.out.println(i1-i2);
	}
}
