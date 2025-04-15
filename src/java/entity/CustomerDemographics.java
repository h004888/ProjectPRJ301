package entity;

public class CustomerDemographics {
    private String customerTypeID;
    private String customerDesc;

    // Getters and Setters
    public String getCustomerTypeID() {
        return customerTypeID;
    }

    public void setCustomerTypeID(String customerTypeID) {
        this.customerTypeID = customerTypeID;
    }

    public String getCustomerDesc() {
        return customerDesc;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }

    @Override
    public String toString() {
        return "CustomerDemographics{" +
                "customerTypeID='" + customerTypeID + '\'' +
                ", customerDesc='" + customerDesc + '\'' +
                '}';
    }
}
