package tpsessionparalelle;

import java.io.IOException;
import java.io.FileWriter;
import net.sf.json.JSONObject;

public class ScheduleSheetAnalyzer {

    public static void main(String[] args) {

        String inputContent ="";
        String outputPath = "C:/Users/gaudrej/Documents/NetBeansProjects" +
                             "/TpSessionParalelle/src/tpsessionparalelle/";
       
        try {
            inputContent = FileReader.loadFileIntoString(args[0], "UTF-8");
        }catch (IOException ex) {
             System.out.println("Erreur dans l'ouverture du fichier");
         }
        
        JSONObject inputJsonObject = JSONObject.fromObject(inputContent);
        Employee employee = new Employee(inputJsonObject);
        ViolationFile violationFile = new ViolationFile (employee);
        
        try{
            FileWriter file = new FileWriter(outputPath + args[1]);
            file.write(violationFile.getOutputViolationArray().toString(2));
            file.close();
        } catch (IOException ex){ };
    }
}
