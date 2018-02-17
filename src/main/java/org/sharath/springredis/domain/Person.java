/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sharath.springredis.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Sharath Kulal
 */
@Entity
@Table(name="person")
public class Person implements Serializable{
    
    @Id
    private Integer id;
    
    @Column
    private String name;
    
    @Column
    private Instant dob;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="person_address",
		joinColumns={@JoinColumn(name="person_id", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="address_id", referencedColumnName="id")})
	private Set<Address> personAddress = new HashSet<>();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getDob() {
        return dob;
    }

    public void setDob(Instant dob) {
        this.dob = dob;
    }
    
    public Set<Address> getPersonAddress() {
        return personAddress;
    }
    
    public void addAddress(Address address) {
        personAddress.add(address);
    }
    
}
