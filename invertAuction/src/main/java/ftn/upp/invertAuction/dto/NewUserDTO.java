package ftn.upp.invertAuction.dto;

public class NewUserDTO {
    private String name;
    private String mail;
    private String username;

    public NewUserDTO(String name, String mail, String username) {
        this.name = name;
        this.mail = mail;
        this.username = username;
    }

    public NewUserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
