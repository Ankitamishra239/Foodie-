package ashiana.com.foodie;

public class Exercise_Model {

    private String name, running, walking, swimming, cycling;

    public Exercise_Model() {
        // for firebase
    }

    public Exercise_Model(String name, String running, String walking, String swimming, String cycling) {
        this.name = name;
        this.running = running;
        this.walking = walking;
        this.swimming = swimming;
        this.cycling = cycling;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRunning() {
        return running;
    }

    public void setRunning(String running) {
        this.running = running;
    }

    public String getWalking() {
        return walking;
    }

    public void setWalking(String walking) {
        this.walking = walking;
    }

    public String getSwimming() {
        return swimming;
    }

    public void setSwimming(String swimming) {
        this.swimming = swimming;
    }

    public String getCycling() {
        return cycling;
    }

    public void setCycling(String cycling) {
        this.cycling = cycling;
    }
}
