package com.ecursus.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-03-03T22:36:31")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> connectionToken;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> password;

}