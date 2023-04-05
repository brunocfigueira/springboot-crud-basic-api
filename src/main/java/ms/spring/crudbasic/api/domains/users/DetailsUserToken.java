package ms.spring.crudbasic.api.domains.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor

@Getter
@Setter
public class DetailsUserToken {

    private final Long userId;
    private final String profileAcronym;
    private final String permissionIds;
    private final String permissionNames;
}
