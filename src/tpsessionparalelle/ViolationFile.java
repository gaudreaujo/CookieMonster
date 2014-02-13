package tpsessionparalelle;

import net.sf.json.JSONArray;

public class ViolationFile {
    
    private final String VIOLATION1 = "L'employé n'a pas travaillé au moins 36 heures au bureau";
    private final String VIOLATION2 = "L'employé n'a pas travaillé au moins 38 heures au bureau";
    private final String VIOLATION3 = "L'employé a passé plus de 43 heures au bureau";
    private final String VIOLATION4 = "L'employé a fait plus de 10 heures de télétravail";
    private final String VIOLATION5 = "L'employé n'a pas fait son minimum de 6 heures de travail au bureau";
    private final String VIOLATION6 = "L'employé n'a pas fait son minimum de 4 heures de travail au bureau";
    private final int MIN_EMP_WORKING_TIME_OFFICE = 2280;
    private final int MIN_ADMIN_WORKING_TIME_OFFICE = 2160;
    private final int MAX_WEEK_OFFICE_WORK_TIME = 2580;
    private final int MAX_WEEK_REMOTE_WORK_TIME = 600;
    private JSONArray outputViolationArray = new JSONArray(); 
    private Employee employee;
    
    public ViolationFile(Employee anEmployee){
        this.employee = anEmployee;
        isMinWeekWorkTimeRespected();
        isMaxWeekWorkTimeRespected();
        isWeekRemoteTimeRespected();
        isWorkDayTimeRespected();
    }
    
    public void isMinWeekWorkTimeRespected(){
        if (this.employee.isAnEmployee()){
            if (this.employee.getWeekTotalWorkTime() < MIN_EMP_WORKING_TIME_OFFICE)
                this.outputViolationArray.add(VIOLATION2);
        }
        else{
            if (this.employee.getWeekTotalWorkTime() < MIN_ADMIN_WORKING_TIME_OFFICE)
                this.outputViolationArray.add(VIOLATION1);
        }
    }
    
    public void isMaxWeekWorkTimeRespected(){
        if (this.employee.getWeekTotalWorkTime() > MAX_WEEK_OFFICE_WORK_TIME)
            this.outputViolationArray.add(VIOLATION3);
    }
    
    public void isWeekRemoteTimeRespected(){
        if (!this.employee.isAnEmployee()){
            if (this.employee.getRemoteWorkTime() < MAX_WEEK_REMOTE_WORK_TIME)
                this.outputViolationArray.add(VIOLATION4);
        }
    }
    
    public void isWorkDayTimeRespected(){
        if (this.employee.isAnEmployee()){
            if (!this.employee.getIsWorkDayTimeAccepted())
                this.outputViolationArray.add(VIOLATION5);
        }
        else{
            if (!this.employee.getIsWorkDayTimeAccepted())
                this.outputViolationArray.add(VIOLATION6);
        }
    }
    
    public JSONArray getOutputViolationArray(){
        return this.outputViolationArray;
    }
}
