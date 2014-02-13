package tpsessionparalelle;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

public class Employee {
    
    private JSONObject inputJsonObject;
    private int idEmployeeNumber;
    private int weekWorkTime;
    private int weekendWorkTime;
    private int totalWeekWorkTime;
    private int remoteWorkTime;
    private boolean isEmployee = true;
    private boolean isWorkDayTimeAccepted = true;
    private JSONArray[] jsonTableOfWeekDays = new JSONArray[5];
    
    public Employee (JSONObject inputJsonObject ){
        this.inputJsonObject = inputJsonObject;
        this.idEmployeeNumber = Integer.parseInt(inputJsonObject.getString("numero_employe"));
        this.isEmployee = isAnEmployee();
        calculateWeekWorkTime();
        calculateWeekendWorkTime();
        calculateTotalWeekWorkTime();
        calculateWorkTimePerDay(this.jsonTableOfWeekDays);
    }
    
    public void calculateWeekWorkTime(){
        int weekWorkTime = 0;
        for (int i = 1; i<=5 ; i++){
            int arraySize = this.inputJsonObject.getJSONArray("jour"+i).size();
            this.jsonTableOfWeekDays[i-1] = this.inputJsonObject.getJSONArray("jour"+i);
            for (int j = 0; j < arraySize ; j ++){
                if (JSONObject.fromObject(this.inputJsonObject.getJSONArray("jour"+i).getString(j)).getInt("projet") > 900){
                    this.remoteWorkTime += JSONObject.fromObject(this.inputJsonObject.getJSONArray("jour"+i).getString(j)).getInt("minutes");
                }
                else
                    weekWorkTime += JSONObject.fromObject(this.inputJsonObject.getJSONArray("jour"+i).getString(j)).getInt("minutes");    
            }
        }
        this.weekWorkTime = weekWorkTime;
    }
    public void calculateWeekendWorkTime(){
        int weekendWorkTime = 0;
        for (int i = 1; i<=2; i++){
            int arraySize = this.inputJsonObject.getJSONArray("weekend"+i).size();
            for (int j = 0; j < arraySize ; j++){
                if (JSONObject.fromObject(this.inputJsonObject.getJSONArray("weekend"+i).getString(j)).getInt("projet") > 900){
                    this.remoteWorkTime += JSONObject.fromObject(this.inputJsonObject.getJSONArray("weekend"+i).getString(j)).getInt("minutes");
                }
                else
                    this.weekendWorkTime += JSONObject.fromObject(this.inputJsonObject.getJSONArray("weekend"+i).getString(j)).getInt("minutes");
            }
        }
    }
     
    public void calculateWorkTimePerDay(JSONArray[] dayJsonArrayTable){
        JSONArray dayJsonArray;
        for (int i=0 ; i<dayJsonArrayTable.length; i++){
            dayJsonArray = dayJsonArrayTable[i];
            int workTime = 0;
                for (int j=0; j<dayJsonArray.size(); j++){
                    workTime += dayJsonArray.getJSONObject(j).getInt("minutes");   
                }
                if (isCompleteDay(workTime)==false){
                    this.isWorkDayTimeAccepted = false;
                    break; 
                }
        }
    }
    public boolean isCompleteDay(int workTime){
        if (!isAnEmployee()){
            if (workTime < 240)
                return false;
        }
        else{
            if (workTime < 360)
                return false;
        }
        return true;
    }

    public boolean isAnEmployee(){
        if (this.idEmployeeNumber<1000)
            return false;
        else
            return true;
    }
    
    public void calculateTotalWeekWorkTime(){
        this.totalWeekWorkTime = this.weekendWorkTime + this.weekWorkTime;
    }
    
    public int getWeekTotalWorkTime(){
        return this.totalWeekWorkTime;
    }
    
    public int getWeekendWorkTime(){
        return this.weekendWorkTime;
    }
    
    public int getWeekWorkTime(){
        return this.weekWorkTime;
    }
    
    public int getRemoteWorkTime(){
        return this.remoteWorkTime;
    }
    
    public int getIdEmployeeNumber(){
        return this.idEmployeeNumber;
    }
    
    public boolean getIsEmployee(){
        return this.isEmployee;
    }
    public boolean getIsWorkDayTimeAccepted(){
        return this.isWorkDayTimeAccepted;
    }

}
    
    

