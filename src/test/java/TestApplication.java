import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.yuudati.Format;
import com.yuudati.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Created by lixindong[lixindong@kuke.com].
 * @date 2021/9/1
 */
@Slf4j
public class TestApplication {

    @Test
    public void test() {
        System.out.println("23423423424");
    }

    @Test
    public void main(){
        String p1 = "A 123456";
        String p2 = "B 11234";
        String p3 = "C 1111567";

        List<String> personStrList = Lists.newArrayList(p1, p2, p3);
        List<Person> personList = personStrList.stream().map(p -> {
            List<String> strings = Splitter.on(" ").trimResults().omitEmptyStrings()
                    .splitToList(p);
            return Format.formatPerson(strings.get(0), strings.get(1), null, null);
        }).collect(Collectors.toList());
        personList = Format.buildLinkedList(personList);

        List<Person> editList = Lists.newArrayList(personList);
//        Collections.copy(editList, personList);
        while (!Format.isStop(editList)) {
            Iterator<Person> iterator = editList.iterator();
            while (iterator.hasNext()) {
                Person current = iterator.next();
                Format.trans(current, current.getNext());
                current.setNext(current.getNext().getNext());
            }
        }
        personList.forEach(item -> {
            log.info(item.toString());
        });
    }

}
