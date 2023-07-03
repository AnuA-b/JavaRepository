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
//Create PDF files with Label: High performer or Medium performer or Low performer

            Instances data = ConverterUtils.DataSource.read("C:\\Users\\Dell\\eclipse-workspace\\td1.arff");
            data.setClassIndex(data.numAttributes() - 1);

            // Create the Naive Bayes classifier
            Classifier classifier = new NaiveBayes();
            classifier.buildClassifier(data);

            // Loop through the instances
            for (int i = 0; i < data.numInstances(); i++) {
                // Get the instance and class label
                double classLabel = classifier.classifyInstance(data.instance(i));
                String studentNo = "";
                if (data.numAttributes() > 0) {
                    studentNo = data.instance(i).stringValue(0);
                }
                String predictedClassLabel = data.classAttribute().value((int) classLabel);

                // Create a PDF file per student
                String pdfFileName = studentNo + ".pdf";
                Document document = new Document();
           	 Path outputPath = Paths.get("C:\\Users\\Dell\\eclipse-workspace\\" + pdfFileName);
               PdfWriter.getInstance(document, new FileOutputStream(outputPath.toFile()));
                document.open();
                document.add(new Paragraph("Student No: " + studentNo));
                document.add(new Paragraph("Label: " + predictedClassLabel));
                document.close();
            }

            System.out.println("\nPDFs have been created successfully.");
	    
            

