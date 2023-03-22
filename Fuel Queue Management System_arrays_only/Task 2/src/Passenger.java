import java.io.Serializable;
public class Passenger implements Serializable {
    public String firstName;
    public String secondName;
    public String vehicleNumber;
    public int liters;

    public Passenger(String firstName, String secondName, String vehicleNumber, int liters) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.vehicleNumber = vehicleNumber;
        this.liters = liters;
    }
    public String getFirstName(){return firstName;}
    public String getSecondName(){return secondName;}
    public  String getVehicleNumber(){return vehicleNumber;}
    public int getLiters(){
        return liters;
    }

}
