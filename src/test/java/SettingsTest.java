import com.zhytnik.library.tools.Settings;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class SettingsTest {
    @Test
    public void getString() {
        Settings s = Settings.getInstance();
        String str = s.getString("mysql.user");
        assertNotNull(str);
    }

    @Test
    public void getInteger() {
        Settings s = Settings.getInstance();
        Integer val = s.getInteger("mysql.max_active");
        assertNotNull(val);
    }
}