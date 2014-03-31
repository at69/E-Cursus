package com.ecursus.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-03-03T22:36:31")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile SingularAttribute<Question, Integer> id;
    public static volatile SingularAttribute<Question, String> wording;
    public static volatile SingularAttribute<Question, Integer> correctAnswer;
    public static volatile SingularAttribute<Question, String> answersArray;
    public static volatile SingularAttribute<Question, Integer> courseId;

}