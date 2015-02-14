package crassirostris.mybookmark.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by crassirostris on 15. 2. 8..
 */
@Repository
public interface BookmarkItemsRepository extends JpaRepository<BookmarkItems, Long> {
        List<BookmarkItems> findAllByDateHourIndex(int dateHourIndex);
}
