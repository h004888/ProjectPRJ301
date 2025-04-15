package entity;

public class Region {
    private int RegionID;
    private String regionDescription;

    // Getters and Setters
    public int getRegionID() {
        return RegionID;
    }

    public void setRegionID(int RegionID) {
        this.RegionID = RegionID;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    @Override
    public String toString() {
        return "Region{" +
                "RegionID=" + RegionID +
                ", regionDescription='" + regionDescription + '\'' +
                '}';
    }
}
