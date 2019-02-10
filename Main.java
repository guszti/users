import java.util.*;
import java.io.*;

class Main{

  static File users = new File("users.txt");

  static Boolean log_in() throws IOException{

    Scanner scan = new Scanner(users);
    Scanner uscan = new Scanner(System.in);
    Boolean state = false;

    System.out.print("username: ");
    String name = uscan.next();
    System.out.print("password: ");
    String pw = uscan.next();

    try{
      while(scan.hasNextLine()){
        String[] line = scan.nextLine().split(";");
        if(line[0].equals(name) && line[1].equals(pw)){
          session(name);
          state = true;
        }
      }
    }catch(Exception ex){
      System.out.println("Error writing!");
      System.out.println(ex.getMessage());
    }finally{
      scan.close();
    }
    return state;

  }

  static Boolean session(String name){

    Scanner scan = new Scanner(System.in);

    System.out.println();
    System.out.println("Session started for " + name);
    System.out.println(".\n.\n.");
    scan.next();

    return true;

  }

  static Boolean check(String name) throws IOException{

    Scanner scan = new Scanner(users);
    Boolean state = false;

    try{
      while(scan.hasNextLine()){
        if(scan.nextLine().split(";")[0].equals(name)){
          state = true;
        }
      }
    }catch(Exception ex){
      System.out.println("Error writing!");
      System.out.println(ex.getMessage());
    }finally{
      scan.close();
    }
    return state;

  }

  static Boolean register() throws IOException{

    FileWriter fw = new FileWriter(users, true);
    Scanner scan = new Scanner(System.in);

    System.out.print("username: ");
    String name = scan.next();
    if(!check(name)){
      System.out.print("password: ");
      String pw = scan.next();
      try{
        fw.write(name + ";" + pw);
        return true;
      }catch(Exception ex){
        System.out.println("Error writing!");
        System.out.println(ex.getMessage());
      }finally{
        fw.close();
      }
    }

    return false;

  }

  public static void main(String[] args) throws IOException{

    Scanner scan = new Scanner(System.in);
    Boolean running = true;

    while(running){
      System.out.println();
      System.out.println("1: log in | 2: register | e: exit");
      String option = scan.next();

      switch(option){
        case("e"):
          running = false;
          break;
        case("1"):
          if(!log_in()){
            System.out.println();
            System.out.println("wrong username or password");
          }
          break;
        case("2"):
          if(!register()){
            System.out.println();
            System.out.println("username already in use");
          }
          break;
        default:
          System.out.println();
          System.out.print("wrong command");
      }
    }

  }
}
