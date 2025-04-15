package entity;

public class CustomerCustomerDemo {
    private String customerID;
    private String customerTypeID;

    // Getters and Setters
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerTypeID() {
        return customerTypeID;
    }

    public void setCustomerTypeID(String customerTypeID) {
        this.customerTypeID = customerTypeID;
    }

    @Override
    public String toString() {
        return "CustomerCustomerDemo{" +
                "customerID='" + customerID + '\'' +
                ", customerTypeID='" + customerTypeID + '\'' +
                '}';
    }
}
