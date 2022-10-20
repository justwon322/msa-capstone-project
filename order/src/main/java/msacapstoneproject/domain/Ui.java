package msacapstoneproject.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name="Ui_table")
@Data
public class Ui {

        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;


}