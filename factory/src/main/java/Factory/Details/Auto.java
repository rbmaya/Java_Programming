package Factory.Details;

import java.util.ArrayList;

public class Auto extends Detail {
    private Body body;
    private Motor motor;
    private ArrayList<Accessory> accessories;

    public Auto(Body body, Motor motor, ArrayList<Accessory> accessories){
        this.body = body;
        this.motor = motor;
        this.accessories = accessories;
    }


    @Override
    public String getID() {
        StringBuilder string = new StringBuilder();
        string.append("Auto: ").append(super.getID()).append(". ");
        string.append("(Body: ").append(body.getID()).append(", ");
        string.append("Motor: ").append(motor.getID()).append(", Accessory: ");
        for (Accessory accessory: accessories){
            string.append(accessory.getID()).append(", ");
        }
        string.replace(string.length() - 3, string.length() - 1, ").");

        return string.toString();
    }
}
