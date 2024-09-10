package audit;

import config.CustomRevisionListener;
import lombok.Data;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "REVISION_INFO")
@RevisionEntity(CustomRevisionListener.class)
@Data
public class Revision implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revision_seq")
    @SequenceGenerator(name = "revision_seq", sequenceName = "rbac.seq_revision_id")
    @RevisionNumber
    private Long id;

    @Column(name = "Revision_DATE")
    private Date date;
}
