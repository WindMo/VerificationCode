package ws.common.demo.pojo;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * @author WindShadow
 * @version 2020/12/2.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String password;
}
