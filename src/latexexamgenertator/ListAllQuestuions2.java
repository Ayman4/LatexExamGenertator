/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package latexexamgenertator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author ASUS
 */
public class ListAllQuestuions2 extends JFrame{
    private JTable jTable1;
    private JButton jbtn =new JButton("Edit");
    private QuestionAnswerForm qaform;
    public ListAllQuestuions2(QuestionAnswerForm qaform)
    {
        this.qaform=qaform;
        setSize(640,500);
        Object[][] objects = new Object[101][101];
            int i = 1;
            if (ExamForm.AllQuestions.size() != 0) {
                for (Question book : ExamForm.AllQuestions) {
                    //System.out.println(book.Desc);
                      objects[i][0] = i;
                  //  objects[i][1] = book.id;
                    objects[i][1] = book.Desc;
                    i++;
                    
                 }
                //jTable1 = new JTable(objects, new String [] {"Ser","ID", "Description"});
                jTable1 = new JTable(objects, new String [] {"SR","Description"});
                JScrollPane scrollPane = new JScrollPane(jTable1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                //jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                this.add(scrollPane,BorderLayout.CENTER);
                this.add(jbtn,BorderLayout.AFTER_LAST_LINE);
                jbtn.addActionListener(new btnAction());
                
                
            }
    }
            private class btnAction implements ActionListener
            {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRowIndex = jTable1.getSelectedRow();
            int selectedColumnIndex = jTable1.getSelectedColumn();
            Object selectedObject = (Object) jTable1.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
            Question q=ExamForm.SearchQuestions((String)selectedObject);
            qaform.loadQuestionForm(q);
            //qaform.btn4setTest("Update");
            qaform.QSearchFoundat=q;
            dispose();
          
            //JOptionPane.showMessageDialog(null, selectedObject);
        }
            
            }
            
    }

