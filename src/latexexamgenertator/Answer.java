/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package latexexamgenertator;

import java.io.Serializable;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class Answer extends Article {
  public int IsCorrect;
  public static int GID=1;
  public Answer()
  {
      this.id=GID;
      GID++;              
  }
  public void InsertAnswer(int parentQuestion, Boolean IsCorrect) throws SQLException
  {
      if (IsCorrect)
      this.IsCorrect=1;
      else
      this.IsCorrect=0;    
     
   if (LatexExamGenertator.IsDatabase)
   {
      String sql="INSERT INTO `latexexamjava`.`questionanswer` (`id`, `Description`, `ParentID`, `IsCorrect`) VALUES (NULL, '"+Desc+"', '"+parentQuestion+"', '"+this.IsCorrect+"');";
      System.out.println(sql);
      DBConnect.ExecuteInsertquery(sql);
   }
   else
   {
       
       ExamForm.AllQuestions.get(ExamForm.AllQuestions.size()-1).ArrayListAnswers.add(this);
       System.out.println("Insert Answer "+ this.Desc +"to Q" + ExamForm.AllQuestions.size());
   }
  }
}
