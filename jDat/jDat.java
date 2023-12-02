package jDat;
import java.io.*;
import java.util.Scanner;

public class jDat {

  private BufferedWriter writer;
  public File f;
  public String name = "";
  public int length = 0;

  // Constructor
  public jDat(String filename, Boolean getLength) {
    name = filename;
    if(!Init(getLength)){
      System.out.println("Error Initializing File");
    }
  }

  // Init method, setup file location and get length ideally
  public boolean Init(boolean getLength) {
    try {
      f = new File(name);
      if (!f.exists()) {
        f.createNewFile();
      }
    } catch (Exception e) {
      System.out.println(e.getClass());
      return false;
    }
    if (getLength) {
      length = getLength();
    }
    return true;
  }

  // Get the file's size
  public int getLength() {
    try {
      Scanner fs = new Scanner(f);
      int count = 0;
      while (fs.hasNextLine()) {
        count++;
        fs.nextLine();
      }
      fs.close();
      length = count;
      return count;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return 0;
  }

  // Read Methods
  // GetInt
  public Integer getInt(String name) {
    try {
      Scanner fs = new Scanner(f);
      String temp = name + ":";
      Integer out = null;
      while (fs.hasNext()) {
        if (fs.next().contains(temp)) {
          out = fs.nextInt();
          fs.close();
          return out;
        }
      }
      fs.close();
      return out;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  // GetString
  public String getString(String name) {
    try {
      Scanner fs = new Scanner(f);
      String temp = name + ":";
      String out = null;
      while (fs.hasNext()) {
        if (fs.next().contains(temp)) {
          out = fs.next();
          fs.close();
          return out;
        }
      }
      fs.close();
      return out;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  // GetBool
  public Boolean getBool(String name) {
    try {
      Scanner fs = new Scanner(f);
      String temp = name + ":";
      Boolean out = null;
      while (fs.hasNext()) {
        if (fs.next().contains(temp)) {
          out = fs.nextBoolean();
          fs.close();
          return out;
        }
      }
      fs.close();
      return out;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  // Write Methods
  // Write Int
  public void setVal(String name, int value) {
    String pN = name + ": ";
    String temp = value + "";
    Integer currentVal = null;
    try {
      FileWriter fw = new FileWriter(f, true);
      writer = new BufferedWriter(fw);
      Scanner fs = new Scanner(f);
      String t = name + ":";
      // Check if variable already exists
      while (fs.hasNext()) {
        if (fs.next().contains(t)) {
          currentVal = fs.nextInt();
          fs.close();
          break;
        }
      }
      fs = new Scanner(f);
      fs.close();
      // If value already exist replace, otherwise create a new slot
      if(currentVal != null){
        String ln = t + " " + currentVal;
        String nln = t + " " + value;
        // Replace current val
        File tempFile = File.createTempFile("buffer", ".tmp");
        FileWriter tempFw = new FileWriter(tempFile);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        while(br.ready()) {
            tempFw.write(br.readLine().replaceAll(ln, nln) + "\n");
        }
        // Close readers and writers
        tempFw.close();
        br.close();
        fr.close();
        // Replace file
        tempFile.renameTo(f);
      } else {
        // Write new Value
        writer.write(pN);
        writer.write(temp);
        writer.newLine();
      }  
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (writer != null)
        try {
          writer.close();
        } catch (IOException ignore) {
        }
    }

  }

  // Write String
    public void setVal(String name, String value) {
    String pN = name + ": ";
    String temp = value + "";
    String currentVal = null;
    try {
      FileWriter fw = new FileWriter(f, true);
      writer = new BufferedWriter(fw);
      Scanner fs = new Scanner(f);
      String t = name + ":";
      // Check if variable already exists
      while (fs.hasNext()) {
        if (fs.next().contains(t)) {
          currentVal = fs.next();
          fs.close();
          break;
        }
      }
      fs = new Scanner(f);
      fs.close();
      // If value already exist replace, otherwise create a new slot
      if(currentVal != null){
        String ln = t + " " + currentVal;
        String nln = t + " " + value;
        // Replace current val
        File tempFile = File.createTempFile("buff er", ".tmp");
        FileWriter tempFw = new FileWriter(tempFile);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        while(br.ready()) {
            tempFw.write(br.readLine().replaceAll(ln, nln) + "\n");
        }
        // Close readers and writers
        tempFw.close();
        br.close();
        fr.close();
        // Replace file
        tempFile.renameTo(f);
      } else {
        // Write new Value
        writer.write(pN);
        writer.write(temp);
        writer.newLine();
      }  
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (writer != null)
        try {
          writer.close();
        } catch (IOException ignore) {
        }
    }

  }

  // Write boolean
    public void setVal(String name, boolean value) {
    String pN = name + ": ";
    try {
      FileWriter fw = new FileWriter(f, true);
      writer = new BufferedWriter(fw);
      Scanner fs = new Scanner(f);
      String t = name + ":";
      Boolean currentVal = null;
      // Check if variable already exists
      while (fs.hasNext()) {
        if (fs.next().contains(t)) {
          if(fs.hasNextBoolean()){
            currentVal = fs.nextBoolean();
          }
          fs.close();
          break;
        }
      }
      fs = new Scanner(f);
      fs.close();
      // If value already exist replace, otherwise create a new slot
      if(currentVal != null){
        String ln = t + " " + currentVal;
        String nln = t + " " + value;
        // Replace current val
        File tempFile = File.createTempFile("buffer", ".tmp");
        FileWriter tempFw = new FileWriter(tempFile);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        while(br.ready()) {
            tempFw.write(br.readLine().replaceAll(ln, nln) + "\n");
        }
        // Close readers and writers
        tempFw.close();
        br.close();
        fr.close();
        // Replace file
        tempFile.renameTo(f);
      } else {
        // Write new Value
        writer.write(pN);
        writer.write("" + value);
        writer.newLine();
      }  
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (writer != null)
        try {
          writer.close();
        } catch (IOException ignore) {
        }
    }

  }

  // Clear File
  public void clear() {
    try {
      FileWriter fw = new FileWriter(f);
      fw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}