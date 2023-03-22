package com.example.task_4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class FuelQueue {

    List<Passenger> fuelQueue = new ArrayList<>();

    static  Passenger[] waitingList = new Passenger[6];
    static String[] menuOption = {
            "100 or VFQ: View all Fuel Queues.",
            "101 or VEQ: View all Empty Queues.",
            "102 or ACQ: Add customer to a Queue.",
            "103 or RCQ: Remove a customer from a Queue.",
            "104 or PCQ: Remove a served customer.",
            "105 or VCS: View Customers Sorted in alphabetical order.",
            "106 or SPD: Store Program Data into file.",
            "107 or LPD: Load Program Data from file.",
            "108 or STK: View Remaining Fuel Stock.",
            "109 or AFS: Add Fuel Stock.",
            "110 or IFQ: Income of Each Fuel Queue.",
            "111 : GUI Menu.",
            "999 or EXT: Exit the Program.",
    };
    static Double fuelStock = 6600.00; //declare the fuelStock array
    private int income;
    private int count;
    public int queueLength(){return count;}
    public int getIncome(){return income;}

    static int front = -1;
    static int rear = -1;

    public static int getLiters(){
        Scanner gl = new Scanner(System.in);
        int litersNum;
        while (true){
            System.out.print("Enter the Number of Liters to Add : ");
            try{
                litersNum = gl.nextInt();
                break;
            }catch (Exception e){
                System.out.println("Invalid Input. Please Enter Integer.\n");
                gl.next();
            }
        }
        return litersNum;
    }

    public int getQueueNum(){
        Scanner qn = new Scanner(System.in);
        int qNum;
        while(true){
            System.out.print("Enter the Queue Number [1/2/3/4/5] : ");
            try{
                qNum= qn.nextInt();
                if (qNum <= 0 || qNum > 5) {
                    System.out.println("\nOut of Range. Please Enter Integer [1/2/3/4/5].\n");
                }break;
            }catch (Exception e){
                System.out.println("\nInvalid Input. Please Enter Integer [1/2/3/4/5].\n");
                qn.next();
            }
        }
        return qNum;
    }

    public int getPlaceNum(){
        Scanner pn = new Scanner(System.in);
        int pNum;
        while(true){
            System.out.print("Enter the Place Number [1/2/3/4/5/6] : ");
            try{
                pNum= pn.nextInt();
                if (pNum <= 0 || pNum > 6) {
                    System.out.println("\nOut of Range. Please Enter Integer [1/2/3/4/5/6].\n");
                }break;
            }catch (Exception e){
                System.out.println("\nInvalid Input. Please Enter Integer [1/2/3/4/5/6].\n");
                pn.next();
            }
        }
        return pNum;
    }

    public void viewAll(int A){
        System.out.print("Queue " + A + " : ");
        for (Passenger newOne : fuelQueue){
            System.out.print(newOne.getFirstName() + " " + newOne.getSecondName() + ", ");
        }
        System.out.println(" ");
    }

    public void viewEmpty(int A){
        if (count<6){
            System.out.print("Queue " + A + " : ");
            if (count!=0){
                System.out.println(" "+(6-count)+" - Remaining in the Queue "+ A);
            }
            System.out.println(" All Empty");
        }
    }

    public void addCustomer(){
        if (queueLength()!=6){
            Scanner cd = new Scanner(System.in); //scanner for Customer Details
            System.out.println("Enter Customer Details");

            System.out.print("First Name : ");
            String firstname = cd.nextLine();

            System.out.print("Second Name : ");
            String secondName = cd.nextLine();

            System.out.print("Vehicle Number : ");
            String vehicleNumber = cd.nextLine();

            double liters;
            while (true){
                System.out.print("No of Liters : ");
                try {
                    liters = cd.nextDouble();
                    break;
                }catch (Exception e){
                    System.out.println("Invalid Input. Please Enter Again.");
                    cd.next();
                }
            }

            income = (int) (income + (430*liters));
            Passenger newOne = new Passenger(firstname, secondName, vehicleNumber, (int) liters);
            fuelQueue.add(newOne);
            fuelStock=fuelStock-liters;
            count+=1;
            System.out.println("Successfully, Added a Customer.");
            viewRemainingFuelStock();
        }else{
            if (!((rear+1)%6==front)){
                System.out.println("Queue is already full.\n Enter Customer Details for Add to Waiting-List.");
                Scanner cd = new Scanner(System.in); //scanner for Customer Details

                System.out.print("First Name : ");
                String firstname = cd.nextLine();

                System.out.print("Second Name : ");
                String secondName = cd.nextLine();

                System.out.print("Vehicle Number : ");
                String vehicleNumber = cd.nextLine();

                double liters;
                while (true){
                    System.out.print("No of Liters : ");
                    try {
                        liters = cd.nextDouble();
                        break;
                    }catch (Exception e){
                        System.out.println("Invalid Input. Please Enter Again.");
                        cd.next();
                    }
                }
                income = (int) (income + (430*liters));
                Passenger newOne = new Passenger(firstname, secondName, vehicleNumber, (int) liters);
                fuelQueue.add(newOne);
                fuelStock=fuelStock-liters;
                count+=1;
                System.out.println("Successfully, Added a Customer.");
                viewRemainingFuelStock();
            }
        }
    }

    public void removeCustomer(int r){
        if (queueLength()!=0){
            int pNum =0;
            for (Passenger newOne : fuelQueue){
                pNum++;
                fuelStock=fuelStock+fuelQueue.get(0).getLiters();
                if (pNum==r){
                    fuelQueue.remove(newOne);
                    count=count-1;
                    System.out.println("Successfully, Removed Customer.");
                    break;
                }
            }
        }else{
            System.out.println("This Queue Place is already Empty.");
        }
    }

    public void removeServedCustomer(){
        if (queueLength()!=0){
            income = income + (430*fuelQueue.get(0).getLiters());
            fuelQueue.remove(0);
            count=count-1;
            System.out.println("Successfully, Served & Removed Customer.");
            if (!(rear==-1 && front==-1)){
                fuelQueue.add(waitingList[front]);
                count=count+1;
//                deQValue();
                if (rear==-1 && front==-1){
                    System.out.println("Waiting-List is Empty.");
                } else if (rear==front) {
                    rear=-1;
                    front=-1;
                }else {
                    front=(front+1)%6;
                }
            }
        }else{
            System.out.println("This Queue Place is already Empty.");
        }
    }

    public void viewCustomerSortedAorder(int s){
        String [] sa =new String[queueLength()]; //created an array Sorted A Oder
        int i=0;
        for (Passenger newOne : fuelQueue){
            sa[i] = newOne.getFirstName() + " " + newOne.getSecondName();
            i+=1;
        }
        for (int j=0; j<sa.length; j++){
            for (int k=0; k<sa.length; k++){
                if (sa[i].compareToIgnoreCase(sa[j])>0){
                    String sOder=sa[i];
                    sa[i]=sa[j];
                    sa[j]=sOder;
                }
            }
        }
        System.out.print("Queue " + s + " : ");
        for (String pn : sa){
            System.out.print(pn+",");
        }
        System.out.print(" \n");
    }

    public void storeProgramData(String FName) throws IOException {
        File storeFile = new File(FName);
        FileOutputStream fos = new FileOutputStream(storeFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Passenger newOne : fuelQueue){
            oos.writeObject(newOne);
        }
    }

    public void lordProgramData(String FName) throws IOException {
        FileInputStream fis = new FileInputStream(FName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        while(true){
            try{
                Passenger newOne = (Passenger) ois.readObject();
                fuelStock = fuelStock-newOne.getLiters();
                income = income + (430*newOne.getLiters());
                fuelQueue.add(newOne);
                count=count+1;
            }catch(IOException | ClassNotFoundException e ){
                break;
            }
        }
    }

    public static void viewRemainingFuelStock(){
        System.out.println("Remaining Fuel Stock is "+fuelStock);
    }


    public static void addFuelStock() {
        viewRemainingFuelStock();
        System.out.print("Maximum Fuel Store - 6600 liters ");
        System.out.println("You can Add Fuel Store up to - "+ (6600-fuelStock)+ " Liters");
        int addFuel = getLiters();
        if (6600>=addFuel+fuelStock){
            fuelStock=fuelStock+addFuel;
            System.out.println(addFuel+" Litters Added.");
            viewRemainingFuelStock();
        }else {
            System.out.println("Out of rang. Please Enter again.");
            addFuelStock();
        }
    }

    public ObservableList<Passenger> getPassenger(){
        ObservableList<Passenger> people = FXCollections.observableArrayList();
        people.addAll(fuelQueue);
        return people;
    }
}
