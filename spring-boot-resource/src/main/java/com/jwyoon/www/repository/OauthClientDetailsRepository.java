package com.jwyoon.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwyoon.www.model.OauthClientDetails;

@Repository("oauthClientDetailsRepository")
public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails, String>{

}
