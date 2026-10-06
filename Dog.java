 public class Dog {
 
    private int dogID;
    private String dogname;
    private Double dogweight;
    private int dogage;
    private String dogbreed;
    private String ownername; 


    // constructor 
    private Dog(int dogID, String dogname, double dogweight, int dogage, String dogbreed, String ownername) {
        this.dogID = dogID;
        this.dogname = dogname;
        this.dogweight = dogweight;
        this.dogage = dogage;
        this.dogbreed = dogbreed;
        this.ownername = ownername;
    }

    //getter 
    private int getdogID(){
        return dogID;
    }

    private String getDogname(){
        return dogname;
    }

    private Double getdogweight(){
        return dogweight;
    }

    private int getdogage(){
        return dogage;
    }

    private String getdogbreed(){
        return dogbreed;
    }

    private String getownername(){
        return ownername;
    }

    private String getdogowner(){
        return ownername;
    }

    // setters
    private void setdogID(int dogID) {
        this.dogID = dogID;
    }

    private void setdogname(String dogname) {
        this.dogname = dogname;
    }

    private void setdogweight(Double dogweight) {
        this.dogweight = dogweight;
    }

    private void setdogage(int dogage) {
        this.dogage = dogage;
    }

    private void setdogbreed(String dogbreed) {
        this.dogbreed = dogbreed;
    }

    private void setownername(String ownername) {
        this.ownername = ownername;
    }

    // toString()
    @Override
    public String toString() {
        return "Dogs{" +
                "dogID=" + dogID +
                ", dogname='" + dogname + '\'' +
                ", dogweight=" + dogweight +
                ", dogage=" + dogage +
                ", dogbreed='" + dogbreed + '\'' +
                ", ownername='" + ownername + '\'' +
                '}';
    }

}; 
