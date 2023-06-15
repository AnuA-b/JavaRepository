
public class Student {
	private String name;
    private String studentClass;
    private String section;
    private String phoneNumber;
    private String email;
    private String studentNo;
    private int age;

    // Constructor
    public Student(String name, String studentClass, String section, String phoneNumber, String email, String studentNo, int age) {
        this.name = name;
        this.studentClass = studentClass;
        this.section = section;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studentNo = studentNo;
        this.age = age;
    }

	@Override
	public String toString() {
		return "[name=" + name + ", studentClass=" + studentClass + ", section=" + section + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", studentNo=" + studentNo + ", age=" + age + "]";
	}
    
    
}
