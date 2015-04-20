package io.github.celebes.hazelcast.test.samples.model;

import io.github.celebes.hazelcast.test.samples.parameters.UserParameters;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllUsers", query = "SELECT u FROM User u"),
		@NamedQuery(name = "findUserById", query = "SELECT u FROM User u WHERE u.id = :id") })
public class User implements Serializable {

	private static final long serialVersionUID = -7249137508537609310L;

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String login;
	
	@Transient
	private UserParameters userParameters;

	public User() {
	}

	public User(Long id, String login) {
		super();
		this.id = id;
		this.login = login;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public UserParameters getUserParameters() {
		return userParameters;
	}

	public void setUserParameters(UserParameters userParameters) {
		this.userParameters = userParameters;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", userParameters="
				+ userParameters + "]";
	}
}
