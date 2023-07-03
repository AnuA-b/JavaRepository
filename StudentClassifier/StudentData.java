
public class StudentData {

	private String studentNo;
	private int classesAttended;
	private int totalExamMarks;
	private double dairyCompletionRate;
	private String month;
	
	
	public StudentData(String studentNo,int classesAttended,int totalExamMarks,double dairyCompletionRate,String month ) {
	this.studentNo=studentNo;
	this.classesAttended=classesAttended;
	this.totalExamMarks=totalExamMarks;
	this.dairyCompletionRate=dairyCompletionRate;
	this.month=month;
	
	
	}	
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public int getClassesAttended() {
		return classesAttended;
	}
	public void setClassesAttended(int classesAttended) {
		this.classesAttended = classesAttended;
	}
	public int getTotalExamMarks() {
		return totalExamMarks;
	}
	public void setTotalExamMarks(int totalExamMarks) {
		this.totalExamMarks = totalExamMarks;
	}
	public double getDairyCompletionRate() {
		return dairyCompletionRate;
	}
	public void setDairyCompletionRate(double dairyCompletionRate) {
		this.dairyCompletionRate = dairyCompletionRate;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	@Override
	public String toString() {
		return " [studentNo=" + studentNo + ", classesAttended=" + classesAttended + ", totalExamMarks="
				+ totalExamMarks + ", dairyCompletionRate=" + dairyCompletionRate + ", month=" + month + "]";
	}

}
