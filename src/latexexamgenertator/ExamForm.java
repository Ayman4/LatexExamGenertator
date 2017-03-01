/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package latexexamgenertator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ExamForm extends Article{
    public static char ModelName='A';
    public static ArrayList<Question> AllQuestions=new ArrayList<Question>(100);
    public static Question SearchQuestions(String QHeader)
    {
        Question r=null;
    for (Question q:ExamForm.AllQuestions)
        {
            
            //if (QHeader.equals(q.Desc))
             System.out.println("Q"+q.Desc.indexOf(QHeader));
            if (q.Desc.indexOf(QHeader)>=0)
           {
                return q;
            }
        }
    return r;
    }
            
    public static int LoadData() throws FileNotFoundException, IOException, ClassNotFoundException
    {
    AllQuestions.clear();
    if (LatexExamGenertator.IsDatabase)
    {
    String sql="select * from questionanswer where parentId=0";
        try {
            ResultSet rs=DBConnect.ExecuteMyquery(sql);
            //Loop to get all Questions
            while (rs.next())
            {
                Question q=new Question(rs.getInt("Id"));
                q.Desc=rs.getString("Description");
                AllQuestions.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    else
    {
    try
    {
    ObjectInputStream Inp1=new ObjectInputStream(new FileInputStream(LatexExamGenertator.ExamFolderPath+""+LatexExamGenertator.ExamFileName+".bin"));
    AllQuestions=(ArrayList<Question>)Inp1.readObject();
    }
    catch (FileNotFoundException ex)
    {
        new File(LatexExamGenertator.ExamFolderPath).mkdirs();   
        System.out.println("File Was not Found");
        ObjectOutputStream Bin=new ObjectOutputStream(new FileOutputStream(LatexExamGenertator.ExamFolderPath+""+LatexExamGenertator.ExamFileName+".bin",false));
        Bin.writeObject(AllQuestions);
        Bin.close();
    }
    
    
    }
    return AllQuestions.size();
    }
    public static void SaveBinary() throws FileNotFoundException, IOException
    {
   
        ObjectOutputStream Bin=new ObjectOutputStream(new FileOutputStream(LatexExamGenertator.ExamFolderPath+""+LatexExamGenertator.ExamFileName+".bin",false));
        Bin.writeObject(AllQuestions);
        Bin.close();
    }
    public ExamForm() throws IOException, FileNotFoundException, ClassNotFoundException
    {
            LoadData();
    }
}
