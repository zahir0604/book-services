package twyla.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import twyla.dataStore.DataSource;

@Component
public class Start implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        DataSource dataSource = new DataSource();
        dataSource.createTableIfDoesNotExist(DataSource.BOOK_TABLE);
        dataSource.createTableIfDoesNotExist(DataSource.COMMENTS_TABLE);
    }

}