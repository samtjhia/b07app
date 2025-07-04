package com.safetyPlanBuilder;

import java.util.List;
public class Question {

    private String question_id;
    private String question;
    private List<String> options;
    private SubQuestion subQuestion;

    //Getters
    public String getQuestion() {
        return question;
    }
    public String getQuestionID() {
        return question_id;
    }
    public List<String> getOptions() {
        return options;
    }
    public SubQuestion getSubQuestion() {
        return subQuestion;
    }


}
