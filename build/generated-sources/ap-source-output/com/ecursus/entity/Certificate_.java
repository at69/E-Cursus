package com.ecursus.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-03-03T22:36:31")
@StaticMetamodel(Certificate.class)
public class Certificate_ { 

    public static volatile SingularAttribute<Certificate, Integer> id;
    public static volatile SingularAttribute<Certificate, Integer> userId;
    public static volatile SingularAttribute<Certificate, Integer> score;
    public static volatile SingularAttribute<Certificate, Integer> courseId;
    public static volatile SingularAttribute<Certificate, Date> date;

}