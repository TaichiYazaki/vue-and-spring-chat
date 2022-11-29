package com.example.vueandspringchat.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vueandspringchat.entity.SignUp;

@Transactional
@Repository
public interface SignUpRepository extends JpaRepository<SignUp, Integer> {

    @Modifying
    @Query(value ="update signup set file_name = :fileName where id = :id",nativeQuery=true)
    SignUp insertImgFile(@Param("id")Integer id,@Param("fileName")String fileName);
}
