package msacapstoneproject.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name="OrderConfirm_table")
@Data
public class OrderConfirm {

        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;


}