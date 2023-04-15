package tr.com.obssintern.book_portal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.obssintern.book_portal.model.BaseEntity;
import tr.com.obssintern.book_portal.service.BaseEntityService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class BaseEntityServiceImpl implements BaseEntityService {

    @Override
    public void nowCreated(BaseEntity baseEntity) {
        baseEntity.setCreatedAt(LocalDateTime.now());
    }

    @Override
    public void nowUpdated(BaseEntity baseEntity) {
        baseEntity.setUpdatedAt(LocalDateTime.now());
    }
}
