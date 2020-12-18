package bunker.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.UUID;

@Entity
@Table(name = "friend")
public class friend {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "friendId", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    UUID id;

    @Column
    String friendName;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private user user;

    //getters
    public UUID getId(){ return id; }
    public user getOwner() { return user; }
    public java.lang.String getFriendName() { return friendName; }

    //setters

    public void setId(UUID id) { this.id = id; }
    public void setOwner(user owner){ this.user = owner; }
    public void setFriendName(String friendName) { this.friendName = friendName; }

    @Override
    public String toString() {
        return "friend{" +
                "owner=" + user.getId() +
                ", id='" + id + '\'' +
                ", friendName='" + friendName + '\'' +
                '}';
    }

    public HashMap toHashMap() {
        HashMap<String, String> returnVal = new HashMap<String, String>();
        returnVal.put("id", this.id.toString());
        returnVal.put("friendName", this.friendName);
        returnVal.put("owner", this.user.getId().toString());
        return returnVal;
    }
}//test
