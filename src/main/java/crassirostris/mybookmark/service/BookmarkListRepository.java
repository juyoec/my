package crassirostris.mybookmark.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by crassirostris on 15. 2. 7..
 */
@Repository
public interface BookmarkListRepository extends JpaRepository<BookmarkList, Long> {
}
