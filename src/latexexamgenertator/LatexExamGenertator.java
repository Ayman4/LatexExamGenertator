/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package latexexamgenertator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author ASUS
 */
public class LatexExamGenertator {

    /**
     * @param args the command line arguments
     */
    public static boolean IsDatabase=false;
    public static String Examiner="";
    public static String ExamTime="";
    public static String ExamTitle="";
    public static String ExamSubject="";
    public static String ExamFolderPath="";
    public static String ExamFileName="";
    public static int NoumberOfModels=0;
    public static String ImageLogo="";
    public static String FacultyName="";
    public static String ExamNotes="";
    public static void Config() throws FileNotFoundException, IOException
    {
        
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        fc.setDialogTitle("Please select Configration File");
        BufferedReader bf;
        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
        bf=new BufferedReader(new FileReader(fc.getSelectedFile()));
        }
        else
        {
         bf=new BufferedReader(new FileReader("Configration.txt"));
        }
        //
        String []Line=bf.readLine().split("=");
        if (Line[1].equals("false"))
            IsDatabase=false;
        else
            IsDatabase=true;
        Line=bf.readLine().split("=");
        Examiner=Line[1];
        Line=bf.readLine().split("=");
        ExamTime=Line[1];
        Line=bf.readLine().split("=");
        ExamTitle=Line[1];
        Line=bf.readLine().split("=");
        ExamSubject=Line[1];
        Line=bf.readLine().split("=");
        ExamFolderPath=Line[1];
        Line=bf.readLine().split("=");
        ExamFileName=Line[1];
        Line=bf.readLine().split("=");
        NoumberOfModels=Integer.parseInt(Line[1]);
        Line=bf.readLine().split("=");
        ImageLogo=Line[1];
        Line=bf.readLine().split("=");
        FacultyName=Line[1];
        Line=bf.readLine().split("=");
        ExamNotes=Line[1];
        
        
        
    }
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        // TODO code application logic here
        
        
        Config();
        if (IsDatabase)
        {DBConnect db=new DBConnect();}
        
        QuestionAnswerForm qf=new QuestionAnswerForm();
        qf.setVisible(true);
        qf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
}
