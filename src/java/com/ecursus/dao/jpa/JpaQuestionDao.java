/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.dao.jpa;

import com.ecursus.dao.QuestionDao;
import com.ecursus.entity.Course;
import com.ecursus.entity.Question;
import java.util.Collection;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Emmanuel
 */
@Stateless
public class JpaQuestionDao implements QuestionDao {
    @PersistenceContext(name="ECursus-ejbPU")
    private EntityManager em;

    @Override
    public Collection<Question> getAllQuestions() {
        return em.createNamedQuery("Question.findAll").getResultList();
    }

    @Override
    public Collection<Question> findQuestionsByCourse(Course course) {
        return em.createNamedQuery("Question.findByCourseId").setParameter("courseId", course.getId()).getResultList();
    }

    @Override
    public Question findQuestionById(int questionId) {
        return em.find(Question.class, questionId);
    }

    @Override
    public Question addQuestion(Question question) {
        em.persist(question);
        return question;
    }

    @Override
    public Question updateQuestion(Question question) {
        em.merge(question);
        return question;
    }

    @Override
    public void removeQuestion(Question question) {
        em.remove(question);
    }

    @Override
    public Integer validateQuizz(Map<Integer, Integer> answers) {
        int score = 0;
        for(Map.Entry<Integer,Integer> e : answers.entrySet()) {
            /**
             * getKey retourne l'id de question
             * getValue retourne le numéro de réponse choisie
             */
            Question question = null;
            question = validateAnswer(e.getKey(), e.getValue());
            if(question != null)
                score += 1;
        }

        return score;

    }

    private Question validateAnswer(Integer questionId, Integer correctAnswer) {
        Question question = null;
        try {
            question = findQuestionById(questionId);
        } catch(Exception e) {
            System.out.println(e);
        }
        if(question != null) {
            if(question.getCorrectAnswer() == correctAnswer)
                return question;
            return null;
        }

        return null;
    }
    
}
