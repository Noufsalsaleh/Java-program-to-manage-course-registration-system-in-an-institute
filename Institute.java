import java.util.Scanner;
public class Institute{
private static Course[] Courses=new Course[20] ;
private static Scanner input = new Scanner(System.in );

public static void main(String[] args) {
int num;
addCourse( "IT01" , "135-11-12" , "12/02/21" ,  3000  ) ; 
registerCourse( "IT01" , "Ali" ) ; 
registerCourse( "IT01" , "Sara" ) ; 

addCourse( "CS01" , "135-08-09" , "13/02/21" ,  2000  ) ; 
registerCourse( "CS01" , "Sara" ) ; 

addCourse( "IS01", "135-09-10" ,  "11/02/21" , 1000  ); 
registerCourse( "IS01" , "Sara" ) ; 
registerCourse( "IS01" , "Fay" ) ; 
addCourse( "SW01", "234-08-09" ,  "14/02/21" , 2000  );         
registerCourse( "SW01" , "Ali" ) ;

addCourse( "IT20", "135-12-01" ,  "20/02/21" , 1000  );         


System.out.println("**** Welcome to Institute Course Registration System **** "); 
 
System.out.println("---------------------------------------");
System.out.println("Choose one of these actions: ");
System.out.println("(1) Add a new course ");
System.out.println("(2) Register a course for a trainee, given the trainee name and course code ");
System.out.println("(3) Cancel Course Registration ");
System.out.println("(4) Find and Display the information of a course, given the course code");
System.out.println("(5) List All courses ");
System.out.println("(6) Find and Display list of registered courses for a given trainee ");
System.out.println("(7) Compute and Display the total tuition for a given trainee ");
System.out.println("(8) Display total number of added courses");
System.out.println("(9) Exit");

num=input.nextInt();


while (num!=9)
{
if (num==1){
System.out.println("Enter the course information: ");
System.out.print(" Course Code in format LLDD, L: letter, Dd: igit: ");
String code = input.next(); 
System.out.print("weeklyTimeSlots in format D-S-E: ");//135-08-09
String weeklyTime = input.next();
//System.out.println(); 

System.out.print("finalExamDate in format : DD/MM/YY: ");// 09/01/22
String finalExamDate = input.next();
 
System.out.print("Tuition: ");
double tuition = input.nextDouble();
 System.out.println();//+from nouf
 

if( addCourse( code , weeklyTime , finalExamDate , tuition ) ) 
System.out.println("The course is added successfully.");
else
System.out.println("Failed adding course");
}//end (num==1)

else 

if(num==2){
System.out.println("please enter course code: ");
String code = input.next();
 
System.out.println("Please enter trainee name:");
String traineeName = input.next(); 

if( registerCourse( code , traineeName ) )
System.out.println("Course is registered successfully.");
else
System.out.println("Course is not registered");
}//end (num==2)

else 
if(num==3){
System.out.println("please enter course code: ");
String courseCode = input.next(); 

System.out.println("Please enter trainee name:");
String traineeName = input.next(); 

if( cancelCourseRegisteration( courseCode , traineeName ))
System.out.println("Course registeration is canceled successfully.");
else
System.out.println("Failed canceling this registeration");
}//end (num==3)

else 
if(num ==4){
System.out.println("please enter course code: ");
String code = input.next(); 

int findIndex=findCourse(code); 
if( findIndex == -1 )
System.out.println("This course cannot be found");
else
Courses[findIndex].DisplayCourseInfo();
}

else 
if (num ==5)
printAll(); 

else
 
if(num==6){
System.out.println("Please enter trainee name:");
String traineeName = input.next(); 

Course[] list= findRegisteredCourses(traineeName) ; 
if( list.length != 0 ){
for( int i = 0 ; i < list.length ; i++)
System.out.println(list[i].getCourseCode());
}
else
System.out.println(" No courses with the name you entered");

}//end (num==6)

else
 if (num==7){
 System.out.println("Please enter trainee name:");
String traineeName = input.next(); 
double total = 0 ; 
Course[] Ttuition = findRegisteredCourses(traineeName) ; 
if( Ttuition.length == 0 )
System.out.println("no courses found for you" );
else
for( int i = 0 ; i < Ttuition.length ; i++)
total=total + Ttuition[i].getTuition() ; 

System.out.printf("The total tuition is %.1f" , total ); 
System.out.println();
}//end (num==7)

else 
if(num==8)
System.out.println("Total number Of courses is: " +  Course.totalCourses );
//end (num==8)

System.out.println("---------------------------------------");
System.out.println("Choose one of these actions: ");
System.out.println("(1) Add a new course ");
System.out.println("(2) Register a course for a trainee, given the trainee name and course code ");
System.out.println("(3) Cancel Course Registration ");
System.out.println("(4) Find and Display the information of a course, given the course code");
System.out.println("(5) List All courses ");
System.out.println("(6) Find and Display list of registered courses for a given trainee ");
System.out.println("(7) Compute and Display the total tuition for a given trainee ");
System.out.println("(8) Display total number of added courses");
System.out.println("(9) Exit");

num=input.nextInt();


}//end while
if(num==9)
System.out.println("Thank you");

}//end main

//..................................................................................


public static boolean addCourse(String courseCode, String weeklyTimeSlots,String finalExamDate, double tuition){
if( Course.totalCourses == 20 )
{
System.out.println("list is full cannot add more courses"); 
return false;
}
int course1 = findCourse(courseCode) ;
if( course1 == -1 )
{
Course newCourse = new Course( courseCode ,weeklyTimeSlots, finalExamDate, tuition ); 
Courses[Course.totalCourses] = newCourse ; 
Course.totalCourses++; 
return true;
}
System.out.println("this course has already been entered"); 
return false ;
}//end method addCourse


public static boolean registerCourse(String courseCode, String traineeName){
Course[] RegisteredCourses =findRegisteredCourses( traineeName );
int Rcourse = findCourse( courseCode) ;
if( Rcourse == -1 ) { 
return false;
}

if( Courses[Rcourse].numTrainees == 3 ){
//System.out.println("capacity is full");
return false;
}
else
 
  if( RegisteredCourses.length == 3 ){ 
 //System.out.println("Can't add more, already had 3 courses"); 
 return false;
 }
 //else
for( int i = 0 ; i < RegisteredCourses.length; i++)
{
if( RegisteredCourses[i]!= null){
String FinalDate= Courses[Rcourse].getFinalExamDate();
String regsteredFinalDate=RegisteredCourses[i].getFinalExamDate();

if( FinalDate.equals(regsteredFinalDate) ){ 
 System.out.println("conflict dates of final exam"); 
return false; 
}

String daysInCourse, dayInRegisteredCourses, timeInCourse, timeInRegisteredCourses; 

daysInCourse=Courses[Rcourse].getWeeklyTimeSlots().substring(0,3);
dayInRegisteredCourses=RegisteredCourses[i].getWeeklyTimeSlots().substring(0,3);
timeInCourse=Courses[Rcourse].getWeeklyTimeSlots().substring(4);
timeInRegisteredCourses=RegisteredCourses[i].getWeeklyTimeSlots().substring(4);

if(daysInCourse.equals(dayInRegisteredCourses)&&timeInCourse.equals(timeInRegisteredCourses))
{
System.out.println("conflict in WeeklyTimeSlots");
return false;
 }
 }
    }//end for

String[] names = Courses[Rcourse].getTraineeNames();//20 
names[Courses[Rcourse].numTrainees] = traineeName ; //3
//Courses[Rcourse].numTrainees++;
 Courses[Rcourse].setTraineeNames(names);
return true ; 
}//end method registerCourse


public static boolean cancelCourseRegisteration(String courseCode, String TraineeName){
int course = findCourse( courseCode) ; 
if( course == -1 ){ 
System.out.println("cannot found code");
return false;
}

String[] names = Courses[course].getTraineeNames(); 
for(int i = 0 ; i < Courses[course].numTrainees ; i++ ) 
if( names[i].equals(TraineeName))
{ 
for( int j = i ; j < names.length - 1 ; j++) //shifting
names[j] = names[j+1]; 
Courses[course].numTrainees--;
names[Courses[course].numTrainees] = null; 
Courses[course].setTraineeNames(names); 
return true;
 } 
return false;
 }//end method cancelCourseRegisteration

 
public static int findCourse(String courseCode){
for( int i = 0 ;i < Course.totalCourses ; i++ ) 
if( Courses[i].getCourseCode().equals(courseCode)) 
return i ; 
return -1;
}//end method findCourse


public static Course[] findRegisteredCourses(String traineeName){
 //Course[] registerCourse= new Course[Course.totalCourses] ; 
 int Tname = 0; 
 for( int i = 0 ;i < Course.totalCourses ; i++ ) 
 for( int j = 0 ; j < Courses[i].numTrainees ; j++) 
 if(Courses[i].getTraineeNames()[j].equals(traineeName)){
 Tname++;}
 Course[] registerCourse= new Course[Tname] ; 
int k=0;
 for( int i = 0 ;i < Course.totalCourses ; i++ ) 
 for( int j = 0 ; j < Courses[i].numTrainees ; j++) 
 if(Courses[i].getTraineeNames()[j].equals(traineeName) ) 
 registerCourse[k++] = Courses[i] ;
 return registerCourse;
 
} //end method findRegisteredCourses

public static void printAll(){
for( int i = 0 ;i < Course.totalCourses ; i++ ) 
System.out.println(Courses[i].getCourseCode() );
}//end method printAll

}//end class

