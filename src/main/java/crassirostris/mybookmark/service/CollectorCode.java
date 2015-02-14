package crassirostris.mybookmark.service;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by crassirostris on 15. 2. 7..
 */
@Data
@Entity
@Table(name = "collector_codes")
public class CollectorCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 11, unique = true, nullable = false)
    private long seq;
    @NotNull
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private boolean acceptYN = false;
    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @LastModifiedDate
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
}
