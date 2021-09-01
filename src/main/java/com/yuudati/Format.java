package com.yuudati;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.yuudati.bean.Club;
import com.yuudati.bean.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Created by lixindong[lixindong@kuke.com].
 * @date 2021/9/1
 */
@Slf4j
public class Format {
    /**
     * 移动元素
     *
     * @param sourcePerson 源
     * @param targetPerson 目标
     * @return
     */
    public static void trans(Person sourcePerson, Person targetPerson) {
        log.info("开始进行移动: {} ----> {}",
                sourcePerson.getNickname(), targetPerson.getNickname());
        List<Club> sourceClubList = sourcePerson.getClubList()
                .stream().filter(club -> !club.isTrans())
                .collect(Collectors.toList());
        List<Club> targetClubList = targetPerson.getClubList();
        int sourceIndex = 0, targetIndex = 0;
        while (sourceIndex < sourceClubList.size()
                && targetIndex < targetClubList.size()) {
            Club source = sourceClubList.get(sourceIndex);
            Club target = targetClubList.get(targetIndex);
            if (source.compareTo(target) == 0) {
                if (source.getClub().equals(target.getClub())) {
                    source.setTrans(true);
                    source.setTargetNickName(targetPerson.getNickname());
                    sourceIndex++;
                    targetIndex++;
                }
            } else if (source.compareTo(target) < 0) {
                sourceIndex++;
            } else {
                targetIndex++;
            }
        }
    }

    /**
     * 判断是否可以停止
     *
     * @param personList
     * @return
     */
    public static boolean isStop(List<Person> personList) {
        /*
        所有的线索为已经交换状态
        或
        所有未交换状态的线索都不相同
         */
        if (personList == null || personList.size() < 2) {
            return true;
        }
        List<Club> noTransClubList = personList.stream()
                .flatMap(person -> person.getClubList().stream())
                .filter(club -> !club.isTrans())
                .collect(Collectors.toList());
        if (noTransClubList.size() < 2){
            return true;
        }
        HashMap<Integer, Integer> map = Maps.newHashMap();
        for (Club item :
                noTransClubList) {
            Integer count = map.getOrDefault(item.getClub(), 0);
            ++count;
            if (count > 1) {
                return false;
            }
            map.put(item.getClub(), count);
        }
        return false;
    }

    /**
     * 格式化为单向环形列表
     *
     * @param personList
     * @return
     */
    public static List<Person> buildLinkedList(List<Person> personList) {
        if (personList == null || personList.isEmpty()) {
            throw new RuntimeException("人员列表为空");
        }
        if (personList.size() < 2) {
            throw new RuntimeException("人员列表必须大于1");
        }
        Person first = personList.get(0);
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            if (i == personList.size() - 1) {
                person.setNext(first);
            } else {
                person.setNext(personList.get(i + 1));
            }
        }
        return personList;
    }

    /**
     * 格式化为节点
     *
     * @param nickname
     * @param clubStr
     * @param exclude
     * @param wanted
     * @return
     */
    public static Person formatPerson(String nickname,
                                      String clubStr,
                                      String exclude,
                                      String wanted) {
        if (Strings.isNullOrEmpty(nickname)) {
            throw new RuntimeException("缺少昵称");
        }
        if (Strings.isNullOrEmpty(clubStr)) {
            throw new RuntimeException("缺少线索");
        }
        List<Club> clubList = Arrays.stream(clubStr.split(""))
                .map(item -> {
                    Integer clubInt = Integer.valueOf(item);
                    return new Club(nickname, clubInt);
                })
                .sorted()
                .collect(Collectors.toList());

        return new Person(nickname, clubList);
    }

}
