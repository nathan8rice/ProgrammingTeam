import java.util.*;

/**
 * Problem 202: Repeating Decimals
 * Given a numerator and a denominator, determine quotient. If
 * the quotient has a repeating decimal, surround the part that
 * repeats with parenthesis.
 * Ex. 10/3 = 3.(3)
 * @author Nathan Rice
 */
public class RepeatingDecimal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int num, den;
		System.out.println("What is the numerator?");
		num = input.nextInt();
		System.out.println("What is the denominator?");
		den = input.nextInt();

		//whole is the whole number before the decimal
		int whole = num / den;

		//boolean to let us know when we're done
		boolean done = false;

		int[] decimal = new int[50];
		int[] remainders = new int[50];

		int startIndex = 49,endIndex = 49;

		int rem = num % den;
		// System.out.println("Remainder is " + rem + ".");
		remainders[0] = rem;
		if(rem == 0)
		{
			startIndex = 0;
			endIndex = 0;
			done = true;
		}

		int display;

		//decimal[0] = rem;

		int temp;

		for(int i = 1; i < 50 && !done; i++)
		{
			rem = (rem*10);
			// System.out.println("Add a zero to remainder to make " + rem + ".");
			display = rem / den;
			// System.out.println(den + " goes into " + rem + " "+display+ " times.");
			decimal[i-1] = display;
			rem = rem % den;
			// System.out.println( "Subtract and get new remainder "+rem+"." );
			remainders[i] = rem;

			temp = arrayContains(remainders, rem, i);

			if(rem == 0)
			{
				// System.out.println("Remainder = 0.");
				decimal[i] = 0;
				startIndex = i;
				endIndex = i;
				done = true;
			}
			else if( temp != -1)
			{
				// System.out.println("We've seen this remainder.");
				// printArray(remainders);
				endIndex = i - 1;
				startIndex = temp;
				done = true;
			}
		}

		System.out.print(num + " / "+ den + " = ");
		System.out.print(whole + ".");

		int j = 0;
		while(j < startIndex)
		{
			System.out.print(decimal[j]);
			j++;
		}
		System.out.print("(");
		while(j <= endIndex)
		{
			System.out.print(decimal[j]);
			j++;
		}
		System.out.print(")\n");
	}

	public static int arrayContains(int[] arr, int num, int limit) {
		// TODO Auto-generated method stub
		int index = -1;
		for(int i = 0; i < limit && index == -1; i++)
		{
			if(num == arr[i])
			{
				index = i;
			}
		}
		return index;
	}

	public static void printArray(int[] arr)
	{
		System.out.print("[ ");
		for(int i = 0; i < arr.length; i++)
		{
			System.out.print(arr[i] + " ");
		}
		System.out.println("]");
	}

}
