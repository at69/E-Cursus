/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ecursus.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Emmanuel
 */
@Entity
@Table(name = "readcourse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Readcourse.findAll", query = "SELECT r FROM Readcourse r"),
    @NamedQuery(name = "Readcourse.findById", query = "SELECT r FROM Readcourse r WHERE r.id = :id"),
    @NamedQuery(name = "Readcourse.findByCourseId", query = "SELECT r FROM Readcourse r WHERE r.courseId = :courseId"),
    @NamedQuery(name = "Readcourse.findByUserId", query = "SELECT r FROM Readcourse r WHERE r.userId = :userId"),
    @NamedQuery(name = "Readcourse.findByUserAndCourse", query = "SELECT r FROM Readcourse r WHERE r.userId = :userId AND r.courseId = :courseId"),
    @NamedQuery(name = "Readcourse.findByDate", query = "SELECT r FROM Readcourse r WHERE r.date = :date")})
public class Readcourse implements Serializable {
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
    @Column(name = "userId")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Readcourse() {
    }

    public Readcourse(Integer courseId, Integer userId, Date date) {
        this.courseId = courseId;
        this.userId = userId;
        this.date = date;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        if (!(object instanceof Readcourse)) {
            return false;
        }
        Readcourse other = (Readcourse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecursus.entity.Readcourse[ id=" + id + " ]";
    }
    
}
