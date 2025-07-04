package com.safetyPlanBuilder;

import java.util.Map;
import java.util.List;
public class Questionnaire {

    private List<Question> warmUp;
    private Map<String, List<Question>> branchSpecific;
    private List<Question> followUp;

    public List<Question> getWarmUp() {
        return warmUp;
    }
    public Map<String, List<Question>> getBranchSpecific() {
        return branchSpecific;
    }
    public List<Question> getFollowUp() {
        return followUp;
    }
}
