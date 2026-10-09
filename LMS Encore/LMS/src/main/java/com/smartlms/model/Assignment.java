package com.smartlms.model;

public class Assignment {
    private long id;
    private long courseId;
    private String courseTitle;
    private String title;
    private String instructions;
    private String dueDate;
    private boolean submitted;
    private String answerText;
    public Assignment(long id, long courseId, String courseTitle, String title, String instructions,
                      String dueDate, boolean submitted, String answerText) {
        this.id=id; this.courseId=courseId; this.courseTitle=courseTitle; this.title=title;
        this.instructions=instructions; this.dueDate=dueDate; this.submitted=submitted; this.answerText=answerText;
    }
    public long getId(){return id;}
    public long getCourseId(){return courseId;}
    public String getCourseTitle(){return courseTitle;}
    public String getTitle(){return title;}
    public String getInstructions(){return instructions;}
    public String getDueDate(){return dueDate;}
    public boolean isSubmitted(){return submitted;}
    public String getAnswerText(){return answerText;}
}
