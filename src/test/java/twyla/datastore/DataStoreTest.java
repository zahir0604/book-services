package twyla.datastore;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import twyla.config.Text;
import twyla.dataStore.DataSource;

import java.sql.Connection;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataStoreTest {


    @Mock
    private Connection connection;

    @Mock
    private DataSource dataSource;

    @Test
    public void testMockConnection() throws Exception {

        when(dataSource.getConnection()).thenReturn(connection);
        Assert.assertNotNull(dataSource.getConnection());
    }

}
