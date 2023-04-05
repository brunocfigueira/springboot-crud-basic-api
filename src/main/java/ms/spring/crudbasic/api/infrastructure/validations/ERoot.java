package ms.spring.crudbasic.api.infrastructure.validations;

import lombok.Getter;

@Getter
public enum ERoot {
    USER_ID(1),
    PROFILE_ID(1),
    PERMISSION_ID(1);
    private final int value;

    private ERoot(int value) {
        this.value = value;
    }


}
