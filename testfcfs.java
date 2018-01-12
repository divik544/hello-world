/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

class Driver
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter No of Process:");
		int n = sc.nextInt();
		Process data[] = new Process[n];
		System.out.println("Enter process id, arrival time and Cpu cycles required for each process");
		int a,b,c;
		for(int i=0; i < n; i++)
		{
		    a=sc.nextInt();
		    b = sc.nextInt();
		    c = sc.nextInt();
		    data[i] = new Process(a,b,c);
		}
		Arrays.sort(data, new Comparator<Process>()
		{
		    @Override
		    public int compare(Process p1, Process p2)
		    {
		        Integer a = p1.arr_time;
		        Integer b = p2.arr_time;
		        return a.compareTo(b);
		    }
		}
		);
		
		int curtime=0;
		int total_wait_time = 0;
		for(int i=0; i < n; ++i)
		{
		    if(data[i].arr_time<=curtime)
		        curtime += data[i].cpu_cycle;
		    else
		    {
		        curtime = data[i].arr_time+data[i].cpu_cycle;
		    }
		    data[i].tat = curtime - data[i].arr_time;
		    data[i].wait_time = data[i].tat - data[i].cpu_cycle;
		    total_wait_time += data[i].wait_time;
		}
		double avgwait = (double)total_wait_time/(double)n;
		System.out.println("ProcessId\tArrivalTime\tCPU Cycle\tTAT\tWT");
		for(int i=0; i<n; ++i)
		{
		    System.out.println(data[i].id+"\t\t"+data[i].arr_time+"\t\t"+data[i].cpu_cycle+"\t\t"+data[i].tat+"\t"+data[i].wait_time);
		}
		
		System.out.println("Average Waiting Time: "+avgwait);
		for(int i=0; i < n; ++i)
		{
		    System.out.print("["+data[i].arr_time+" p"+data[i].id+" "+(data[i].tat+data[i].arr_time)+"]");
		}
	}
}
class Process
{
    int id;
    int arr_time;
    int cpu_cycle;
    int priority;
    int tat;    //turn around time
    int wait_time;
    Process(int id,int arrival, int time)
    {
        this.id = id;
        this.arr_time = arrival;
        this.cpu_cycle = time;
    }
    
}
