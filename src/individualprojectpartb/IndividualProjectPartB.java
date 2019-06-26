
package individualprojectpartb;

import dao.AssignmentDao;
import dao.CourseDao;
import dao.EnrollmentDao;
import dao.HomeworkDao;
import dao.Login;
import dao.StudentDao;
import dao.TrainerCourseDao;
import dao.TrainerDao;
import entities.Assignment;
import entities.Course;
import entities.Enrollment;
import entities.Homework;
import entities.Student;
import entities.Trainer;
import entities.TrainerCourse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IndividualProjectPartB {

    public static void main(String[] args) {
        // Creating DAO's //
        StudentDao sdao = new StudentDao();
        CourseDao cdao = new CourseDao();
        TrainerDao tdao = new TrainerDao();
        AssignmentDao adao = new AssignmentDao();
        EnrollmentDao edao = new EnrollmentDao();
        HomeworkDao hdao = new HomeworkDao();
        TrainerCourseDao tcdao = new TrainerCourseDao();
        // App Start //
        
        //1. User Validation
        System.out.println("Welcome to MySchool\nPlease enter username and password");
        Login l = new Login();
        Scanner s = new Scanner(System.in);
        String username;
        String password;
        
        int ucounter = 0;
        do {
            if (ucounter > 0) {
                System.out.println("Please enter a valid username");
            }
            System.out.println("USERNAME:");
            username = s.nextLine();
            ucounter++;

        } while (l.isValidUser(username)==false);
        
        int pcounter = 0;
        do{
            if (pcounter > 0) {
                System.out.println("Please enter the correct password");
            }
            System.out.println("PASSWORD:");
            password = s.nextLine();
            pcounter++;
        } while (!l.login(password, username));
        
        // 2. User is validated ! Chooses whether he wants to read or input data
        System.out.println("Welcome "+username);
        s = new Scanner(System.in);
        boolean domore = true;
        while (domore) {
            System.out.println("\nWhat would you like to do?\n1.Print Data\n2.Insert Data");
            
            int choice = getValidMenuChoice(s,1,2);
            switch (choice) {
                case 1: // 3. Reading procedures
                    System.out.println("What would you like to print");
                    System.out.println("1.All Students");
                    System.out.println("2.All Courses");
                    System.out.println("3.All Trainers");
                    System.out.println("4.All Assignments");
                    System.out.println("5.Students per Course");
                    System.out.println("6.Trainers per Course");
                    System.out.println("7.Assignments per Course");
                    System.out.println("8.Assignments per Student per Course");
                    System.out.println("9.Students who attend multiple courses");
                    System.out.println("0.Go back to main menu");
                    int printChoice = getValidMenuChoice(s,0,9);
                    switch (printChoice) {
                        case 0:
                            break;
                        case 1:
                            for (Student st : sdao.getStudents()) {
                                System.out.println(st.getSid() + " " + st.getSfname() + " " + st.getSlname() + " " + st.getSdob() + " " + st.getSfees());
                            }
                            break;
                        case 2:
                            for (Course c : cdao.getCourses()) {
                                System.out.println(c.getCid() + " " + c.getCtitle() + " " + c.getCtype() + " " + c.getCstream() + " " + c.getCsdate() + " - " + c.getCedate());
                            }
                            break;
                        case 3:
                            for (Trainer t : tdao.getTrainers()) {
                                System.out.println(t.getTid() + " " + t.getTfname() + " " + t.getTlname());
                            }
                            break;
                        case 4:
                            for (Assignment a : adao.getAllAssignments()) {
                                System.out.println(a.getAid() + " " + a.getCid().getCtitle() + " " + a.getAtitle() + " " + a.getAdescr() + " " + a.getAdate());
                            }
                            break;
                        case 5:
                            System.out.println("\nWould you like the students of all courses or a specific one ?");
                            System.out.println("1. All of them");
                            System.out.println("2. A specific one\n");
                            int scchoice = getValidMenuChoice(s, 1, 2);
                            switch (scchoice) {
                                case 1:

                                    for (Enrollment e : edao.getEnrollments()) {
                                        System.out.println(e);
                                    }
                                    break;
                                case 2:
                                    choice = getValidCourse(s, getCourseIndexes(cdao.getCourses()), cdao.getCourses());
                                    for (Student st : edao.getStudentsByCourse(choice)) {
                                        System.out.println(st.getSid() + " " + st.getSfname() + " " + st.getSlname());
                                    }
                                    break;
                            }
                            break;
                        case 6:
                            System.out.println("\nWould you like the trainers of all courses or a specific one ?");
                            System.out.println("1. All of them");
                            System.out.println("2. A specific one\n");
                            choice = getValidMenuChoice(s, 1, 2);
                            switch (choice) {
                                case 1:
                                    for (TrainerCourse tc : tcdao.getTrainersByAllCourses()) {
                                        System.out.println(tc);
                                    }
                                    break;
                                case 2:
                                    choice = getValidCourse(s, getCourseIndexes(cdao.getCourses()), cdao.getCourses());
                                    System.out.println("Course " + cdao.getCourseById(choice).getCtitle() + " has the following trainers:");
                                    for (Trainer t : tcdao.getTrainersByCourse(choice)) {
                                        System.out.println(t.getTid() + " " + t.getTfname() + " " + t.getTlname());
                                    }
                                    break;
                            }
                            break;
                        case 7:
                            System.out.println("\nWould you like the assignments of all courses or a specific one ?");
                            System.out.println("1. All of them");
                            System.out.println("2. A specific one\n");
                            choice = getValidMenuChoice(s, 1, 2);
                            switch (choice) {
                                case 1:
                                    for (Assignment a : adao.getAllAssignments()) {
                                        System.out.println(a);
                                    }
                                    break;
                                case 2:
                                    choice = getValidCourse(s, getCourseIndexes(cdao.getCourses()), cdao.getCourses());
                                    System.out.println("Course " + cdao.getCourseById(choice).getCtitle() + " has the following assignments:");
                                    for (Assignment a : adao.getAssignmentsByCourse(choice)) {
                                        System.out.println(a);
                                    }
                            }
                            break;
                        case 8:
                            System.out.println("\nWould you like the assignments of all students of all courses or a specific course/student ?");
                            System.out.println("1. All of them");
                            System.out.println("2. A specific course");
                            System.out.println("3. A specific student\n");
                            choice = getValidMenuChoice(s, 1, 3);
                            switch (choice) {
                                case 1:
                                    for (Homework h : hdao.getAllHomeWork()) {
                                        System.out.println(h);
                                    }
                                    break;
                                case 2:
                                    choice = getValidCourse(s,getCourseIndexes(cdao.getCourses()), cdao.getCourses());
                                    System.out.println("Course " + cdao.getCourseById(choice).getCtitle() + " has the following students-assignments:");
                                    for (Homework h : hdao.getHomeworkByCourse(choice)) {
                                        System.out.println(h);
                                    }
                                    break;
                                case 3:
                                    choice = getValidStudent(s, getStudentIndexes(sdao.getStudents()), sdao.getStudents());
                                    System.out.println("Student " + sdao.getStudentById(choice).getSfname() + " has the following assignments per course:");
                                    for (Homework h : hdao.getHomeworkByStudent(choice)) {
                                        System.out.println(h);
                                    }
                                    break;
                            }
                            break;
                        case 9:
                            System.out.println("\nThese students are the busiest:\n");
                            edao.printBusyStudents();

                            break;
                    }
                    break;
                case 2: // 4. Insert Procedures
                    System.out.println("\nWhat would you like to insert?");
                    System.out.println("1.Student");
                    System.out.println("2.Course");
                    System.out.println("3.Trainer");
                    System.out.println("4.Assignment");
                    System.out.println("5.Student to Course");
                    System.out.println("6.Trainer to Course");
                    System.out.println("7.Assignment to Student");
                    System.out.println("0.Go back to main menu\n");
                    int insertChoice = getValidMenuChoice(s, 0, 7);
                    switch (insertChoice) {
                        case 0:
                            break;
                        case 1:
                            sdao.insertStudent(createStudent(s));
                            break;
                        case 2:
                            cdao.insertCourse(createCourse(s));
                            break;
                        case 3:
                            tdao.insertTrainer(createTrainer(s));
                            break;
                        case 4:
                            adao.insertAssignment(createAssignment(s,getCourseIndexes(cdao.getCourses()),cdao.getCourses()));
                            break;
                        case 5:
                            edao.insertEnrollment(getValidStudent(s,getStudentIndexes(sdao.getStudents()),sdao.getStudents()), getValidCourse(s,getCourseIndexes(cdao.getCourses()),cdao.getCourses()));
                            break;
                        case 6:
                            tcdao.insertTrainerToCourse(getValidCourse(s,getCourseIndexes(cdao.getCourses()),cdao.getCourses()),getValidTrainer(s,getTrainerIndexes(tdao.getTrainers()),tdao.getTrainers()));
                            break;
                        case 7:
                            int ass = getValidAssignment(s,getAssignmentIndexes(adao.getAllAssignments()),adao.getAllAssignments());
                            int marks[] = getValidMarks(s,ass,adao);
                            hdao.insertHomework(getValidStudent(s,getStudentIndexes(sdao.getStudents()),sdao.getStudents()), ass, marks[0],marks[1]);
                            break;
                    
                    }
                    break;
            }
            //5. Continue using the app or not ?
            //if yes ---> step 2
            //if not ---> End Of Program
            System.out.println("\nWould you like to do more stuff or quit?");
            System.out.println("1. Continue with more actions");
            System.out.println("2. Quit\n");
            choice = getValidMenuChoice(s,1,2);
            if (choice == 2) {
                domore = false;
            }
        }
        System.out.println("\n GoodBye !");
        s.close();
    }
    
    public static int getValidMenuChoice(Scanner s,int l,int u){
        int choice;
        do{
            System.out.println("Please choose a valid number");
            while (!s.hasNextInt()){
                System.out.println("Please enter a number");
                s.nextLine();
            }
            choice = s.nextInt();
            s.nextLine();
        }while(!(choice>=l && choice<=u));
        return choice;
    }
    
    public static int getPositiveNumber(Scanner s) {
        int n;
        do {
            System.out.println("Please enter a positive integer");
            while (!s.hasNextInt()) {
                System.out.println("Please enter a positive integer");
                s.nextLine();
            }
            n = s.nextInt();
            s.nextLine();
        } while (n < 0);
        return n;
    }
    
    public static int getValidCourse(Scanner sc,List<Integer> courseIndexes,List<Course> courses) {
        System.out.println("Choose the index of the course you want");
        for (Course c : courses) {
            System.out.println(c);
        }
        int counter = 0;
        int choice;
        do {
            if (counter > 0) {
                System.out.println("Please choose the index of a course in the list");
            }
            choice = getPositiveNumber(sc);
            counter++;
        } while (!courseIndexes.contains(choice));
        return choice;
    }
    
    public static int getValidStudent(Scanner sc,List<Integer> studentIndexes,List<Student> students) {
        System.out.println("Choose the index of the student you want");
        for (Student s:students) {
            System.out.println(s);
        }
        int counter = 0;
        int choice;
        do {
            if (counter > 0) {
                System.out.println("Please choose the index of a student in the list");
            }
            choice = getPositiveNumber(sc);
            counter++;
        } while (!studentIndexes.contains(choice));
        return choice;
    }
    
    public static int getValidTrainer(Scanner sc,List<Integer> trainerIndexes,List<Trainer> trainers) {
        System.out.println("Choose the index of the trainer you want");
        for (Trainer t:trainers) {
            System.out.println(t);
        }
        int counter = 0;
        int choice;
        do {
            if (counter > 0) {
                System.out.println("Please choose the index of a trainer in the list");
            }
            choice = getPositiveNumber(sc);
            counter++;
        } while (!trainerIndexes.contains(choice));
        return choice;
    }
    
    public static int getValidAssignment(Scanner sc,List<Integer> assignmentIndexes,List<Assignment> assignments) {
        System.out.println("Choose the index of the assignment you want");
        for (Assignment a:assignments) {
            System.out.println(a);
        }
        int counter = 0;
        int choice;
        do {
            if (counter > 0) {
                System.out.println("Please choose the index of an assignment in the list");
            }
            choice = getPositiveNumber(sc);
            counter++;
        } while (!assignmentIndexes.contains(choice));
        return choice;
    }
    
    public static List<Integer> getCourseIndexes(List<Course> courses){
        List<Integer> courseIndexes = new ArrayList();
        CourseDao cdao = new CourseDao();
        for (Course c: cdao.getCourses()){
            courseIndexes.add(c.getCid());
        }
        return courseIndexes;
    }
    
    public static List<Integer> getStudentIndexes(List<Student> students){
        List<Integer> studentIndexes = new ArrayList();
        StudentDao sdao = new StudentDao();
        for (Student s : sdao.getStudents()){
            studentIndexes.add(s.getSid());
        }
        return studentIndexes;
    }
    
    public static List<Integer> getTrainerIndexes(List<Trainer> trainers){
        List<Integer> trainerIndexes = new ArrayList();
        TrainerDao tdao = new TrainerDao();
        for (Trainer t:tdao.getTrainers()){
            trainerIndexes.add(t.getTid());
        }
        return trainerIndexes;
    }
    
    public static List<Integer> getAssignmentIndexes(List<Assignment> assignments){
        List<Integer> assignmentIndexes = new ArrayList();
        AssignmentDao adao = new AssignmentDao();
        for (Assignment a : adao.getAllAssignments()){
            assignmentIndexes.add(a.getAid());
        }
        return assignmentIndexes;
    }
    
    
    
    public static String isAlpha(Scanner s) {
        String name = null;
        boolean flag = true;
        while (flag){
            name = s.nextLine().trim();
            char[] chars = name.toCharArray();
            boolean foundError = false;
            for (char c : chars) {
                if (!Character.isLetter(c)) {
                    System.out.println("Sorry, can't contain numbers,symbols or spaces.Try again:");
                    foundError = true;
                    break;
                }
            }
            if (!foundError){
                flag=false;
            }
        }
        return name;
    }
    
    public static boolean isNumeric(String str) { 
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static LocalDate getValidDate(Scanner s){
        String day;
        String month;
        String year;
        String CorrectDate;
        LocalDate dateFromString = null;
        boolean inputIsValid=false;
        while (inputIsValid==false){
            System.out.println("Please enter the date in the following form: dd/M/yyyy\ndd<=31\tM<=12\tyyyy<2100");
            String input = s.nextLine();
            String [] inputArray = input.split("/");
            for (int i = 0 ; i< inputArray.length ;i++){
                inputArray[i]=inputArray[i].trim();
            }
            if (( inputArray.length==3 ) && ( isNumeric(inputArray[0])) && ( isNumeric(inputArray[1])) && ( isNumeric(inputArray[2]))){
                List<Integer> inputNumbers = new ArrayList();
                inputNumbers.add(0, Integer.parseInt(inputArray[0]));
                inputNumbers.add(1, Integer.parseInt(inputArray[1]));
                inputNumbers.add(2, Integer.parseInt(inputArray[2]));
                if (inputNumbers.get(0)>=1 && inputNumbers.get(0)<=31 && inputNumbers.get(1)>=1 && inputNumbers.get(1)<=12 && inputNumbers.get(2)>=1950 && inputNumbers.get(2)<=2100){
                    if (inputNumbers.get(0)<10){
                        day="0"+inputNumbers.get(0);
                    }else{
                        day=""+inputNumbers.get(0);
                    }
                    month = ""+inputNumbers.get(1);
                    year = ""+inputNumbers.get(2);
                    inputIsValid = true;
                    CorrectDate=day+"/"+month+"/"+year;
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
                    dateFromString = LocalDate.parse(CorrectDate, formatter);
                }
            }else{
                inputIsValid=false;    
            }
        }  
    return dateFromString;
    }
  
    public static Student createStudent(Scanner s) {
        Student st = new Student();
        System.out.println("Give the first name of student");
        st.setSfname(isAlpha(s));

        System.out.println("Give the last name of student");
        st.setSlname(isAlpha(s));

        System.out.println("Date of birth of student ?");
        st.setSdob(getValidDate(s));

        System.out.println("Insert the tuition fees ($) of student");
        st.setSfees(getPositiveNumber(s));
        
        return st;
    }
   
    public static Course createCourse(Scanner s) {
        Course c = new Course();

        System.out.println("Name course");
        String name = s.nextLine();
        c.setCtitle(name);

        System.out.println("Stream of course");
        String stream = isAlpha(s);
        c.setCstream(stream);

        System.out.println("Type of course");
        String type = isAlpha(s);
        c.setCtype(type);

        System.out.println("Start Date of course ?");
        c.setCsdate(getValidDate(s));

        System.out.println("End Date of course ?");
        c.setCedate(getValidDate(s));

        while ((c.getCedate().compareTo(c.getCsdate())) <= 0) {
            System.out.println("Sorry, can't set end-date earlier than start-date");
            System.out.println("Set end-date again");
            c.setCedate(getValidDate(s));
        }
        return c;
    }

    public static Trainer createTrainer(Scanner s) {
        Trainer t = new Trainer();

        System.out.println("Type the first name of the teacher");
        t.setTfname(isAlpha(s));
        System.out.println("Type the last name of the teacher");
        t.setTlname(isAlpha(s));
        
        return t;
    }
    
    public static Assignment createAssignment(Scanner s,List<Integer> courseIndexes,List<Course> courses) {
        Assignment a = new Assignment();

        System.out.println("Insert the title of the assignmen");
        a.setAtitle(s.nextLine());
        System.out.println("Insert the description of the assignment");
        a.setAdescr(s.nextLine());
      
        System.out.println("What's the oral mark of assignment ?");
        a.setAomark(getPositiveNumber(s));

        System.out.println("What's the total mark of assignment ?");
        a.setAtmark(getPositiveNumber(s));
      
        System.out.println("Which course does the assignment belong to ?");
        int choice = getValidCourse(s, courseIndexes, courses);
        CourseDao cdao = new CourseDao();
        a.setCid(cdao.getCourseById(choice));

        System.out.println("Submission Date of assignment ?");
        int dateTries = 0;
        do {
            if (dateTries>0){
                System.out.println("Sorry, can't set submission date of the assignment out of the range of course start-end dates\n\nInsert a valis submission date"); 
            }
            a.setAdate(getValidDate(s));
        } while (!(a.getAdate().isAfter(cdao.getCourseById(choice).getCsdate())) && (a.getAdate().isBefore(cdao.getCourseById(choice).getCedate())));
        return a;
    }
    
    public static int[] getValidMarks(Scanner s,int ass,AssignmentDao adao){
        int marks[] = new int [2];
        System.out.println("What's the oral mark of the student?");
        int counter = 0;
        int mark;
        do {
            if (counter > 0) {
                System.out.println("Please insert a positive number lower than the max oral mark of the assignment: " + adao.getAssignmentById(ass).getAtitle() + ", which is: " + adao.getAssignmentById(ass).getAomark());
            }
            mark = getPositiveNumber(s);
        } while (mark > adao.getAssignmentById(ass).getAomark());
        marks[0]=mark;

        System.out.println("What's the total mark of the student?");
        int counter2 = 0;
        int mark2;
        do {
            if (counter2 > 0) {
                System.out.println("Please insert a positive number lower than the max total mark of the assignment: " + adao.getAssignmentById(ass).getAtitle() + ", which is: " + adao.getAssignmentById(ass).getAtmark());
            }
            mark2 = getPositiveNumber(s);
        } while (mark > adao.getAssignmentById(ass).getAtmark());
        marks[1]=mark2;
        
        return marks;
    }
}
