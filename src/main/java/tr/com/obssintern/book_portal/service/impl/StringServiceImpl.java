package tr.com.obssintern.book_portal.service.impl;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.obssintern.book_portal.service.StringService;

@NoArgsConstructor
@Service
public class StringServiceImpl implements StringService {
    @Override
    public String properPathVariable(String variable) {
        return variable.replace('-', ' ');
    }
}
