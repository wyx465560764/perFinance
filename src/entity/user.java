package entity;

public class user {
    private String userid;
    private String email;
    private String password;
    private String username;
    private String type;
    private String identity;
    private String managercontext;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getManagercontext() {
        return managercontext;
    }

    public void setManagercontext(String managercontext) {
        this.managercontext = managercontext;
    }

}
