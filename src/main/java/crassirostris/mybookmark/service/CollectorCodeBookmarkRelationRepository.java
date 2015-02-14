package crassirostris.mybookmark.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by crassirostris on 15. 2. 8..
 */
@Repository
public interface CollectorCodeBookmarkRelationRepository extends JpaRepository<BookmarkList, Long> {
    List<CollectorCodeBookmarkRelation> findAllByCollectorCode(String code);
}
