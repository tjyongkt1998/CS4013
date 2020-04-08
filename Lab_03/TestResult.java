public class TestResult{
    private double percentage[];
    private String[] grade;
    private String[] Result;
    double score1;
    double score2;
    double score3;
    
    public TestResult(){
        percentage = new double[]{0,1,30,35,40,48,52,56,60,64,72,80};
        grade = new String[]{"NG","F","D2","D1","C3","C2",
                             "C1","B3","B2","B1","A2","A1"};
    
    }
    
    public void setScore(int i, double value){
        if( i == 1) score1 = value;
        else if( i == 2) score2 = value;
        else if( i == 3) score3 = value;
    }
    
    public double getScore(int i){
        if( i == 1) return score1;
        else if( i == 2) return score2;
        else if( i == 3) return score3;
        return 0;
    }
    
    public double getAverage(){
        return ((score1+score2+score3)/3);
    }
    
    public double getTotal(){
        return (score1+score2+score3);
    }
    
    public String getGrade(int value){
        double number = getScore(value);
        for( int i = percentage.length - 1; i >=0; i--){
            if (number >= percentage[i]){
                return grade[i];
            }
        }
        return "Invalid Score";
    }
    
    public String toString() {
        return this.score1 + "," + getGrade(1)+"\n"+this.score2 +","+ getGrade(2)+"\n"+ this.score3 + "," + getGrade(3);
    }
}