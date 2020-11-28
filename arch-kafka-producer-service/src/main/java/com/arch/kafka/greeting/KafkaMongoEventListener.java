package com.arch.kafka.greeting;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Log4j2
@Component
public class GreetingMongoEventListener extends AbstractMongoEventListener<Greeting> {

    @Override
    public void onAfterSave(AfterSaveEvent<Greeting> event) {
        Greeting source = event.getSource();
        log.info("on after save {}", source);
        if (source.getId() == null) {
            throw new RuntimeException("no id can be found after save Collection");
        }
        String collectionName = getMongoDbCollectionName(source);
        
    }


    private String getMongoDbCollectionName(Object collection) {
        try {
            Document annotation = collection.getClass().getAnnotation(Document.class);
            if (annotation != null && StringUtils.hasText(annotation.value())) {
                return annotation.value();
            }
        } catch (Exception ex) {
            Class<?> aClass = collection.getClass();
            return aClass.getSimpleName().substring(0, 1).toLowerCase() + aClass.getSimpleName().substring(1);
        }
    }
}
