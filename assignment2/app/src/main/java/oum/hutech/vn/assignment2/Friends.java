package oum.hutech.vn.assignment2;

public class Friends {
    private int id;
    private String name;
    private int phoneNo;
    private String address;

    public Friends(int id, String name, int phoneNo, String address) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    public Friends(){
        this.id = 0;
        this.name = null;
        this.phoneNo = 0;
        this.address = null;
    }

    public int getId()   {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


