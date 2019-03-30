package com.akvone.dao;

import com.akvone.entity.GroupEntity;
import com.akvone.entity.HistoryRecordEntity;
import com.akvone.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface HistoryRecordRepository extends JpaRepository<HistoryRecordEntity, Long> { // TODO: Verify

  boolean existsByUserAndGroup(@Param("user") UserEntity user, @Param("group") GroupEntity group);

  @Query("select count(distinct user) from HistoryRecordEntity where location.id = :locationId")
  int countUsersByLocation(@Param("locationId") Long locationId);

  List<HistoryRecordEntity> findByLocationId(long locationId);

}
