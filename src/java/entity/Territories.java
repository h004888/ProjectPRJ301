package entity;

public class Territories {
    private String territoryID;
    private String territoryDescription;
    private int regionID;

    // Getters and Setters
    public String getTerritoryID() {
        return territoryID;
    }

    public void setTerritoryID(String territoryID) {
        this.territoryID = territoryID;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        this.territoryDescription = territoryDescription;
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    @Override
    public String toString() {
        return "Territories{" +
                "territoryID='" + territoryID + '\'' +
                ", territoryDescription='" + territoryDescription + '\'' +
                ", regionID=" + regionID +
                '}';
    }
}
