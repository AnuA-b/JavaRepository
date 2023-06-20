//Consider you are running a tutorial. we need to create an application which can classify the students based on their performance.
//Create a POJO object which can have the student detail like name, class, section,phoneno, email,studentno,age
//Create a program which can display details of all the students using the concept of Collections.

public class StudentMain {
	public static void main(String[] args) {
	
		Student s1=new Student("Rajesh","10th","A","1234567891","rajesh@abc.com","S01",16); //a student class is created to initialize these values
		Student s2=new Student("Shyama","10th","B","5234567880","shyama@abc.com","S02",16);
		Student s3=new Student("Sunita","10th","A","9334567812","sunita@abc.com","S03",16);
	
		List<Student> students=new ArrayList<>();
		students.add(s1);
		students.add(s2);
		students.add(s3);
	
		System.out.println("Student Details: ");
		for(Student student: students)
		{
			System.out.println(student);
		}
    
//Create a temporary data in a excel format with the following-Student No, No Of Classes Attended,Total Exam Marks,Dairy completion rate,Month
//Write a program which can read the excel file and print the above data using collection framework.

try {
            File file = new File("C:\\Users\\Dell\\eclipse-workspace\\Book1.xlsx");
            FileInputStream fis = new FileInputStream(file);

            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            List<StudentData> studentDataList = new ArrayList<>();

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // Skip header row
                if (row.getRowNum() == 0) {
                    continue;
                }
                String studentNo = row.getCell(0).getStringCellValue();
                int classesAttended =(int) row.getCell(1).getNumericCellValue();
                int totalExamMarks = (int)row.getCell(2).getNumericCellValue();
                double dairyCompletionRate =(double) row.getCell(3).getNumericCellValue();
                String month = row.getCell(4).getStringCellValue();
              

                StudentData studentData = new StudentData(studentNo,classesAttended , totalExamMarks, dairyCompletionRate, month);
                studentDataList.add(studentData);
            }
            // Print student data
            System.out.println("\nStudent Details in the excel file: ");
            for (StudentData studentData : studentDataList) {
                System.out.println(studentData);
            }
            
//Compute the total across all the months for each student no- Classes Attended,Total Exam marks,Dairy completion rate

            // Compute the total classes attended for each student
            Map<String, Integer> totalClassesAttendedMap = new TreeMap<>();
            for (StudentData student : studentDataList) {
                String studentNo = student.getStudentNo();
                int classesAttended = student.getClassesAttended();

                totalClassesAttendedMap.put(studentNo, totalClassesAttendedMap.getOrDefault(studentNo, 0) + classesAttended);
            }

            // Compute the total exam marks for each student
            Map<String, Double> totalExamMarksMap = new TreeMap<>();
            for (StudentData student : studentDataList) {
                String studentNo = student.getStudentNo();
                int examMarks = student.getTotalExamMarks();

                totalExamMarksMap.put(studentNo, totalExamMarksMap.getOrDefault(studentNo, (double) 0) + examMarks);
            }
            
         // Compute the total dairy completion rate for each student
            Map<String, Double> totalDairyCompletionRateMap = new TreeMap<>();
            for (StudentData student : studentDataList) {
                String studentNo = student.getStudentNo();
                double dairyCompletionRate = student.getDairyCompletionRate();

                totalDairyCompletionRateMap.put(studentNo, totalDairyCompletionRateMap.getOrDefault(studentNo, 0.0) + dairyCompletionRate);
            }

            // Print the total across all months for each student
            for (String studentNo : totalClassesAttendedMap.keySet()) {
                int totalClassesAttended = totalClassesAttendedMap.get(studentNo);
                Double totalExamMarks = totalExamMarksMap.get(studentNo);
                double totalDairyCompletionRate = totalDairyCompletionRateMap.get(studentNo);

                System.out.println("\nStudent No: " + studentNo);
                System.out.println("Total Classes Attended: " + totalClassesAttended);
                System.out.println("Total Exam Marks: " + totalExamMarks);
                System.out.println("Total Dairy Completion Rate: " + totalDairyCompletionRate);
               
            }
            
             // Close the workbook and file input stream
            workbook.close();
            fis.close();
            
//Find the high performing, medium performing and low performing students based on Various attributes 
//Number of Classes Attended, Total Exam Marks and Dairy completion rate
//Make use of algorithm of your choice
//For all the students print the labels – high performing, low performing and medium performing.

try {
			 Instances data = ConverterUtils.DataSource.read("C:\\Users\\Dell\\eclipse-workspace\\td1.arff");

		        //select only from column 1 to the end
		        Instances subData = new Instances(data, 1, data.numInstances() - 1);
		        // Create the K-means clustering algorithm
		        SimpleKMeans kMeans = new SimpleKMeans();
		        kMeans.setNumClusters(3); // Set the number of clusters

		        // Build clusters
		        kMeans.buildClusterer(subData);

		        //Build an arrayList of 3 clusters
		       
		        ArrayList<String> c0 = new ArrayList<>();    
		        ArrayList<String> c1 = new ArrayList<>();
		        ArrayList<String> c2 = new ArrayList<>();
		        // Assign instances to clusters
		        System.out.println("\nStudent Numbers and their corresponding clusters: ");
		        for (int i = 0; i < data.numInstances(); i++) {
		            int res = kMeans.clusterInstance(data.instance(i));
		            Attribute firstAttribute = data.attribute(0);
		            String n = firstAttribute.value(i);
		            if (res == 0) {
		            	String n1=n+": Low Performer";
		                System.out.println(n+" "+res+" Low Performer");
		                c0.add(n1);
		                
		            }
		            if (res == 1) {
		            	String n2=n+": High Performer";
		                System.out.println(n+" "+res+" High Performer");
		                c1.add(n2);

		            }
		            if (res == 2) {
		            	String n3=n+": Medium Performer";
		                System.out.println(n+" "+res+" Medium performer");
		                c2.add(n3);
		            }
		        }
            
//Create PDF files with Label: High performer or Medium performer or Low performer

CreatePDFThread thread1 = new CreatePDFThread(c0, "doc1.pdf");
	        CreatePDFThread thread2 = new CreatePDFThread(c1, "doc2.pdf");
	        CreatePDFThread thread3 = new CreatePDFThread(c2, "doc3.pdf");

	        thread1.start();
	        thread2.start();
	        thread3.start();
	        try {
	            thread1.join();
	            thread2.join();
	            thread3.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        System.out.println("\nPDFs have been created successfully.");
          } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
//Code snippet for CreatePDFThread Class

class CreatePDFThread extends Thread {
    private ArrayList<String> arrayList;
    private String filename;

    public CreatePDFThread(ArrayList<String> arrayList, String filename) {
        this.arrayList = arrayList;
        this.filename = filename;
    }

    @Override
    public void run() {
        
        Document doc = new Document();

        try {
            Path outputPath = Paths.get("C:\\Users\\Dell\\eclipse-workspace\\" + filename);
            PdfWriter.getInstance(doc, new FileOutputStream(outputPath.toFile()));
            doc.open();

            for (String element : arrayList) {
                doc.add(new Paragraph(element));
            }

            doc.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
