package hu.danceschool.domain.model;

public class Dance {

    private final String danceName;
    private final String girlName;
    private final String boyName;

    public Dance(String danceName, String girlName, String boyName) {
        this.danceName = danceName;
        this.girlName = girlName;
        this.boyName = boyName;
    }

    public String getDanceName() {
        return danceName;
    }

    public String getGirlName() {
        return girlName;
    }

    public String getBoyName() {
        return boyName;
    }

    public boolean isDance(String danceName) {
        return this.danceName.equals(danceName);
    }

    public boolean isGirl(String girlName) {
        return this.girlName.equals(girlName);
    }
}
