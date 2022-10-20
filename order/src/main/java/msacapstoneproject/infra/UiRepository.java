package msacapstoneproject.infra;

import msacapstoneproject.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel="uis", path="uis")
public interface UiRepository extends PagingAndSortingRepository<Ui, Long> {

    

    
}
