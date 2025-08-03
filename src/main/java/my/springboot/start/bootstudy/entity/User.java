package my.springboot.start.bootstudy.entity;

public class User {
    int ID;

    String name;

    int age;

    public User(int id, String name, int age, String password) {
        super();
        this.ID = id;
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public User() {
    }

    String password;

    /**
     * Get the id.
     * 
     * @return the specified value.
     */
    public int getId() {
        return ID;
    }

    /**
     * Set the id to the specified value.
     * 
     * @param id the specified value.
     */
    public void setId(int id) {
        this.ID = id;
    }

    /**
     * Get the name.
     * 
     * @return the specified value.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name to the specified value.
     * 
     * @param name the specified value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the age.
     * 
     * @return the specified value.
     */
    public int getAge() {
        return age;
    }

    /**
     * Set the age to the specified value.
     * 
     * @param age the specified value.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get the password.
     * 
     * @return the specified value.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password to the specified value.
     * 
     * @param password the specified value.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
