package tr.com.obssintern.book_portal.service;

import tr.com.obssintern.book_portal.model.BaseEntity;

public interface BaseEntityService {
    void nowCreated(BaseEntity baseEntity);

    void nowUpdated(BaseEntity baseEntity);
}
