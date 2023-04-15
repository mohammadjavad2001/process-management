package com.company;// Java program for implementation of FCFS
// scheduling

import java.text.ParseException;
import java.util.ArrayList;

class FIFOClass {
    public static void adder(int iterator,ArrayList<ProcessClass> listOfItems,ArrayList<ProcessClass> queue){
        queue.clear();
        for(int i=iterator;i<iterator+100;i++){
            queue.add(listOfItems.get(i));
        }

    }
    // Function to find the waiting time for all
    // processes
    static int[] FIFOExecuter(ArrayList<ProcessClass> queue,int wt[],int Response[],int t,int utiliz) {
        int bt[] = new int[queue.size()];

        // waiting time for first process is 0
        wt[0] = 0;
        for (int i = 0; i < queue.size(); i++){
            bt[i] = queue.get(i).getPBurstTime();}
        // calculating waiting time
        for (int i = 1; i < queue.size(); i++) {
            wt[i] = bt[i - 1] + wt[i - 1];
            Response[i]=wt[i];

        }
        for (int i = 1; i < queue.size(); i++) {
            if(queue.get(i).getPArrivalTime()>queue.get(i-1).getPBurstTime()+t){
                System.out.println("SCSCSCSCWWWWWWWWWWWWWWWWWWWWWWWWWW"
                );
                int z=queue.get(i).getPArrivalTime()-queue.get(i-1).getPBurstTime()+t;
                utiliz=utiliz-z;
            }
            t=t+queue.get(i-1).getPBurstTime();

        }

        t=t+queue.get(queue.size()-1).getPBurstTime();

        int temp[]={t,utiliz};
    return temp;
    }

    // Function to calculate turn around time
    static void FIFOfindTurnAroundTime(ArrayList<ProcessClass> queue, int wt[], int tat[]) {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        int bt[]=new int[queue.size()];
        for (int i = 0; i < queue.size(); i++){
            bt[i] = queue.get(i).getPBurstTime();}
        for (int i = 0; i < queue.size(); i++) {
            tat[i] = bt[i] + wt[i];
        }
    }


    //Function to calculate average time
    static void findavgTime(ArrayList<ProcessClass> list, ArrayList<ProcessClass> queue) {
            ArrayList<ProcessClass> queueT=queue;
            int Response[] = new int[100], wt[] = new int[100], tat[] = new int[100];
            int  total_wt = 0, total_tat = 0,total_Response=0;

            int itterator=0;
            int temp[]=new int[2];
            for (int j = 0; j < 1000; j++) {
                adder(itterator,list,queue);
                FIFOClass Executer=new FIFOClass();
                temp= Executer.FIFOExecuter(queue, wt, Response,temp[0],temp[1]);

                FIFOfindTurnAroundTime(queue, wt, tat);
                for (int i = 0; i < queue.size(); i++) {
                  //  System.out.println(i+"  wait :  "+wt[i]+"turn :  "+tat[i]+" response:  "+Response[i]);
                    total_wt = total_wt + wt[i];
                    total_tat = total_tat + tat[i];
                    total_Response=total_Response+Response[i];
                }

                itterator+=99;

            }
            float avgthroughput=(float)100000/(float)temp[0];
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



}
