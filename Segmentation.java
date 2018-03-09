import java.util.*;
public class Segmentation
{
	
	boolean isValid(int base[], int size[], int start, int n)
	{
		boolean result = true;
		for(int i = 0; i<=n; i++)
		{
			if(start + size[n+1] < base[i] || start >= base[i] + size[i])
			{

			}
			else
			{
				result = false;
				break;
			}
		}

		return result;
	}

	public static void main(String args[])
	{
		Segmentation obj = new Segmentation();
		Random ran = new Random();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number of Segments: ");
		int noseg = sc.nextInt();
		int maxsz = (int)1e5;
		int[] sizes = new int[noseg];
		int sum = 0;
		for(int i = 0; i < noseg; i++)
		{
			System.out.println("Enter size of "+(i)+" Segment");
			sizes[i] = sc.nextInt();
			sum += sizes[i];
		}
		if(sum > maxsz)
		{
			System.out.println("Insufficient Memory");
			System.exit(0);
		}
		int[] base = new int[noseg];
		base[0] = ran.nextInt(maxsz);
		for(int i = 1; i < noseg; i++)
		{
			while(true)
			{
				base[i] = ran.nextInt(maxsz);
				if(obj.isValid(base, sizes, base[i], i-1))
					break;
			}
		}

		boolean until = true;
		int choice;
		while(until)
		{
			System.out.print("1.Convert LA to PA\n2.Print Segment table\n3.Exit\nEnter choice: ");
			choice = sc.nextInt();
			switch(choice)
			{
				case 1:
					System.out.println("Enter Segment number: ");
					int seg = sc.nextInt();
					if(seg >= noseg)
					{
						System.out.println("\nInvalid Segment Number\n");
						break;
					}
					System.out.println("\nEnter Address: ");
					int addr = sc.nextInt();
					
					if(addr >= sizes[seg])
					{
						System.out.println("\nInvalid Address\n");
						break;
					}
					int pa = base[seg] + addr;
					System.out.println("Base Address of Segment: "+base[seg]);
					System.out.println("Physical address "+pa);
					break;

				case 2:
					// System.out.println("Case 2");
					for(int i = 0; i < noseg; i++)
					{
						System.out.println(i + "-- " + base[i]+" | "+sizes[i]);
					}
					break;

				case 3:
					until = false;
					break;
					
				default:
				System.out.println("Invalid choice");
			}
		}
	}
}