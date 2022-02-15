package io.novelis.smartroby.sample.dao.person;

import com.querydsl.jpa.impl.JPAQuery;
import io.novelis.smartroby.sample.dao.common.AbstractDao;
import io.novelis.smartroby.sample.domain.PersonEntity;
import io.novelis.smartroby.sample.domain.QPersonEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDao extends AbstractDao<PersonEntity, PersonRepository> {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public PersonRepository getJpaRepository() {
        return personRepository;
    }

    public List<PersonEntity> findByCriteria(String firstName, String lastName) {
        JPAQuery<PersonEntity> query = getJPAQueryFactory().selectFrom(QPersonEntity.personEntity);
        if(StringUtils.isNotBlank(firstName)) {
            query.where(QPersonEntity.personEntity.firstName.like("%" + firstName + "%"));
        }
        if(StringUtils.isNotBlank(lastName)) {
            query.where(QPersonEntity.personEntity.lastName.like("%" + lastName + "%"));
        }
        return (List<PersonEntity>) query.fetch();
    }
}
