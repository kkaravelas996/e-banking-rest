package gr.aueb.cf.bankApp.core.specifications;

import gr.aueb.cf.bankApp.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    private UserSpecification(){}

    public static Specification<User> userAfmIs(String afm){
        return ((root, query, criteriaBuilder) -> {
            if (afm==null || afm.isBlank()){
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.equal(root.get("afm"),afm);
        });
    }

    public Specification<User> userFieldLike(String field, String value){
        return ((root, query, criteriaBuilder) -> {
            if (value==null || value.trim().isBlank()){
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.like(root.get("field"),value);
        });
    }
}
