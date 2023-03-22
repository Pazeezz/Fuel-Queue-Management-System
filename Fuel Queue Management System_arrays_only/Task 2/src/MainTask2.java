import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MainTask2 {

    static FuelQueue q1 = new FuelQueue();  //for queue1
    static FuelQueue q2 = new FuelQueue();  //for queue2
    static FuelQueue q3 = new FuelQueue();  //for queue3
    static FuelQueue q4 = new FuelQueue();  //for queue4
    static FuelQueue q5 = new FuelQueue();  //for queue5


    public static void main(String[] args) throws IOException {

        System.out.println("\n<<<------Welcome to Fuel Management System------>>>\n");
        String option;

        do {
            Arrays.stream(FuelQueue.menuOption).forEach(System.out::println);
            Scanner mainInput = new Scanner(System.in);
            System.out.print("\nEnter Your Option : ");
            option = mainInput.next();

            if (FuelQueue.fuelStock <= 500) {
                System.out.println("\nWarning !!! \n" + "Fuel level Low: " + FuelQueue.fuelStock + " Liters !!!");
            }
            switch (option) {
                case "100", "VFQ" -> {
                    System.out.println("\nAll Fuel Queues\n");
                    q1.viewAll(1);
                    q2.viewAll(2);
                    q3.viewAll(3);
                    q4.viewAll(4);
                    q5.viewAll(5);
                }
                case "101", "VEQ" -> {
                    System.out.println("\nAll Empty Queues.");
                    q1.viewEmpty(1);
                    q2.viewEmpty(2);
                    q3.viewEmpty(3);
                    q4.viewEmpty(4);
                    q5.viewEmpty(5);
                }
                case "102", "ACQ" -> {
                    System.out.println("\nAdd Customer");
                    int queue1 = q1.queueLength();
                    int queue2 = q2.queueLength();
                    int queue3  = q3.queueLength();
                    int queue4  = q4.queueLength();
                    int queue5  = q5.queueLength();
                    int[] acqArray = {queue1, queue2, queue3, queue4, queue5};
                    for (int i=0; i<5; i++){
                        for (int j=i+1; j<5; j++){
                            if (acqArray[i]>acqArray[j]){
                                int acq = acqArray[i];
                                acqArray[i] = acqArray[j];
                                acqArray[j] = acq;
                            }
                        }
                    }
                    if (acqArray[0]==queue1 ){
                        q1.addCustomer();
                    }else if (acqArray[0]==queue2) {
                        q2.addCustomer();
                    }else if (acqArray[0]==queue3) {
                        q3.addCustomer();
                    }else if (acqArray[0]==queue4) {
                        q4.addCustomer();
                    } else if (acqArray[0]==queue5 ) {
                        q5.addCustomer();
                    }
                }
                case "103", "RCQ" -> {
                    System.out.println("\nRemove Customer");
                    int qNum = q1.getQueueNum();
                    int pNum = q1.getPlaceNum();
                    switch (qNum){
                        case 1 -> q1.removeCustomer(pNum);
                        case 2 -> q2.removeCustomer(pNum);
                        case 3 -> q3.removeCustomer(pNum);
                        case 4 -> q4.removeCustomer(pNum);
                        case 5 -> q5.removeCustomer(pNum);
                    }
                }
                case "104", "PCQ" -> {
                    System.out.println("\nRemove Served Customer");
                    int qNum = q1.getQueueNum();
                    switch (qNum){
                        case 1 -> q1.removeServedCustomer();
                        case 2 -> q2.removeServedCustomer();
                        case 3 -> q3.removeServedCustomer();
                        case 4 -> q4.removeServedCustomer();
                        case 5 -> q5.removeServedCustomer();
                    }
                }
                case "105", "VCS" -> {
                    System.out.println("\nCustomers Sorted in Alphabetical order");
                    q1.viewCustomerSortedAorder(1);
                    q2.viewCustomerSortedAorder(2);
                    q3.viewCustomerSortedAorder(3);
                    q4.viewCustomerSortedAorder(4);
                    q5.viewCustomerSortedAorder(5);
                }
                case "106", "SPD" -> {
                    System.out.println("\nStore Program Data into Text files.");
                    q1.storeProgramData("Queue 1 Data_File.txt");
                    q2.storeProgramData("Queue 2 Data_File.txt");
                    q3.storeProgramData("Queue 3 Data_File.txt");
                    q4.storeProgramData("Queue 4 Data_File.txt");
                    q5.storeProgramData("Queue 5 Data_File.txt");
                    System.out.println("Successfully, Data Stored to Text files.");
                }
                case "107", "LDP" -> {
                    System.out.println("\nLord Program Data form Text files.");
                    q1.lordProgramData("Queue 1 Data_File.txt");
                    q2.lordProgramData("Queue 2 Data_File.txt");
                    q3.lordProgramData("Queue 3 Data_File.txt");
                    q4.lordProgramData("Queue 4 Data_File.txt");
                    q5.lordProgramData("Queue 5 Data_File.txt");
                    System.out.println("Successfully, Data Lorded from Text files.");
                }
                case "108", "STK" -> FuelQueue.viewRemainingFuelStock();
                case "109", "AFS" -> {
                    System.out.println("\nAdd Fuel Stock");
                    FuelQueue.addFuelStock();
                }
                case "110", "IFQ" -> {
                    System.out.println("Income of each Fuel queue.");
                    System.out.println("Queue 1 Income : Rs. " + q1.getIncome());
                    System.out.println("Queue 2 Income : Rs. " + q2.getIncome());
                    System.out.println("Queue 3 Income : Rs. " + q3.getIncome());
                    System.out.println("Queue 4 Income : Rs. " + q4.getIncome());
                    System.out.println("Queue 5 Income : Rs. " + q5.getIncome());
                    System.out.println("Total Income : Rs. "+(q1.getIncome()+q2.getIncome()+q3.getIncome()+q4.getIncome()+q5.getIncome()));
                }
                case "999", "EXT" -> System.out.println("\nExiting the Program...Thank You.");
                default -> System.out.println("Invalid Input. Please Enter Again.");
            }
            System.out.println("\n");
        } while (!option.equals("999") && !option.equals("EXT"));
    }
}
