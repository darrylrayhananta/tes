package Actions;

public abstract class Action {
    protected String name;
    protected int energyRequired;
    protected int timeRequired;

    public Action(String name, int energyRequired, int timeRequired) {
        this.name = name;
        this.energyRequired = energyRequired;
        this.timeRequired = timeRequired;
    }

    public String getName() {
        return name;
    }

    public int getEnergyRequired() {
        return energyRequired;
    }

    public int getTimeRequired() {
        return timeRequired;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnergyRequired(int energyRequired) {
        if (energyRequired < 0) {
            this.energyRequired = 0;
        } else {
            this.energyRequired = energyRequired;
        }
    }

    public void setTimeRequired(int timeRequired) {
        if (timeRequired < 0) {
            this.timeRequired = 0;
        } else {
            this.timeRequired = timeRequired;
        }
    }

    public abstract void executeAction();
}