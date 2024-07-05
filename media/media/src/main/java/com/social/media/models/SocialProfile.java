package com.social.media.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne() // this relationship is mapped by socialProfile field in SocialUser class
	// because of removing redundancy foreign key
	@JoinColumn(name = "social_user") // manually generate name of the foreign key column if fk is used
	@JsonIgnore // for the purpose of blocking circular references
	private SocialUser user;

	private String description;

	public void setSocialUser(SocialUser socialUser) {
		this.user = socialUser;
		if(user.getSocialProfile() != this) {
			user.setSocialProfile(this);
		}
	}
}
