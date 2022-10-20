package msacapstoneproject.infra;

import msacapstoneproject.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel="orderConfirms", path="orderConfirms")
public interface OrderConfirmRepository extends PagingAndSortingRepository<OrderConfirm, Long> {

    

    
}
