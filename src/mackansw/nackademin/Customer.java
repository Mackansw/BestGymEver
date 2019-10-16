package mackansw.nackademin;

public class Customer {

    private String socialNumber;
    private String name;
    private String lastVisit;

    public Customer(String socialNumber, String name, String lastVisit) {
        this.socialNumber = socialNumber;
        this.name = name;
        this.lastVisit = lastVisit;
    }

    public String getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(String socialNumber) {
        this.socialNumber = socialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }
}