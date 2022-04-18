package com.sevi.cadegouline.repositories;

import com.sevi.cadegouline.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Long> {

    @Query("SELECT t.id FROM Track t")
    List<Long> findAllIds();

    @Query("SELECT count(t) FROM Track t where t.oldOrNew='old' and t.id in :allTrackIds")
    int countOld(@Param("allTrackIds") List<Long> allTrackIds);

    @Query("SELECT count(t) FROM Track t where t.oldOrNew='new' and t.id in :allTrackIds")
    int countNew(@Param("allTrackIds") List<Long> allTrackIds);
}