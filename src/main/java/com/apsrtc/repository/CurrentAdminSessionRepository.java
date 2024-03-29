package com.apsrtc.repository;

import com.apsrtc.model.CurrentAdminSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentAdminSessionRepository extends JpaRepository<CurrentAdminSession, Integer> {
    @Query("select c from CurrentAdminSession c where c.aid=?1")
    public CurrentAdminSession findByaid(String aid);
}
