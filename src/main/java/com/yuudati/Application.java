package com.yuudati;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.yuudati.bean.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Created by lixindong[lixindong@kuke.com].
 * @date 2021/9/1
 */
@Slf4j
public class Application {

    public static void main(String[] args) {
        String p1 = "65 1112355667";
        String p2 = "acce 3444577";
        String p3 = "q 1223344566";
        String p4 = "竹子1 1123345577";
        String p5 = "竹子2 12334467777";

        List<String> personStrList = Lists.newArrayList(p1, p2, p3, p4, p5);
        List<Person> personList = personStrList.stream().map(p -> {
            List<String> strings = Splitter.on(" ").trimResults().omitEmptyStrings()
                    .splitToList(p);
            return Format.formatPerson(strings.get(0), strings.get(1), null, null);
        }).collect(Collectors.toList());
        personList = Format.buildLinkedList(personList);

        List<Person> editList = Lists.newArrayList(personList);
//        Collections.copy(editList, personList);
//        while (!Format.isStop(editList)) {
            for (Person person : editList) {
                Person next = person.getNext();
                while (!Objects.equals(person, next)) {
                    Format.trans(person, next);
                    next = next.getNext();
                }
            }
//            for (int i = 0; i < editList.size(); i++) {
//                Person current = editList.get(i);
//                if (Objects.equals(current, current.getNext())) {
//                    continue;
//                }
//                Format.trans(current, current.getNext());
//                if (i == editList.size() - 1) {
//                    current.setNext(editList.get(0));
//                } else {
//                    current.setNext(current.getNext().getNext());
//                }
//            }
//        }
        personList.forEach(item -> {
            item.getClubList().forEach(club -> {
                log.info("{} 发送 {} 给 {}", club.getSourceNickName(), club.getClub(), club.getTargetNickName());
            });
        });
    }

}
