package cn.itsource.hrm.web.dto;

import cn.itsource.hrm.domain.Tenant;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 18:11 2019/12/29
 * @Version : v1.0
 */
@Data
@ToString
public class TenantDto {

    private Tenant tenant;
    private String username;
    private String password;
    private Long mealId;

}
