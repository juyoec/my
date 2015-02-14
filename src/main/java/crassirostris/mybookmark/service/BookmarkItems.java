package crassirostris.mybookmark.service;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by crassirostris on 15. 2. 7..
 */
@Data
@Entity
@Table(name = "bookmark_items")
public class BookmarkItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 11, unique = true, nullable = false)
    private long seq;
    @Column(length = 11, nullable = false, updatable = false)
    private long listSeq;
    @Column(length = 5000, nullable = false, updatable = false)
    private String url;
    @Column(length = 5000, nullable = false, updatable = false)
    private String description;
    @Column(nullable = false, updatable = false)
    private int dateHourIndex;
    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
