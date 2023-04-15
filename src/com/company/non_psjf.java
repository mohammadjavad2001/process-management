package com.company;

import java.util.ArrayList;

public class non_psjf {
    public static void adder(int iterator,ArrayList<ProcessClass> listOfItems,ArrayList<ProcessClass> queue){
        queue.clear();
        for(int i=iterator;i<=iterator+99;i++){
            queue.add(listOfItems.get(i));
        }

    }
    // Method to calculate average time
    public void findavgTime(ArrayList<ProcessClass> list,ArrayList<ProcessClass> queue)
    {        ArrayList<ProcessClass> queueT=queue;
        int Response[] = new int[100], wt[] = new int[100], tat[] = new int[100];
        int  total_wt = 0, total_tat = 0,total_Response=0;

        int itterator=0;
        int temp[]=new int[2];
     for (int j = 0; j < 1000; j++) {
            adder(itterator,list,queue);
            non_psjf Executer=new non_psjf();
            temp= Executer.nonsjfExecuter(queue, wt, Response,temp[0],temp[1]);

            nonsjffindTurnAroundTime(queue, wt, tat);
            for (int i = 0; i < queue.size(); i++) {
         // System.out.println(i+"  wait :  "+wt[i]+"turn :  "+tat[i]+" response:  "+Response[i]);
                total_wt = total_wt + wt[i];
                total_tat = total_tat + tat[i];
                total_Response=total_Response+Response[i];
          }

            itterator+=100;

 }


        float avgthroughput=(float)100000/(float)temp[0];
        System.out.println(temp[1]);
        double utilization1=((float)temp[1]+(float) temp[0])/(float)temp[0] ;
        System.out.println("Average waiting time = " +
                (float)total_wt / (float)100000);
        System.out.println("Average turn around time = " +
                (float)total_tat / (float)100000);
        System.out.println("Average Response time = " +
                (float)total_Response / (float)100000);
        System.out.println("total time = " + (temp[0]));

        System.out.println("Throughput = " +avgthroughput);
        System.out.println("CPU utilization = " +
                utilization1*100+"%");

    }

    static int[] nonsjfExecuter(ArrayList<ProcessClass> queue,int wt[],int Response[],int t,int utiliz ) {
        int complete = 0,  minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;
        boolean checkerone=false;
        int brusttime[] = new int[queue.size()];

        // Copy the burst time into rt[]
        for (int i = 0; i < queue.size(); i++){
            brusttime[i] = queue.get(i).getPBurstTime();
        }

        while (complete != queue.size()) {
  if((t==Response[shortest]+queue.get(shortest).getPBurstTime())||checkerone==false) {
       //   System.out.println("llllllllllllvvvvvvvvvvv");
                for (int j = 0; j < queue.size(); j++) {
                    if ((queue.get(j).getPArrivalTime() <= t) && (brusttime[j] < minm) && brusttime[j] > 0) {
                        minm = brusttime[j];
                        shortest = j;
                        check = true;
                        checkerone=true;

                    }
                }
      }
   else {
         // System.out.println("SCSCCCCccccccccccccccccccccxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
      }

            if (brusttime[shortest] == queue.get(shortest).getPBurstTime() && Response[shortest] > -1) {
                Response[shortest] = t;
            }
            if (check == false) {
                utiliz--;
                time();
                t++;
                continue;
            }

            brusttime[shortest]--;
          minm = brusttime[shortest];

            if (minm == 0)
                minm = Integer.MAX_VALUE;

            if (brusttime[shortest] == 0) {
                // Increment complete
                complete++;
                check = false;

                // Find finish time of current
                // process
                finish_time = t + 1;

                // Calculate waiting time
                wt[shortest] = finish_time - queue.get(shortest).getPBurstTime() - queue.get(shortest).getPArrivalTime();


                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }

            time();
            t++;

        }
        int[] a = {t,utiliz};
        return a;

    }
    static void nonsjffindTurnAroundTime(ArrayList<ProcessClass> queue, int wt[], int tat[])
    {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < queue.size(); i++)
            tat[i] = queue.get(i).getPBurstTime()  + wt[i];
    }
    static void time() {
        for (int i = 0; i < 10000; i++) {
            int temp = 0;
            if (i % 2 == 0)
                temp = i / 2;
            else
                temp = 2 * i;
        }
    }

    /*private String waitingTime(ArrayList<ProcessClass> listOfItems) {
        String strTemp = "P" + listOfItems.get(0).getPId() + "= 0, ";
        for (int i = 1; i < listOfItems.size() - 1; i++) {
            strTemp += "P" + listOfItems.get(i).getPId() + "= " + listOfItems.get(i - 1).getPBurstTime() + ", ";
        }
        return strTemp + "P" + listOfItems.get(listOfItems.size() - 1).getPId() + "= " + listOfItems.get(listOfItems.size() - 2).getPBurstTime();
    }*/
    // Method to calculate average time


    private float averageWaitingTime(ArrayList<ProcessClass> listOfItems) {
        return 1;
    }

    private float averageTurnaroundTime(ArrayList<ProcessClass> listOfItems) {
        return 1;
    }

    private float Throughput(ArrayList<ProcessClass> listOfItems) {
        return 1;
    }

    public String ResultToString(ArrayList<ProcessClass> listOfItems) {
        return "Result OF FIFO Scheduling:\nAverage Waiting Time: " + averageWaitingTime(listOfItems)
                + "\nAverage Turnaround Time: " + averageTurnaroundTime(listOfItems)
                + "\nAverage Response Time: " + averageTurnaroundTime(listOfItems)
                + "\nThroughput: " + Throughput(listOfItems);
    }
}
