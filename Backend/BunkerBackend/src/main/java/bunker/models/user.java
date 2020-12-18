package bunker.models;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
public class user {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GenericGenerator(strategy = org.hibernate.id.UUIDGenerator)
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "userId", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
	UUID id;

	@Column(unique = true)
	String user;

	@Column
	String address;

	@Column
	String firstName;

	@Column
	String lastName;

	@Column(unique = true)
	String email;

	@Column(unique = true)
	String phone;

	@Column
	String password;

	//@Column
	//UUID serviceId;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<credential> credential;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<friend> friend;


	//getters
	public UUID getId() { return id; }
	public String getUser() { return user; }
	public String getAddress() { return address; }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getEmail() { return email; }
	public String getPhone() { return phone; }
	public String getPassword() { return password; }
	public Set getCredentials() { return credential; }
	public Set getFriends() { return friend; }
	//public UUID getServiceId() { return serviceId; }


	//setters
	public void setId(UUID id) { this.id = id; }
	public void setUser(String user) { this.user = user; }
	public void setAddress(String address) { this.address = address; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setEmail(String email) { this.email = email; }
	public void setPhone(String phone) { this.phone = phone; }
	public void setPassword(String password) { this.password = password; }
	public void setCredentials(Set credential) { this.credential = credential; }
	public void setFriends(Set friend) { this.friend = friend; }
	//public void setServiceId(UUID serviceId) { this.serviceId = serviceId; }


	//public bunker.models.user User(){ return this; }

	@Override
	public String toString() {
		return "user{" +
				"id=" + id +
				", user='" + user + '\'' +
				", address='" + address + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", password='" + password + '\'' +
				//", serviceId='" + serviceId + '\'' +
				'}';
	}

	public HashMap toHashMap() {
		HashMap<String, String> returnVal = new HashMap<String, String>();
		returnVal.put("id", this.id.toString());
		returnVal.put("user", this.user);
		returnVal.put("address", this.address);
		returnVal.put("firstName", this.firstName);
		returnVal.put("lastName", this.lastName);
		returnVal.put("email", this.email);
		returnVal.put("phone", this.phone);
		returnVal.put("password", this.password);
		return returnVal;
	}
}
