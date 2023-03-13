/**
 * This is a simple console program that takes input from the
 * user and creates a VCF card
 *
 * @author Joan Shameti
 * @verion 1.0
 * @since 2023-03-13
 */


import java.time.LocalDate;
import java.util.Scanner;
import java.io.*;
public class vcfeditor {

    //Declare class variables
    public static String first_name;
    public static String last_name;
    public static LocalDate birthday;
    public static String org;
    public static String title;
    public static String phone_number;
    public static String e_mail;
    public static String note;
    public static String website;

    public static void main(String[] args){
        //Initialize the website and note variable because they are optional
        website = ""; note = "";
        system();
        writeToVCF();
    }


    public static void system(){

        //Create scanner object to read user input
        Scanner console = new Scanner(System.in);

        //Prompt user to enter information on their VCF Card
        System.out.println("Pleas enter your first name:");
        first_name = console.next();
        System.out.println("Pleas enter your last name:");
        last_name = console.next();
        System.out.println("Plese enter your birthday (YYYY-MM-DD) ");
        System.out.print("Year: ");
        int year = console.nextInt();
        System.out.print("Month: ");
        int month = console.nextInt();
        System.out.print("Day: ");
        int day = console.nextInt();
        birthday = LocalDate.of(year,month,day);
        System.out.println(birthday);
        System.out.println("What is your organization:");
        org = console.next();
        console.nextLine();
        System.out.println("What is your title:");
        title = console.next();
        System.out.println("What is your phone number:");
        phone_number = console.next();
        System.out.println("What is your email: ");
        e_mail = console.next();
        System.out.println("Do you want to add a note? Y/N");
        String Answer = console.next();
        if(checkAnswer(Answer)){
            System.out.println("What do you want to say:");
            note = console.next();
        }
        console.nextLine();
        System.out.println("Do you want to add a website? Y/N");
        Answer = console.next();

        if (checkAnswer(Answer)){
            System.out.println("What is your website url: ");
            website = console.next();
        }
    }

    public static boolean checkAnswer(String answer){
        if (answer.equalsIgnoreCase("Y")){
            return true;
        } else {
            return false;
        }
    }

    public static void writeToVCF(){
        try{
            //Create a new VCF file with the user first name and first char of last name
            File vcffile = new File(first_name+last_name.charAt(0)+".vcf");
            FileWriter filewriter = new FileWriter(vcffile);
            String birthdayInt = birthday.toString();
            //Write the heater and data to the file
            filewriter.write("##fileformat=VCFv4.3\n");
            filewriter.write("#CHROM\tPOS\tID\tREF\tALT\tQUAL\tFILTER\tINFO\n");
            filewriter.write("BEGIN:VCARD\n" +
                    "VERSION:3.0\n" +
                    "REV:2023-03-12T00:00:00Z\n" +
                    "N:"+last_name+";"+first_name+";;;\n" +
                    "BDAY:"+birthdayInt+"\n" +
                    "ORG:"+org+"\n" +
                    "TITLE:"+title+"\n" +
                    "NOTE:"+note +"\n"+
                    "TEL;PREF:"+phone_number+"\n" +
                    "EMAIL;INTERNET;MAIN:"+e_mail+"\n" +
                    "URL:"+website+"\n" +
                    "END:VCARD");
            filewriter.close();
            System.out.println("Card created Successfully");


        }
        catch(IOException e){
            System.out.println("Error creating your cardx");
            e.printStackTrace();
        }
    }
}
