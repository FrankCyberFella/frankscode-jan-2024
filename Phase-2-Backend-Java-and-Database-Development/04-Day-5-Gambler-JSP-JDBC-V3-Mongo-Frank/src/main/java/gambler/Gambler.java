package gambler;

import java.time.LocalDate;
import java.util.Objects;

import org.bson.types.ObjectId;

/****************************************************************************************************
 	POJO for gambler table
 
 	gambler table as defined in the database
  	
 	+----------------+--------------+------+-----+---------+----------------+
    | Field          | Type         | Null | Key | Default | Extra          |
    +----------------+--------------+------+-----+---------+----------------+
    | id             | smallint     | NO   | PRI | NULL    | auto_increment |
    | gambler_name   | char(20)     | NO   |     | NULL    |                |
    | address        | char(20)     | YES  |     | NULL    |                |
    | birth_date     | date         | NO   |     | NULL    |                |
    | monthly_salary | decimal(9,2) | NO   |     | NULL    |                |
    +----------------+--------------+------+-----+---------+----------------+

 *****************************************************************************************************/

public class Gambler {
	/****************************************************************************************************
	 * 	Instance variables to hold columns for a row in the table
	 * 
	 *  Names do not necessarily have to match column names in the table, but typically do
	 ****************************************************************************************************/

	private ObjectId  id;
	private int       gamblerId;
	private String    name;
	private String    address;
	private String    birthDate;
	private double    monthlySalary;
	
	/****************************************************************************************************
	 * Constructors
	 * 
	 * Should have at least a default constructor and an all variables constructor
	 ****************************************************************************************************/
	
	public Gambler() {}; //Default ctor required due having other ctors

	public Gambler(int id, String name, String address, String birthDate, double monthlySalary) {
		this.gamblerId     = gamblerId;
		this.name          = name;
		this.address       = address;
		this.birthDate     = birthDate;
		this.monthlySalary = monthlySalary;
	}
	
	// Copy constructor - create a Gambler from a Gambler - used to create deep copy of a Gambler
	public Gambler(Gambler sourceGambler) {
		this.id            = sourceGambler.getId();
		this.name          = sourceGambler.getName();
		this.address       = sourceGambler.getAddress();
		this.birthDate     = sourceGambler.getBirthDate();
		this.monthlySalary = sourceGambler.getMonthlySalary();			
	}
	
	/****************************************************************************************************
	 * Getters and Setters with standard names
	 ****************************************************************************************************/
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public int getGamblerId() {
		return gamblerId;
	}
	public void setGamblerId(int gamblerId) {
		this.gamblerId = gamblerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public double getMonthlySalary() {
		return monthlySalary;
	}
	public void setMonthlySalary(double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	
	/****************************************************************************************************
	 * 	Standard overrides of Object class methods tailored for this class
	 ****************************************************************************************************/

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gambler other = (Gambler) obj;
		return Objects.equals(address, other.address) && Objects.equals(birthDate, other.birthDate) && gamblerId == other.gamblerId
				&& Double.doubleToLongBits(monthlySalary) == Double.doubleToLongBits(other.monthlySalary)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "gambler [onjectId=" + id + ", gamblerId=" + gamblerId + ", name=" + name + ", address=" + address + ", birthDate=" + birthDate
				+ ", monthlySalary=" + monthlySalary + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(address, birthDate, id, gamblerId, monthlySalary, name);
	}

	/****************************************************************************************************
	 * Helper methods for this class
	 ****************************************************************************************************/
	public void displayGambler() { // Display data in a gambler object
			
		System.out.println("$".repeat(30));
		System.out.println("Gambler Name: "   + name
				          +"\nGambler Id: "   + gamblerId
				          +"\n     Address: " + address
				          +"\n    Birthday: " + birthDate
				          +"\n Monthly Pay: " + monthlySalary);
	}

} // End of gambler class
