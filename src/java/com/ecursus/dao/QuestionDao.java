/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.dao;

import java.util.Collection;
import javax.ejb.Local;
import com.ecursus.entity.Question;
import com.ecursus.entity.Course;
import java.util.Map;

/**
 *
 * @author Emmanuel
 */
@Local
public interface QuestionDao {
    public Collection<Question> getAllQuestions();
    
    public Collection<Question> findQuestionsByCourse(Course course);

    public Question findQuestionById(int questionId);
    
    public Question addQuestion(Question question);

    public Question updateQuestion(Question question);
    
    public void removeQuestion(Question question);
    
    public Integer validateQuizz(Map<Integer,Integer> answers);
}
