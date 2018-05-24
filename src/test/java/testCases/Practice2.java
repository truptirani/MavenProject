package testCases;

public class Practice2 {

	static {
		System.out.println("Hello world");
	}
	
	public static void main(String[] args) {
		System.out.println("Hello world1");
		System.out.println("Hello world2");
		printNumber(1);
	}
	
	public static void printNumber(int number) {
		if(number <= 5) {
			System.out.println(number);
			number++;
			printNumber(number);			//Note: method calling itself
		}else {
			System.exit(0);
		}
	}
	
	

}