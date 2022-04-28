import cn.hutool.extra.template.TemplateConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class GenTest {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(File.separator);
        List list = new ArrayList();
        list.removeIf(Predicate.isEqual(1));
    }

}
