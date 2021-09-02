package com.yuudati;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.yuudati.bean.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Created by lixindong[lixindong@kuke.com].
 * @date 2021/9/1
 */
@Slf4j
public class Application {

    public static void main(String[] args) {
        System.out.println("请输入待换列表:");
        Scanner scanner = new Scanner(System.in);

        List<String> personStrList = Lists.newArrayList();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (null == line || "".equals(line)) {
                break;
            }
            personStrList.add(line);
        }
        List<Person> personList = personStrList.stream().map(p -> {
            List<String> strings = Splitter.on("-").trimResults().omitEmptyStrings()
                    .splitToList(p);
            return Format.formatPerson(strings.get(0), strings.get(1), null, null);
        }).collect(Collectors.toList());
        personList = Format.buildLinkedList(personList);

        List<Person> editList = Lists.newArrayList(personList);
            for (Person person : editList) {
                Person next = person.getNext();
                while (!Objects.equals(person, next)) {
                    Format.trans(person, next);
                    next = next.getNext();
                }
            }
        personList.forEach(item -> {
            item.getClubList().forEach(club -> log.info("{} 发送 {} 给 {}",
                    club.getSourceNickName(), club.getClub(), club.getTargetNickName()));
        });
    }

}
