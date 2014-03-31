/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Emmanuel
 */
@Entity
@Table(name = "question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findById", query = "SELECT q FROM Question q WHERE q.id = :id"),
    @NamedQuery(name = "Question.findByCourseId", query = "SELECT q FROM Question q WHERE q.courseId = :courseId"),
    @NamedQuery(name = "Question.findByWording", query = "SELECT q FROM Question q WHERE q.wording = :wording"),
    @NamedQuery(name = "Question.findByAnswersArray", query = "SELECT q FROM Question q WHERE q.answersArray = :answersArray"),
    @NamedQuery(name = "Question.findByCorrectAnswer", query = "SELECT q FROM Question q WHERE q.correctAnswer = :correctAnswer")})
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "courseId")
    private Integer courseId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "wording")
    private String wording;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "answersArray")
    private String answersArray;
    @Basic(optional = false)
    @NotNull
    @Column(name = "correctAnswer")
    private int correctAnswer;

    public Question() {
    }

    public Question(Integer courseId, String wording, String answersArray, int correctAnswer) {
        this.courseId = courseId;
        this.wording = wording;
        this.answersArray = answersArray;
        this.correctAnswer = correctAnswer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public String getAnswersArray() {
        return answersArray;
    }

    public void setAnswersArray(String answersArray) {
        this.answersArray = answersArray;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecursus.entity.Question[ id=" + id + " ]";
    }
    
}
