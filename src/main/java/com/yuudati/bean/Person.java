package com.yuudati.bean;

import lombok.Data;

import java.util.List;

/**
 * @author Created by lixindong[lixindong@kuke.com].
 * @date 2021/9/1
 */
@Data
public class Person {
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 拥有的线索
     */
    private List<Club> clubList;

    /**
     * 排除对象
     */
    private List<String> exceptList;

    /**
     * 倾向的线索
     */
    private List<Trans> wanted;

    /**
     * 下一个人
     */
    private Person next;

    public Person(String nickname, List<Club> clubList) {
        this.nickname = nickname;
        this.clubList = clubList;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nickname='" + nickname + '\'' +
                ", clubList=" + clubList +
                ", exceptList=" + exceptList +
                ", wanted=" + wanted +
                ", next=" + System.identityHashCode(next) +
                '}';
    }
}
