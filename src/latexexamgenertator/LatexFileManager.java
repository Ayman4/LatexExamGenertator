/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package latexexamgenertator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;
//import static latexexamgenertator.Question.SpecialSymbol;

/**
 *
 * @author ASUS
 */
public class LatexFileManager {
    public String FileName;  
    public BufferedWriter bf,bfModel;
    public void GenerateLatex()throws IOException
    {
        char ModelName='A';
        for (int i=0;i<LatexExamGenertator.NoumberOfModels;i++)
        {
        bf=new BufferedWriter(new FileWriter(FileName+"_"+ModelName+".tex"));
        bfModel=new BufferedWriter(new FileWriter(FileName+"_"+ModelName+"ModelAnswer.txt"));
        bf.write(Header(ModelName));
        //Generate Questions
        long seed = System.nanoTime();
        Collections.shuffle(ExamForm.AllQuestions, new Random(seed));
        int index=1;
        for (Question q:ExamForm.AllQuestions)
        {
            bf.write(q.toString());
            bfModel.write(index+" - "+(char)(q.ArrayListAnswers.indexOf(q.GetCorrectAnswer())+65) +" - " + q.GetCorrectAnswer().Desc +"\r\n");
            index++;
            
        }
        
        //End of Questions
        bf.write(footer());
        bf.close();
        bfModel.close();
        ModelName++;
//        SpecialSymbol=false;
        }
    }
    public String footer()
    {
        String r="";
        r=" \n \\end{questions} \n \\textit{\\\\Good luck \\\\ "+LatexExamGenertator.Examiner+"}\n" +
" \n" +
"\n" +
"\\end{document}";
        return r;
    }
    public LatexFileManager(String FileName) throws IOException 
    {
        this.FileName=FileName;
        
    }
    public String Header(char ModelName) throws IOException
      {
          
          String result="\\documentclass[a4paper,8pt,answers]{exam}\n" +
"\\usepackage{xpatch}\n" +
"\\usepackage{amsfonts, amsmath, latexsym}\n" +
"\\usepackage{geometry}\n" +
"\\usepackage{graphicx}\n" +
"\\usepackage{bibentry, natbib}\n" +
"\\usepackage{listings}\n" +
"\\usepackage{multirow}\n"+
"\\usepackage{lastpage} \n" +
"\\setcounter{MaxMatrixCols}{10}\n" +
"\\geometry{left=.75in,right=.2in,top=.2in,bottom=0.6in}\n" +
" \\rfoot{Page  \\thepage /  \\pageref{LastPage} "+ModelName+" }\n" +
"\\begin{document}\n" +
"\n" +
"\n" +
"\\begin{table}[]\n" +
"\\centering\n" +
"\\begin{tabular}{lllll}\n" +
"\\multirow{3}{*}{\\includegraphics[width=1in, height=0.8in]{images/Helwan.jpg}} &         &                &               &            \\\\\n" +
"                      & Faculty of computers and information &                &               &            \\\\\n" +
"                      & Software Engineering 1 CS251 & \\multicolumn{2}{l}{\\textbf{Final Exam}} & Exam time: \\textbf{3 hours} \\\\\n" +
"					& \\multicolumn{4}{l}{\\textit{ Note: For True and False Questions select LETTER as written in exam paper}}                                                     \n" +
"\\end{tabular}\n" +
"\\end{table}" +
                  
                  
                  
                  
/*"\\includegraphics[width=1.3in, height=1in]{"+LatexExamGenertator.ImageLogo+"} "+LatexExamGenertator.FacultyName+
" \\\\\n- "+LatexExamGenertator.ExamSubject+"" +
"\\textbf{"+LatexExamGenertator.ExamTitle+"} Exam time: \\textbf{"+LatexExamGenertator.ExamTime+"}\\linebreak\n" +
*/
        "\\xpatchcmd{\\oneparchoices}{\\penalty -50\\hskip 1em plus 1em\\relax}{\\hfill}{}{}\n" +
"\\xpatchcmd{\\oneparchoices}{\\penalty -50\\hskip 1em plus 1em\\relax}{\\hfill}{}{} \n \\begin{questions} ";
          //Updated on 23 may 2016 for narrow styles
          /*String result="\\documentclass[a4paper,8pt,answers]{exam}\n" +
"\n" +
"%\\documentclass[10pt]{article}\n" +
"\\usepackage{xpatch}\n" +
"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
"\\usepackage{amsfonts, amsmath, latexsym}\n" +
"\\usepackage{geometry}\n" +
"%\\usepackage{fancyhdr}\n" +
"\\usepackage{graphicx}\n" +
"\\usepackage{bibentry, natbib}\n" +
"\\usepackage{listings}\n" +
"\\usepackage{lastpage} \n" +
"\\setcounter{MaxMatrixCols}{10}\n" +
"%TCIDATA{OutputFilter=Latex.dll}\n" +
"%TCIDATA{Version=5.50.0.2953}\n" +
"%TCIDATA{<META NAME=\"SaveForMode\" CONTENT=\"1\">}\n" +
"%TCIDATA{BibliographyScheme=Manual}\n" +
"%TCIDATA{LastRevised=Saturday, March 06, 2010 06:09:27}\n" +
"%TCIDATA{<META NAME=\"GraphicsSave\" CONTENT=\"32\">}\n" +
"\n" +
"\\geometry{left=.2in,right=.2in,top=.6in,bottom=2in}\n" +
"%\\thispagestyle{fancy}\n" +
"\\setlength\\headheight{110pt}\n" +
"\n" +
"\\lhead{\\begin{tabular}[l]{l}\n" +
"     "+LatexExamGenertator.ExamNotes+"    \n" +
"        \\end{tabular}\\linebreak\n" +
"}        \n" +
"\\chead{\\textbf{\\Large{"+LatexExamGenertator.ExamTitle+" "+ModelName+"}}}\n" +
"\n" +
"\\rhead {\\includegraphics[width=1.3in, height=1in]{"+LatexExamGenertator.ImageLogo+"}\\\\ "+LatexExamGenertator.FacultyName+" \\\\"+LatexExamGenertator.ExamSubject+"\\\\ Exam time: \\textbf{"+LatexExamGenertator.ExamTime+"}\\linebreak}\n" +
"\n" +
"%\\renewcommand\\headrulewidth{1pt}\n" +
"\n" +
"\n" +
"\n \\rfoot{Page \\thepage /  \\pageref{LastPage}}" +
"\n" +
"\\begin{document}\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"% Need both to patch both instances (choice, correctchoice) in the oneparchoices environment.\n" +
"% Unless there is a global replacement that works within an environment. I don't know.\n" +
"% Replaces \\hskip 1em with \\hfill\n" +
"\\xpatchcmd{\\oneparchoices}{\\penalty -50\\hskip 1em plus 1em\\relax}{\\hfill}{}{}\n" +
"\\xpatchcmd{\\oneparchoices}{\\penalty -50\\hskip 1em plus 1em\\relax}{\\hfill}{}{} \n \\begin{questions}";
*/
          
          return result;
      }
}
