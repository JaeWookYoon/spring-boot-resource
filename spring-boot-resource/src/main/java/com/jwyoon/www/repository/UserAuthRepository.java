package com.jwyoon.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jwyoon.www.model.UserAuth;

import java.util.List;

@Repository("userAuthRepository")
public interface UserAuthRepository extends JpaRepository<UserAuth, String>{

    @Query(nativeQuery=true,value="select * from user_auth where id=:idx")
    public List<UserAuth> findByIdx(@Param("idx")String idx);

}
