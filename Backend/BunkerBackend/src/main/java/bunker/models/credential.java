package bunker.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.UUID;

@Entity
@Table(name = "credential")
public class credential {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "credentialId", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    UUID id;

    @Column
    String credentialName;

    @Column
    String userName;

    @Column
    String password;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private user user;

    //getters
    public UUID getId(){ return id; }
    public user getOwner() { return user; }
    public java.lang.String getUserName() { return userName; }
    public java.lang.String getPassword() { return password; }
    public java.lang.String getCredentialName() { return credentialName; }

    //setters

    public void setId(UUID id) { this.id = id; }
    public void setOwner(user owner){ this.user = owner; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setPassword(String password) { this.password = password; }
    public void setCredentialName(String credentialName) { this.credentialName = credentialName; }

    @Override
    public String toString() {
        return "credential{" +
                "owner=" + user.getId() +
                ", id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", credentialName='" + credentialName + '\'' +
                '}';
    }

    public HashMap toHashMap() {
        HashMap<String, String> returnVal = new HashMap<String, String>();
        returnVal.put("id", this.id.toString());
        returnVal.put("credentialName", this.credentialName);
        returnVal.put("userName", this.userName);
        returnVal.put("password", this.password);
        returnVal.put("owner", this.user.getId().toString());
        return returnVal;
    }
}
