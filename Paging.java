import java.util.*;

public class Paging
{
	void fillPT(ArrayList<Integer> table, int limit, int n)
	{
		// System.out.println("fillPT Called");
		Random r = new Random();
		// int n = table.size();
		// System.out.println("N is "+n);
		HashSet<Integer> hs = new HashSet<Integer>();
		while(hs.size() != n)
		{
			hs.add(r.nextInt(limit+1));
		}
		int ind = 0;
		for(int i : hs)
		{
			table.add(i);
			ind++;
		}
	}
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		Paging pg = new Paging();
		System.out.println("Enter Page Size: ");
		int page_size = sc.nextInt();

		System.out.println("Enter Process Size: ");
		int proc_size = sc.nextInt();

		System.out.println("Want to enter Physical Memory available:(1 for yes and 0 for no) ");
		int choice = sc.nextInt();
		int lim = (int)1e5;
		if(choice == 1)
		{
			System.out.println("Enter Physical Memory: ");
			lim = sc.nextInt();
		}

		int pages = proc_size/page_size + 1;
		int frames = lim/page_size + 1;
		if(pages > frames)
			System.out.println("Insufficient Memory");
		else
		{
			ArrayList<Integer> page_table = new ArrayList<Integer>();
			pg.fillPT(page_table, frames, pages);
			// System.out.println(page_table);
			boolean until = true;
			while(until)
			{
				System.out.print("1.Convert LA to PA\n2.Print Page table\n3.Exit\nEnter choice: ");
				choice = sc.nextInt();
				switch(choice)
				{
					case 1:
						System.out.print("Enter Logical Address: ");
						int la = sc.nextInt();
						if(la >= proc_size)
							System.out.println("Invalid Logical Address");
						else
						{
							int pageno = la/page_size;
							int offset = la%page_size;
							System.out.println("Page Number-"+pageno+"  offset-"+offset);
							int pa = page_table.get(pageno)*page_size + offset;
							System.out.println("Frame Number - "+page_table.get(pageno));
							System.out.println("Physical address - "+pa);
						}
						break;

					case 2:
						// System.out.println("Case 2");
						for(int i = 0; i < pages; i++)
						{
							System.out.println(i + "->" + page_table.get(i));
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
}