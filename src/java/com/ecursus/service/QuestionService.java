/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.service;

import com.ecursus.dao.QuestionDao;
import com.ecursus.entity.Question;
import com.ecursus.entity.Course;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Emmanuel
 */
@Stateless
public class QuestionService {
    
    @EJB
    private QuestionDao questionDao;
    
    public Collection<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }

    public Collection<Question> getQuestionsByCourse(Course course) {
        return questionDao.findQuestionsByCourse(course);
    }

    public Question findQuestionById(int questionId) {
        return questionDao.findQuestionById(questionId);
    }

    public Question addQuestion(Question question) {
        return questionDao.addQuestion(question);
    }

    public Question updateQuestion(Question question) {
        return questionDao.updateQuestion(question);
    }

    public void removeQuestion(Question question) {
        questionDao.removeQuestion(question);
    }
    
    public Integer validateQuizz(Map<Integer,Integer> answers) {
        return questionDao.validateQuizz(answers);
    }
}
