package com.group8.dalsmartteamwork.student;

public class MultipleChoiceSingleResponse implements IResponseObject {
    @Override
    public void addResponse(int questionId, String response) {
        Answer answer = StudentFactory.instance().answer();
        answer.addAnswer(questionId, response);
    }
}
