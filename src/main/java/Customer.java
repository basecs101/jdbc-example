public class Customer {
    private int id;
    private String name;
    private String contactName;
    private String address;
    private String city;
    private int postalCode;
    private String country;

    public Customer(int id, String name, String contactName, String address, String city, int postalCode, String country) {
        this.id = id;
        this.name = name;
        this.contactName = contactName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public int getId() {
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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
