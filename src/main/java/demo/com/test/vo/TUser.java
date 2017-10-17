package demo.com.test.vo;


public class TUser{
	
	
	private int id;
	private String name;
	private int age;
	private String sex;
	private String city;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "TUser [id=" + id + ", name=" + name + ", age=" + age + ", sex="
				+ sex + ", city=" + city + "]";
	}
	
	
}
