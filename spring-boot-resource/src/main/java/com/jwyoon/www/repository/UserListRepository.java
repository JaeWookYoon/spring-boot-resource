
package com.jwyoon.www.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwyoon.www.model.UserList;

@Repository("userListRepository")
public interface UserListRepository extends JpaRepository<UserList, String>{

    List<UserList> findByIdx(String idx);
    UserList findByUserId(String userId);
	/*
	 * boolean existsByUserEmail(String userEmail); boolean
	 * existsByUserNickname(String userNickname); boolean existsByUserPhone(String
	 * userPhone); boolean existsByUserMobile(String userMobile); boolean
	 * existsByRecommendCode(String recommendCode);
	 */
	/*
	 * static List<Integer> exam = new ArrayList<Integer>(); public static void
	 * main(String[]args) { exam.forEach(a -> System.out.println(a));
	 * 
	 * System.out.println("END"); }
	 */
}
