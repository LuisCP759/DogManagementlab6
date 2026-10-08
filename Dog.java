 public class Dog {
 
    private int dogID;
    private String dogname;
    private Double dogweight;
    private int dogage;
    private String dogbreed;
    private String ownername; 


    // default constructior
    // do not have to be final  there just default value 
    public Dog() {
        dogID = 0;
        dogname = "";
        dogweight = 0.0;
        dogage = 0;
        dogbreed = "";
        ownername = "";
    }

    // constructor 
    public Dog(int dogID, String dogname, double dogweight, int dogage, String dogbreed, String ownername) {
        this.dogID = dogID;
        this.dogname = dogname;
        this.dogweight = dogweight;
        this.dogage = dogage;
        this.dogbreed = dogbreed;
        this.ownername = ownername;
    }

    //getter 
    public int getdogID(){
        return dogID;
    }

    public String getDogname(){
        return dogname;
    }

    public Double getdogweight(){
        return dogweight;
    }

    public int getdogage(){
        return dogage;
    }

    public String getdogbreed(){
        return dogbreed;
    }

    public String getownername(){
        return ownername;
    }


    // setters
    public void setdogID(int dogID) {
        this.dogID = dogID;
    }

    public void setdogname(String dogname) {
        this.dogname = dogname;
    }

    public void setdogweight(Double dogweight) {
        this.dogweight = dogweight;
    }

    public   void setdogage(int dogage) {
        this.dogage = dogage;
    }

    public void setdogbreed(String dogbreed) {
        this.dogbreed = dogbreed;
    }

    public void setownername(String ownername) {
        this.ownername = ownername;
    }

    // toString()
    @Override
    public String toString() {
        return "Dog{" +
                "dogID=" + dogID +
                ", dogname='" + dogname + '\'' +
                ", dogweight=" + dogweight +
                ", dogage=" + dogage +
                ", dogbreed='" + dogbreed + '\'' +
                ", ownername='" + ownername + '\'' +
                '}';
    }

}
