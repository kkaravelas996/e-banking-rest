package gr.aueb.cf.bankApp.core.filters;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserFilters extends GenericFilters{

    @Nullable
    private String afm;
}
