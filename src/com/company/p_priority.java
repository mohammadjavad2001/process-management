package com.company;

import java.util.ArrayList;

public class p_priority {
    public static void adder1(int iterator,ArrayList<ProcessClass> listOfItems,ArrayList<ProcessClass> queue){
        queue.clear();
        for(int i=iterator;i<iterator+100;i++){
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
            adder1(itterator,list,queue);
            p_priority Executer=new p_priority();
            temp= Executer.priorityExecuter(queue, wt, Response,temp[0],temp[1]);

            ppriorityfindTurnAroundTime(queue, wt, tat);
            for (int i = 0; i < queue.size(); i++) {
                System.out.println(i+"  wait :  "+wt[i]+"turn :  "+tat[i]+" response:  "+Response[i]);
                total_wt = total_wt + wt[i];
                total_tat = total_tat + tat[i];
                total_Response=total_Response+Response[i];
            }

            itterator+=99;

    }


        float avgthroughput=(float)100000/(float)temp[0];

        float utilization1=((float)temp[1]+(float) temp[0])/(float)temp[0] ;
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

    static int[] priorityExecuter(ArrayList<ProcessClass> queue,int wt[],int Response[],int t,int utiliz ) {
        int complete = 0,  minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;
        int priority[] = new int[queue.size()];
        int bursttime[] = new int[queue.size()];
        boolean checkerone=false;
        // Copy the burst time into rt[]
        for (int i = 0; i < queue.size(); i++){
            priority[i] = queue.get(i).getPPriority();}
        for (int i = 0; i < queue.size(); i++){
            bursttime[i] = queue.get(i).getPBurstTime();}

        while (complete != queue.size()) {
            for (int j = 0; j < queue.size(); j++) {
                if ((queue.get(j).getPArrivalTime() <= t) && (priority[j] < minm) && bursttime[j] > 0) {
                    minm = priority[j];
                    shortest = j;
                    check = true;

                }
            }


            if (bursttime[shortest] == queue.get(shortest).getPBurstTime() && Response[shortest] > -1) {
                Response[shortest] = t;
            }
            if (check == false) {
                utiliz--;
                time();
                t++;
                continue;
            }

            bursttime[shortest]--;



            if (bursttime[shortest] == 0) {
                // Increment complete
                complete++;
                check = false;

                // Find finish time of current
                // process
                finish_time = t + 1;

                    minm = Integer.MAX_VALUE;
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
    static void ppriorityfindTurnAroundTime(ArrayList<ProcessClass> queue, int wt[], int tat[])
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
