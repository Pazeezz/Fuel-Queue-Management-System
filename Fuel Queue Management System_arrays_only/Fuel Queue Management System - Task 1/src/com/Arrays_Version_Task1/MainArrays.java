package com.Arrays_Version_Task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MainArrays {

    static String[] menuOption = {                  //declare the main menu array
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
            "999 or EXT: Exit the Program.",
    };
    static String[] customerMenu = {   //declare the c.menu array
            "Enter 1 for Pump 1",
            "Enter 2 for Pump 2",
            "Enter 3 for Pump 3",
            "Enter 4 for Main Menu."
    };
    static String[] pump1 = new String[6]; //declare the pump1 array
    static String[] pump2 = new String[6]; //declare the pump2 array
    static String[] pump3 = new String[6]; //declare the pump3 array
    static Double fuelStock = 6600.00; //declare the fuelStock array
    private static int remaining1, remaining2, remaining3;

    public static void main(String[] args) throws IOException {

        System.out.println("\n<<<------Welcome to Fuel Management System------>>>\n");
        String option;
        do {
            Arrays.stream(menuOption).forEach(System.out::println);
            Scanner mainInput = new Scanner(System.in);  //Scanner for Main Input
            System.out.print("\nEnter Your Option : ");
            option = mainInput.next();

            if (fuelStock <= 500) {
                System.out.println("\nWarning !!! \n" + "Fuel level Low: " + fuelStock + " Liters !!!");
            }
            switch (option) {
                case "100", "VFQ" -> {
                    System.out.println("\nAll Fuel Queues");
                    viewAll();
                }
                case "101", "VEQ" -> {
                    System.out.println("\nAll Empty Queues.");
                    viewEmpty();
                }
                case "102", "ACQ" -> {
                    System.out.println("\nAdd Customer");
                    addCustomer();
                }
                case "103", "RCQ" -> {
                    System.out.println("\nRemove Customer");
                    removeCustomer();
                }
                case "104", "PCQ" -> {
                    System.out.println("\nRemove Served Customer");
                    removeServedCustomer();
                }
                case "105", "VCS" -> {
                    System.out.println("\nCustomers Sorted in Alphabetical order");
                    viewCustomerSortedAorder();
                }
                case "106", "SPD" -> {
                    System.out.println("\nStore Program Data into Text file.");
                    storeProgramData();
                }
                case "107", "LDP" -> {
                    System.out.println("Lord Program Data form Text file.");
                    lordProgramData();
                }
                case "108", "STK" ->
                        viewRemainingFuelStock();
                case "109", "AFS" -> {
                    System.out.println("Add Fuel Stock");
                    addFuelStock();
                }
                case "999", "EXT" -> System.out.println("\nExiting the Program...Thank You.");
                default -> System.out.println("Invalid Input. Please Enter Again.");
            }
            System.out.println("\n");
        } while (!option.equals("999") && !option.equals("EXT"));
    }

    private static void viewAll() {
        System.out.print("\nPump 1 : [");       //for queue1
        int count1 = 0;
        for (String sp1 : pump1) {
            if (sp1 == null) {
                continue;
            }
            System.out.print(sp1 + ",");
            count1++;
        }
        remaining1 = 6 - count1;
        System.out.println("] " + remaining1 + " - Remaining in the Pump 1.");

        System.out.print("Pump 2 : [");     //for queue2
        int count2 = 0;
        for (String sp2 : pump2) {
            if (sp2 == null) {
                continue;
            }
            System.out.print(sp2 + ",");
            count2++;
        }
        remaining2 = 6 - count2;
        System.out.println("] " + remaining2 + " - Remaining in the Pump 2.");

        System.out.print("Pump 3 : [");     //for queue3
        int count3 = 0;
        for (String sp3 : pump3) {
            if (sp3 == null) {
                continue;
            }
            System.out.print(sp3 + ",");
            count3++;
        }
        remaining3 = 6 - count3;
        System.out.println("] " + remaining3 + " - Remaining in the Pump 3.");

    }

    private static void viewEmpty() {
        int count1 = 0;
        for (String sp1 : pump1) {      //for queue1
            if (sp1 == null) {
                continue;
            }
            count1++;
        }
        remaining1 = 6 - count1;
        if (remaining1 >= 1 && remaining1 <= 5) {
            System.out.println("Pump 1 : " + remaining1 + " Empty space.");
        }
        if (remaining1 == 6 && count1 == 0) {
            System.out.println("Pump 1 : All Empty");
        }

        int count2 = 0;
        for (String sp2 : pump2) {      //for queue2
            if (sp2 == null) {
                continue;
            }
            count2++;
        }
        remaining2 = 6 - count2;
        if (remaining2 >= 1 && remaining2 <= 5) {
            System.out.println("Pump 2 : " + remaining2 + " Empty space.");
        }
        if (remaining2 == 6 && count2 == 0) {
            System.out.println("Pump 2 : All Empty");
        }

        int count3 = 0;
        for (String sp3 : pump3) {      //for queue3
            if (sp3 == null) {
                continue;
            }
            count3++;
        }
        remaining3 = 6 - count3;
        if (remaining3 >= 1 && remaining3 <= 5) {
            System.out.println("Pump 3 : " + remaining3 + " Empty space.");
        }
        if (remaining3 == 6 && count3 == 0) {
            System.out.println("Pump 3 : All Empty");
        }

    }

    private static void addCustomer() {
        System.out.println(" ");
        int pump = 0;
        Scanner anc = new Scanner(System.in); //Scanner for add new customer
        while (pump != 1 && pump != 2 && pump != 3 && pump != 4) {

            Arrays.stream(customerMenu).forEach(System.out::println);
            System.out.print("Please Enter the pump number to a add customer [1/2/3/4] : ");
            try {
                pump = anc.nextInt();              //try and catch
                if (pump <= 0 || pump > 4) {
                    System.out.println("\nOut of Range. Please Enter Integer [1/2/3/4].\n");
                }
            } catch (Exception e) {
                System.out.println("\nInvalid Input. Please Enter Integer [1/2/3/4].\n");
                anc.next();
            }
        }
        Scanner cn = new Scanner(System.in); //Scanner for customer name
        if (pump != 4) {
            System.out.print("\nEnter Customer name : ");
            String cname = cn.nextLine();

            if (pump == 1) {                                //for queue1
                for (int i = 0; i < pump1.length; i++) {
                    if (pump1[i] == null) {
                        pump1[i] = cname;
                        fuelStock = fuelStock - 10;
                        System.out.println("" + cname + " added to the Pump 1 queue.");
                        break;
                    }
                    if (i == 5) {
                        System.out.println("Can't add a customer to the Pump 1 Queue.\nPump 1 queue is full.");
                    }
                }
            }
            if (pump == 2) {                                //for queue2
                for (int i = 0; i < pump2.length; i++) {
                    if (pump2[i] == null) {
                        pump2[i] = cname;
                        fuelStock = fuelStock - 10;
                        System.out.println("" + cname + " added to the Pump 2 queue.");
                        break;
                    }
                    if (i == 5) {
                        System.out.println("Can't add a customer to the Pump 2 Queue.\nPump 2 queue is full.");
                    }
                }
            }
            if (pump == 3) {                                //for queue3
                for (int i = 0; i < pump3.length; i++) {
                    if (pump3[i] == null) {
                        pump3[i] = cname;
                        fuelStock = fuelStock - 10;
                        System.out.println("" + cname + " added to the Pump 3 queue.");
                        break;
                    }
                    if (i == 5) {
                        System.out.println("Can't add a customer to the Pump 3 Queue.\nPump 3 queue is full.");
                    }
                }
            }
        }
    }

    private static void removeCustomer() {
        System.out.println(" ");
        Scanner rc = new Scanner(System.in); //Scanner for remove customer
        int pump = 0;
        while (pump != 1 && pump != 2 && pump != 3 && pump != 4) {

            Arrays.stream(customerMenu).forEach(System.out::println);
            System.out.print("Please Enter the pump number to the remove customer [1/2/3/4] : ");
            try {
                pump = rc.nextInt();
                if (pump <= 0 || pump > 4) {
                    System.out.println("\nOut of Range. Please Enter Integer [1/2/3/4].\n");
                }
            } catch (Exception e) {
                System.out.println("\nInvalid Input. Please Enter Integer [1/2/3/4].\n");
                rc.next();
            }
        }

        if (pump != 4) {
            if (pump == 1) {                                    //for queue1
                System.out.println(Arrays.toString(pump1));
                System.out.print("Please Enter the position number for remove customer [1/2/3/4/5/6] : ");
                Scanner rcn = new Scanner(System.in);
                try{
                    int nc = rcn.nextInt();
                    if (nc<0 || nc>6){
                        System.out.println("\nOut of Range. Please Enter Integer [1/2/3/4/5/6].");
                    }else{
                        String[] np1 = new String[pump1.length];
                        for (int i=0, k=0; i<(pump1.length);i++){
                            if (i==nc-1){
                                continue;
                            }
                            np1[k++]=pump1[i];

                        }if (pump1[nc-1]==null){
                            System.out.println("Empty Customer in the position - "+nc+".");
                            System.out.println(Arrays.toString(pump1));
                        }else{
                            pump1=np1;
                            System.out.println(nc+" - Customer removed.");
                            System.out.println(Arrays.toString(pump1));
                        }
                    }
                } catch (Exception e) {
                    System.out.println("\nInvalid Input. Please Enter Integer [1/2/3/4/5/6].");
                }
            }
            if (pump == 2) {                                    //for queue2
                System.out.println(Arrays.toString(pump2));
                System.out.print("Please Enter the position number for remove customer [1/2/3/4/5/6] : ");
                try{
                    int nc = rc.nextInt();
                    if (nc<0 || nc>6){
                        System.out.println("\nOut of Range. Please Enter Integer [1/2/3/4/5/6].");
                    }else{
                        String[] np2 = new String[pump2.length];
                        for (int i=0, k=0; i<(pump2.length);i++){
                            if (i==nc-1){
                                continue;
                            }
                            np2[k++]=pump2[i];
                        }if (pump2[nc-1]==null){
                            System.out.println("Empty Customer in the position - "+nc+".");
                            System.out.println(Arrays.toString(pump2));
                        }else {
                            pump2=np2;
                            System.out.println(nc+" - Customer removed.");
                            System.out.println(Arrays.toString(pump2));
                        }
                    }
                } catch (Exception e) {
                    System.out.println("\nInvalid Input. Please Enter Integer [1/2/3/4/5/6].");
                }
            }
            if (pump == 3) {                                    //for queue3
                System.out.println(Arrays.toString(pump3));
                System.out.print("Please Enter the position number for remove customer [1/2/3/4/5/6] : ");
                try{
                    int nc = rc.nextInt();
                    if (nc<0 || nc>6){
                        System.out.println("\nOut of Range. Please Enter Integer [1/2/3/4/5/6].");
                    }else{
                        String[] np3 = new String[pump3.length];
                        for (int i=0, k=0; i<(pump3.length);i++){
                            if (i==nc-1){
                                continue;
                            }
                            np3[k++]=pump3[i];
                        }
                        if (pump3[nc-1]==null){
                            System.out.println("Empty Customer in the position - "+nc+".");
                            System.out.println(Arrays.toString(pump3));
                        }else {
                            pump3=np3;
                            System.out.println(nc+" - Customer removed.");
                            System.out.println(Arrays.toString(pump3));
                        }
                    }
                } catch (Exception e) {
                    System.out.println("\nInvalid Input. Please Enter Integer [1/2/3/4/5/6].");
                }
            }
        }
    }

    private static void removeServedCustomer(){
        System.out.println(" ");
        Scanner rc = new Scanner(System.in); //Scanner for remove customer
        int pump = 0;
        while (pump != 1 && pump != 2 && pump != 3 && pump != 4) {

            Arrays.stream(customerMenu).forEach(System.out::println);
            System.out.print("Please Enter the pump number to the remove customer [1/2/3/4] : ");
            try {
                pump = rc.nextInt();
                if (pump <= 0 || pump > 4) {
                    System.out.println("\nOut of Range. Please Enter Integer [1/2/3/4].\n");
                }
            } catch (Exception e) {
                System.out.println("\nInvalid Input. Please Enter Integer [1/2/3/4].\n");
                rc.next();
            }
        }
        if (pump != 4) {

            if (pump == 1) {                                    //for queue1
                System.out.println(Arrays.toString(pump1));
                System.out.print(pump1[0] + " - Customer.");
                fuelStock = fuelStock - 10;
                int nc = 1;
                String[] np1 = new String[pump1.length];
                for (int i=0, k=0; i<(pump1.length);i++){
                    if (i==0){
                        continue;
                    }
                    np1[k++]=pump1[i];
                }
                if (pump1[nc-1]==null){
                    System.out.println("Empty Customer in the position.");
                    System.out.println(Arrays.toString(pump1));
                }else {
                    pump1=np1;
                    System.out.println(" Served & removed.");
                    System.out.println(Arrays.toString(pump1));
                }
            }
            if (pump == 2) {                                    //for queue2
                System.out.println(Arrays.toString(pump2));
                System.out.print(pump2[0] + " - Customer");
                fuelStock = fuelStock - 10;
                int nc = 1;
                String[] np2 = new String[pump2.length];
                for (int i=0, k=0; i<(pump2.length);i++){
                    if (i==0){
                        continue;
                    }
                    np2[k++]=pump2[i];
                }
                if (pump2[nc-1]==null){
                    System.out.println("Empty Customer in the position.");
                    System.out.println(Arrays.toString(pump2));
                }else {
                    pump2=np2;
                    System.out.println(" Served & removed.");
                    System.out.println(Arrays.toString(pump2));
                }
            }
            if (pump == 3) {                                    //for queue3
                System.out.println(Arrays.toString(pump3));
                System.out.print(pump3[0] + " - Customer");
                fuelStock = fuelStock - 10;
                int nc = 1;
                String[] np3 = new String[pump3.length];
                for (int i=0, k=0; i<(pump3.length);i++){
                    if (i==0){
                        continue;
                    }
                    np3[k++]=pump3[i];
                }
                if (pump3[nc-1]==null){
                    System.out.println("Empty Customer in the position.");
                    System.out.println(Arrays.toString(pump3));
                }else {
                    pump3=np3;
                    System.out.println(" Served & removed.");
                    System.out.println(Arrays.toString(pump3));
                }
            }
        }
    }

    private static void viewCustomerSortedAorder(){
        System.out.println("\n");
        int x=0;
        for (String pn1 : pump1){       //for queue1 data
            if (pn1==null){
                break;
            }
            x++;
        }String [] sa1 =new String[x];
        System.arraycopy(pump1,0,sa1,0,x);
        for (int i=0; i<sa1.length; i++){
            for (int j=i+1; j<sa1.length;j++){
                if (sa1[i].compareToIgnoreCase(sa1[j])>0){
                    String s=sa1[i];
                    sa1[i]=sa1[j];
                    sa1[j]=s;
                }
            }
        }
        System.out.print("Pump 1 : [");
        for (String pn : sa1){
            System.out.print(pn+",");
        }
        System.out.print(" ]\n");

        int y=0;
        for (String pn2 : pump2){       //for queue2 data
            if (pn2==null){
                break;
            }
            y++;
        }String [] sa2 =new String[y];
        System.arraycopy(pump2,0,sa2,0,y);
        for (int i=0; i<sa2.length; i++){
            for (int j=i+1; j<sa2.length;j++){
                if (sa2[i].compareToIgnoreCase(sa2[j])>0){
                    String s2=sa2[i];
                    sa2[i]=sa2[j];
                    sa2[j]=s2;
                }
            }
        }
        System.out.print("Pump 2 : [");
        for (String pn2 : sa2){
            System.out.print(pn2+",");
        }
        System.out.print(" ]\n");

        int z=0;
        for (String pn3 : pump3){       //for queue3 data
            if (pn3==null){
                break;
            }
            z++;
        }String [] sa3 =new String[z];
        System.arraycopy(pump3,0,sa3,0,z);
        for (int i=0; i<sa3.length; i++){
            for (int j=i+1; j<sa3.length;j++){
                if (sa3[i].compareToIgnoreCase(sa3[j])>0){
                    String s3=sa3[i];
                    sa3[i]=sa3[j];
                    sa3[j]=s3;
                }
            }
        }
        System.out.print("Pump 3 : [");
        for (String pn3 : sa3){
            System.out.print(pn3+",");
        }
        System.out.print(" ]\n");
    }

    private static void storeProgramData()throws IOException {
        FileWriter fw = new FileWriter("fuel-data.txt");
        for (String pd : pump1){        //store queue1 data
            fw.write(pd + ",");
        }
        fw.write(System.lineSeparator());
        for (String pd : pump2){        //store queue2 data
            fw.write(pd + ",");
        }
        fw.write(System.lineSeparator());
        for (String pd : pump3){        //store queue3 data
            fw.write(pd + ",");
        }
        fw.write(System.lineSeparator());       //store fuel stock
        fw.write("Fuel Stock : "+fuelStock.toString());
        fw.close();
        System.out.println("Successfully, Data Stored to fuel-data text file.\n");
    }

    private static void lordProgramData() throws IOException{
        BufferedReader bufferedReader=new BufferedReader(new FileReader("fuel-data.txt"));
        String st;
        while ((st = bufferedReader.readLine()) != null){       //lord store data from text file
            System.out.println(st);
        }
    }

    private static void viewRemainingFuelStock(){
        System.out.print("\nRemaining Fuel Stock is "+fuelStock+"\n");
    }

    private static void addFuelStock(){
        Scanner af = new Scanner(System.in); //Scanner for add fuel
        viewRemainingFuelStock();
        System.out.println("Maximum Fuel Store - 6600 liters ");
        System.out.print("Enter the Liters to Add : ");
        double addFuel = af.nextDouble();
        if (6600>=addFuel+fuelStock){
            fuelStock=fuelStock+addFuel;
            System.out.println(addFuel+" Litters Added.");
            viewRemainingFuelStock();
        }else {
            System.out.println("Out of rang. Please Enter again.");
            addFuelStock();
        }
    }
}