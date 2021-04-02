package data;

public class Data {

	private int id;
    private String email;
    private String job;
    private String name;
    private String pass;
    private String ph;
    
	public Data(int id, String email, String job, String name, String pass, String ph) {
		this.id = id;
		this.email = email;
		this.job = job;
		this.name = name;
		this.pass = pass;
		this.ph = ph;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
   

}
