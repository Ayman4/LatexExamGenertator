/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package latexexamgenertator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Question extends Article{
    public ArrayList<Answer> ArrayListAnswers=new ArrayList<Answer>(5);
//    public static boolean SpecialSymbol=true;
    Question() {
        
    }
    public void delQuestion() throws SQLException
    {
            String sql="DELETE FROM `latexexamjava`.`questionanswer` WHERE `questionanswer`.`ParentID` ="+this.id+" \"";
            System.out.println("Del Childs : " + sql);
            DBConnect.ExecuteInsertquery(sql);    
            sql="DELETE FROM `latexexamjava`.`questionanswer` WHERE `questionanswer`.`id` ="+this.id+" \"";
            System.out.println("Del Q : " + sql);
            DBConnect.ExecuteInsertquery(sql);    
    }
    public int InserQuestion () 
    {
        int id=0;
        if (LatexExamGenertator.IsDatabase)
        {
        try {
            
            ResultSet rs1 = DBConnect.ExecuteMyquery("SELECT * FROM questionanswer where Description like '"+Desc+"' and ParentID=0");
            if (rs1.next())
            {
                return 0;
            }
            String sql="INSERT INTO `latexexamjava`.`questionanswer` (`id`, `Description`, `ParentID`, `IsCorrect`) VALUES (NULL, '"+Desc+"', '0', '0');";
            System.out.println("Q : " + sql);
            DBConnect.ExecuteInsertquery(sql);
            ResultSet rs = DBConnect.ExecuteMyquery("SELECT MAX(id) AS id FROM questionanswer");
            rs.next();
            id = rs.getInt("id");
     
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        }
        else
        {
            if (ExamForm.AllQuestions.contains(this) )
            {
                return 0;
            }
            else
            {
                try
                {
                    if (ExamForm.AllQuestions.size()>0)
                    {
                        this.id=ExamForm.AllQuestions.get(ExamForm.AllQuestions.size()-1).id+1;
                        ExamForm.AllQuestions.add(this);
                    }
                    else
                    {
                        //First Element
                        this.id=1;
                        ExamForm.AllQuestions.add(this);
                    }
                }
                catch (Exception ex)
                {
                    System.out.println("My Ex" +ex);
                }
                return this.id;
                
            }
        }
        
        return id;
    }
    public String toString()
    {
        //String r="QID "+this.id+" "+ this.Desc+"\n";
        String r="\n \\question "+this.Desc+"\n\n \\begin{oneparchoices} \n";
        long seed = System.nanoTime();
        Collections.shuffle(ArrayListAnswers, new Random(seed));
//        int i=0;
        for (Answer a:ArrayListAnswers)
        {
            //r+="ID "+a.id+" "+ a.Desc +"\n";
//            if (SpecialSymbol)
//            {
//            a.Desc=a.Desc.replace("(","\\\\(");
//            a.Desc=a.Desc.replace(")","\\\\)");
//            
//            }
            r+="\\choice " +a.Desc+"\n ";
//            if (i==2)
//            {
//            r+="\\\\";
//            }
//            i++;
        }
        r+="\\end{oneparchoices}\n";
        //r+="\\noindent\\makebox[\\linewidth]{\\rule{\\paperwidth}{0.1pt}}";
        
        return r;
    }
    public Answer GetCorrectAnswer()
    {
        Answer a=new Answer();
        for (Answer e:ArrayListAnswers)
        {
            a=e;
            if (e.IsCorrect==1)
            {
                return e;
            }
        }
        return a;
    }
    public Question (int id) throws SQLException
    {
        this.id=id;
        //Select All answers
        if (LatexExamGenertator.IsDatabase)
        {
        String sql="select * from questionanswer where parentId="+id+"";
        ResultSet rs1;
        rs1=DBConnect.ExecuteMyquery(sql);
        while (rs1.next())
        {
            Answer a=new Answer();
            a.id=rs1.getInt("Id");
            a.Desc=rs1.getString("Description");
            a.IsCorrect=rs1.getInt("IsCorrect");
            ArrayListAnswers.add(a);
            
        }
        }
        else
        {
        
        }
        
            
    }
}
