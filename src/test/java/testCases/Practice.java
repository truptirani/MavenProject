package testCases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Practice {

	static public void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] arrayOfInt = { 1, 2, 3, 5, 1, 2, 7, 8, 9, 10 };
//		Set<Integer> notDupes = new HashSet<Integer>();
//		Set<Integer> duplicates = new HashSet<Integer>();
//		for (int i = 0; i < arrayOfInt.length; i++) {
//		    if (!notDupes.contains(arrayOfInt[i])) {
//		        notDupes .add(arrayOfInt[i]);
//		        continue;
//		    }
//		    duplicates.add(arrayOfInt[i]);
//		}
//		System.out.println("num of dups:" + duplicates.size());
//		System.out.println("num of norls:" + notDupes.size());
		

				
		
		// find repeted value in an array
//		int[] NumbersArray = { 1, 2, 3, 5, 1, 2, 7, 8, 9, 10,13,76,45,23,1,2,3,4,5,6,7,6,66,5,4,3,2,1,2,3,4,5,6,7,7,77,77,7,7,7,7,7,7,77,7,7,77, };
//		Set<Integer> UniqueValue = new HashSet<Integer>();
//		//Set<Integer> UniqueValueRepeat = new HashSet<Integer>();
//		int unqvalue=1;
//		for (int i=0; i<NumbersArray.length;i++) {
//			if(!UniqueValue.contains(NumbersArray[i])) {
//				UniqueValue.add(NumbersArray[i]);
//				for (int k=i+1;k<NumbersArray.length;k++) {
//					if (NumbersArray[i]==NumbersArray[k]) {
//						unqvalue=unqvalue+1;
//					}
//				}
//				System.out.println(NumbersArray[i]+ "is repeated for "+unqvalue+" times");
//				unqvalue=1;
//			}
//		}
//		
//		String x ="fgfgfgfggfgfgfgf7677676767   fgfhfhf fjufhihfif $%^$^$^$^$^$^$^$^";
//		char[] ch = x.toCharArray();
//		int letter = 0;
//		int space = 0;
//		int num = 0;
//		int other = 0;
//		for(int i = 0; i < x.length(); i++){
//			if(Character.isLetter(ch[i])){
//				letter ++ ;
//			}
//			else if(Character.isDigit(ch[i])){
//				num ++ ;
//			}
//			else if(Character.isSpaceChar(ch[i])){
//				space ++ ;
//			}
//			else{
//				other ++;
//			}
//		}
//		System.out.println("The string is : Aa kiu, I swd skieo 236587. GH kiu: sieo?? 25.33");
//		System.out.println("letter: " + letter);
//		System.out.println("space: " + space);
//		System.out.println("number: " + num);
//		System.out.println("other: " + other);
//			}	
		
		
//
//            String inputStr;
//            int v = 0;
//            int n = 0;
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("other: " + other);
//            System.out.println("Enter your string : ");
//            inputStr = scanner.nextLine();
//            for (int i = 0; i < inputStr.length(); i++) {
//                Character c = inputStr.charAt(i);
//                if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'a' || c == 'e' || c == 'i' || c ==
//                        'o' || c == 'u') {
//                    v++;
//                } else if (Character.isDigit(c)) {
//                    n++;
//                }
//            }
//            System.out.println("No of vowels "+v);
//            System.out.println("No of numbers "+n);
//        }
//    }

//		String q = "satish";
//		String rq = "";
//		
//		for (int i = q.length()-1; i>=0;i--) {
//			rq=rq+q.charAt(i);	
//		}
//		
//		System.out.println(rq);
		String we = "fhhgh545456^%^%&%&jgjgjgjhg";
		char[] wechar = we.toCharArray();
		
		for (int i = we.length()-1; i>=0;i--) {
			if (Character.isAlphabetic(wechar[i])) {
				
			}
		}
		
//		try {
//		    BufferedReader br = new BufferedReader(new FileReader("Data"));
//		    } 
//		       catch(IOException ie)
//		    {
//		         ie.printStackTrace();
//		    }
//		try {
//		    BufferedReader br = new BufferedReader(new FileReader("Data"));
//		    
//		    } 
//		     catch(FileNotFoundException ie)
//		    {
//		      ie.printStackTrace();
//		    }
//		
		
//		try {
//			 
//            // code which might raise exception
// 
//            int result = 18/0;
//            System.out.println("Result of division : " + result);
//        }
//        catch(ArithmeticException aex) {
// 
//            // corresponding handling code, if any exception from try block
// 
//            aex.printStackTrace();
//        }
//        finally {
// 
//            // finally block always gets executed for code clean-up activities
// 
//            System.out.println("finally block always gets executed");
//            // rest of the code clean-up
//        }
		
	String s= "this is testing";
	char[] rs = s.toCharArray();
	String srs = "";
	for (int i=s.length()-1;i>=0;i--) {
		srs = srs+rs[i];
	}
	System.out.println(srs);	
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	}
}
