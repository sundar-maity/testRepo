package WEB-INF.classes.edu.uga.csci4050.group3.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import javax.servlet.ServletContext;

public class Settings implements Serializable {
  private static final String FILENAME = "CSCI4050-Group3-Settings.obj";
  
  private static final String PATH = "/tmp/";
  
  private static final long serialVersionUID = 1L;
  
  private ServletContext context;
  
  private static edu.uga.csci4050.group3.db.Settings singleton;
  
  private double membershipFee;
  
  public void loadDefaults() {
    this.membershipFee = 10.0D;
  }
  
  public void setMembershipFee(double fee) {
    this.membershipFee = fee;
  }
  
  public double getMembershipFee() {
    return this.membershipFee;
  }
  
  public static edu.uga.csci4050.group3.db.Settings loadFromStorage(ServletContext context) {
    try {
      if ((new File("/tmp/CSCI4050-Group3-Settings.obj")).isFile()) {
        try {
          FileInputStream istream = new FileInputStream(new File("/tmp/CSCI4050-Group3-Settings.obj"));
          ObjectInputStream oistream = new ObjectInputStream(istream);
          singleton = (edu.uga.csci4050.group3.db.Settings)oistream.readObject();
          oistream.close();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        } 
        return singleton;
      } 
      return create(context);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  private static edu.uga.csci4050.group3.db.Settings create(ServletContext context) {
    singleton = new edu.uga.csci4050.group3.db.Settings();
    singleton.loadDefaults();
    try {
      FileOutputStream fostream = new FileOutputStream(new File("/tmp/CSCI4050-Group3-Settings.obj"));
      ObjectOutputStream ostream = new ObjectOutputStream(fostream);
      ostream.writeObject(singleton);
      ostream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return singleton;
  }
  
  public static void saveToStorage(edu.uga.csci4050.group3.db.Settings settings, ServletContext context) throws Exception {
    if (settings == null)
      throw new Exception("Invalid settings object"); 
    try {
      FileOutputStream fostream = new FileOutputStream(new File("/tmp/CSCI4050-Group3-Settings.obj"));
      ObjectOutputStream ostream = new ObjectOutputStream(fostream);
      ostream.writeObject(settings);
      ostream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static edu.uga.csci4050.group3.db.Settings getSingleton() {
    return singleton;
  }
}
